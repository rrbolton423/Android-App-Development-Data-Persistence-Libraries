package info.adavis.topsy.turvey.db;

import android.util.Log;

import info.adavis.topsy.turvey.models.Recipe;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    public void open()
    {
        Log.d( TAG, "open: database opened" );
    }

    public void close()
    {
        Log.d( TAG, "close: database closed" );
    }

    public void createRecipe (final Recipe recipe)
    {
        Log.d(TAG, "createRecipe: the id: " + recipe.getId());
    }

}