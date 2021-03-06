package info.adavis.topsy.turvey.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        dataSource = new TopsyTurveyDataSource();
        dataSource.open();

        setupRecyclerView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Grab the list of recipes
        for (Recipe recipe : RecipesDataProvider.recipesList) {

            // Add each Recipe in the list into the database
            dataSource.createRecipe(recipe);
        }

        // Get the list of recipes from the Realm query
        List<Recipe> allRecipes = dataSource.getAllRecipes();

        // Loop through the list of recipes
        for (Recipe recipe : allRecipes)
        {

            // Print each Recipe out
            Log.i(TAG, "recipe: " + recipe);
        }
    }

    @Override
    protected void onDestroy()
    {
        dataSource.close();

        super.onDestroy();
    }

    private void setupRecyclerView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);
    }

}
