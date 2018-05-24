package info.adavis.topsy.turvey.features.recipes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
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

        disposable = dataSource.getAllRecipes()
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<List<Recipe>>() {
                      @Override
                      public void accept(List<Recipe> recipes) throws Exception {
                          adapter.setRecipes(recipes);
                          adapter.notifyDataSetChanged();
                      }
                  });

        Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (Recipe recipe : RecipesDataProvider.recipesList)
                {
                    dataSource.createRecipe(recipe);
                }
                return null;
            }
        })
                   .subscribeOn(Schedulers.io())
                   .subscribe();

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
        disposable.dispose();
        super.onDestroy();
    }
}
