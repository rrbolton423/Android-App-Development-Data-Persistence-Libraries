package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    public TopsyTurveyDataSource (Context context)
    {

    }

    public void createRecipe (Recipe recipe)
    {
        long rowId = -1;



        Log.d(TAG, "createRecipe: the id: " + rowId);
    }

    public List<Recipe> getAllRecipes ()
    {
        List<Recipe> recipes = new ArrayList<>();



        return recipes;
    }
}
