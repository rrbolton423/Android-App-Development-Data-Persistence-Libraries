package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

public class TopsyTurveyDataSource {
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    // Create RecipeDao field
    private final RecipeDao recipeDao;

    // Create RecipeStepDao field
    private final RecipeStepDao recipeStepDao;

    public TopsyTurveyDataSource(Context context) {
        // Get our Database instance
        TopsyTurveyDatabase database = TopsyTurveyDatabase.getInstance(context);

        // Get an instance of the RecipeDao object and save it as a field of the class
        recipeDao = database.recipeDao();

        // Get an instance of the RecipeStepDao object and save it as a field of the class
        recipeStepDao = database.recipeStepDao();

    }

    public void createRecipe(Recipe recipe) {

        // Add a Recipe to the Room db
        long rowId = recipeDao.createRecipe(recipe);

        // Get the step of the Recipe
        List<RecipeStep> steps = recipe.getSteps();

        // If the Recipe does have steps...
        if (steps != null) {

            // Loop through the steps of said Recipe
            for (RecipeStep step : steps) {

                // Set the RecipeId of the Step
                // using the rowId parameter
                step.setRecipeId(rowId);
            }

            // Add the steps of the Recipe to the database
            recipeStepDao.insertAll(steps);
        }

        // Log the Id of the created Recipe
        Log.d(TAG, "createRecipe: the id: " + rowId);
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();


        return recipes;
    }
}
