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

        dataSource.open();

        // Loop through the list of recipes in the list
        for (Recipe recipe : RecipesDataProvider.recipesList) {

            // Add eah recipe into the database
            dataSource.createRecipe(recipe);
        }

        // Get the list of recipes
        List<Recipe> allRecipes = getRecipes();

        // Get the first recipe in the list
        Recipe updatedRecipe = allRecipes.get(0);

//        Delete the recipe in the database
//        dataSource.deleteRecipe(updatedRecipe);

        // Delete all recipes in the database
        dataSource.deleteAllRecipes();

        // Get the list of recipes from the dataSource class,
        // and update the display in the RecyclerView
        getRecipes();
    }

    public List<Recipe> getRecipes()
    {
        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes)
        {
            Log.i(TAG, "recipe: " + recipe);
        }
        adapter.setRecipes(allRecipes);

        return allRecipes;
    }

    @Override
    protected void onPause ()
    {
        dataSource.close();

        super.onPause();
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
