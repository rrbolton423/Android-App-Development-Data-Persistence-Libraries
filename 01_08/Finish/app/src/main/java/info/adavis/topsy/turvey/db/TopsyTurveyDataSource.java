package info.adavis.topsy.turvey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public TopsyTurveyDataSource(Context context)
    {
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    public void open()
    {
        this.database = dbHelper.getWritableDatabase();

        Log.d(TAG, "database is opened");
    }

    public void close()
    {
        dbHelper.close();

        Log.d(TAG, "database is closed");
    }

    public void createRecipe(Recipe recipe)
    {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        long rowId = database.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);

        Log.d(TAG, "recipe with id: " + rowId);
    }
}
