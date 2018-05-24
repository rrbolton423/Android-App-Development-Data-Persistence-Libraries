package info.adavis.topsy.turvey.features.recipes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.db.RecipesDataProvider;
import info.adavis.topsy.turvey.db.TopsyTurveyDataSource;
import info.adavis.topsy.turvey.models.Recipe;

public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private TopsyTurveyDataSource dataSource;
    private RecipesAdapter adapter;

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

        // Create a new AsyncTask
        new AsyncTask<Void, Void, List<Recipe>>() {
            @Override
            protected List<Recipe> doInBackground(Void... voids) {

                // Loop through the list of Recipes...
                for (Recipe recipe : RecipesDataProvider.recipesList)
                {
                    // Add the Recipe to the database
                    dataSource.createRecipe(recipe);
                }

                // Return an empty list of Recipes if we don't have
                // anything in our RecipeList
                return new ArrayList<>();
            }

            @Override
            protected void onPostExecute(List<Recipe> recipes) {
                super.onPostExecute(recipes);
            }
        }.execute(); // Execute the task

//        List<Recipe> recipes = dataSource.getAllRecipes();
//        adapter.setRecipes(recipes);
//        adapter.notifyDataSetChanged();
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

}
