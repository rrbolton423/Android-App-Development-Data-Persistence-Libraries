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
    private RecipesAdapter adapter;

    // Declare a TopsyTurveyDataSource object
    private TopsyTurveyDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        // Instantiate the DataSource with the Activity's context
        dataSource = new TopsyTurveyDataSource(this);

        setupRecyclerView();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();

        // Open the dataSource
        dataSource.open();

        // Iterate over the list of recipes
        for (Recipe recipe : RecipesDataProvider.recipesList)
        {
            // Add the recipe to the database
            dataSource.createRecipe(recipe);
        }

        // Get and display all the Recipes in the RecyclerView
        List<Recipe> allRecipes = getRecipes();

        // Delete all Recipes from the database
        dataSource.deleteAllRecipes();

        // Retrieve all of the recipes from the database again
        // and display the updated recipes in the RecyclerView
        getRecipes();
    }

    private List<Recipe> getRecipes()
    {

        // Get the list of recipes
        List<Recipe> allRecipes = dataSource.getAllRecipes();

        // Loop through each recipe in the list
        for (Recipe recipe : allRecipes)
        {
            // Log the recipe
            Log.i(TAG, "the recipe: " + recipe);
        }

        // Display the list of recipes in the RecyclerView
        adapter.setRecipes(allRecipes);

        // Return the list of recipes
        return allRecipes;
    }

    @Override
    protected void onPause ()
    {
        super.onPause();

        // Close the dataSource
        dataSource.close();
    }

    private void setupRecyclerView ()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter( this );
        recipesRecyclerView.setAdapter( adapter );
    }

}
