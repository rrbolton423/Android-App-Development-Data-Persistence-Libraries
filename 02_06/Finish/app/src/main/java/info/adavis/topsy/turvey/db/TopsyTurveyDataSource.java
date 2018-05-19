package info.adavis.topsy.turvey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
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

        List<RecipeStep> steps = recipe.getSteps();
        if (steps != null && steps.size() > 0)
        {
            for (RecipeStep step : steps)
            {
                createRecipeStep(step, rowId);
            }
        }
    }

    public void createRecipeStep(RecipeStep recipeStep, long recipeId)
    {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeStepEntry.COLUMN_RECIPE_ID, recipeId);
        values.put(RecipeContract.RecipeStepEntry.COLUMN_INSTRUCTION, recipeStep.getInstruction());
        values.put(RecipeContract.RecipeStepEntry.COLUMN_STEP_NUMBER, recipeStep.getStepNumber());

        long rowId = database.insert(RecipeContract.RecipeStepEntry.TABLE_NAME, null, values);

        Log.d(TAG, "recipe step with id: " + rowId);
    }

    public List<Recipe> getAllRecipes()
    {
        List<Recipe> recipes = new ArrayList<>();

        String selectQuery = "SELECT * FROM recipe";

        Cursor cursor = database.rawQuery(selectQuery, null);
        try
        {
            while (cursor.moveToNext())
            {
                Recipe recipe = new Recipe(
                        cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID))
                );
                recipe.setId(cursor.getLong(cursor.getColumnIndex(RecipeContract.RecipeEntry._ID)));

                recipes.add(recipe);
            }
        }
        finally
        {
            if (cursor != null && !cursor.isClosed())
            {
                cursor.close();
            }
        }

        return recipes;
    }

    public void updateRecipe(Recipe recipe)
    {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        String selection = RecipeContract.RecipeEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(recipe.getId())};

        int count = database.update(RecipeContract.RecipeEntry.TABLE_NAME, values, selection, selectionArgs);
        Log.d(TAG, "number of records updated: " + count);
    }
}
