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
import io.realm.OrderedRealmCollection;

public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private TopsyTurveyDataSource dataSource;
    private RecipesAdapter recipesAdapter;

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

        for (Recipe recipe : RecipesDataProvider.recipesList)
        {
            dataSource.createRecipe(recipe);
        }

        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes)
        {
            Log.i(TAG, "recipe: " + recipe);
        }

        // Update the recipe
        dataSource.modifyDescription();

        // Create an un-managed Recipe object
        Recipe unManaged = new Recipe("Red Velvet", "Yummy!", R.drawable.cake_2);

        // Set an ID on the un-managed instance
        unManaged.setId(allRecipes.get(0).getId());

        // Make a update call
        dataSource.createRecipe(unManaged);
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

        // Pass a reference to our query in the recipesAdapter,
        // and a boolean values to auto-update the adapter
        recipesAdapter = new RecipesAdapter((OrderedRealmCollection<Recipe>) dataSource.getAllRecipes(), true);
        recipesRecyclerView.setAdapter(recipesAdapter);
    }

}
