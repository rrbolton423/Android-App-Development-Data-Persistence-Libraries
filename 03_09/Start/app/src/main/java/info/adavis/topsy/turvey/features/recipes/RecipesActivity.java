package info.adavis.topsy.turvey.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.concurrent.Callable;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.db.RecipesDataProvider;
import info.adavis.topsy.turvey.db.TopsyTurveyDataSource;
import info.adavis.topsy.turvey.models.Recipe;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private TopsyTurveyDataSource dataSource;
    private RecipesAdapter adapter;

    // Create Disposable object
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        dataSource = new TopsyTurveyDataSource(this);

        setupRecyclerView();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();

        /*
        Load the list of Recipes, loads nothing for the first time
        the app is loaded. Listen for any changes.
        Updates the UI on the Main thread
         */

        // In RxJava, dispose of anything you subscribe or observe to.
        // We are returned a Disposable object here
        disposable = dataSource.getAllRecipes() // Get a list of all Recipes
                .observeOn(AndroidSchedulers.mainThread()) // Observe this on our main thread
                .subscribe(new Consumer<List<Recipe>>() { // Subscribe and use a new consumer
                    @Override
                    public void accept(List<Recipe> recipes) throws Exception {

                        // Update our adapter with the new Recipes
                        adapter.setRecipes(recipes);

                        // Notify our adapter of data changes
                        adapter.notifyDataSetChanged();
                    }
                });

        /*
        Add the list of Recipes from the data source to the database
        asynchronously when the app starts up
         */

        // Instead of using the AsyncTask to do background work, we're going
        // to use the fromCallable() method on the Completable.
        Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                // Loop through the list of Recipes
                for (Recipe recipe : RecipesDataProvider.recipesList)
                {
                    // Add each Recipe to the Room db
                    dataSource.createRecipe(recipe);
                }

                // Return null
                return null;
            }
        })
                .subscribeOn(Schedulers.io()) // Subscribe
                .subscribe(); // This is how we set ourselves up to handle updates on our
        // database. If we delete or add any items to our database,
        // the list is going to automatically update.
    }

    private void setupRecyclerView ()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter(this);
        recipesRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        
        // Dispose of our Disposable object
        disposable.dispose();
        
        super.onDestroy();
    }
}
