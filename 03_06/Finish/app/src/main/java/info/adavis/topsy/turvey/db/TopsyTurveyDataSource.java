package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();
    private final RecipeDao recipeDao;
    private final RecipeStepDao recipeStepDao;

    public TopsyTurveyDataSource (Context context)
    {
        TopsyTurveyDatabase database = TopsyTurveyDatabase.getInstance(context);
        recipeDao = database.recipeDao();
        recipeStepDao = database.recipeStepDao();
    }

    public void createRecipe (Recipe recipe)
    {
        long rowId = recipeDao.createRecipe(recipe);
        List<RecipeStep> steps = recipe.getSteps();
        if (steps != null) {
            for (RecipeStep step : steps) {
                step.setRecipeId(rowId);
            }

            recipeStepDao.insertAll(steps);
        }

        Log.d(TAG, "createRecipe: the id: " + rowId);
    }

    public List<Recipe> getAllRecipes ()
    {
        List<Recipe> recipes = new ArrayList<>();



        return recipes;
    }
}
