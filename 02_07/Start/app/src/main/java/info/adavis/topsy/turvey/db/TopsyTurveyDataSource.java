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

public class TopsyTurveyDataSource {

    // Create TAG for logging
    private static final String TAG = "TopsyTurveyDataSource";

    // Declare Database and OpenHelper objects
    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public TopsyTurveyDataSource(Context context) {
        // Initialize our DBHelper variable
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    // The open method will get the writable database from
    // the DBHelper class
    public void open() {

        // Get an instance of the SQLiteDatabase
        this.database = dbHelper.getWritableDatabase();

        // Log helpful information
        Log.d(TAG, "database is opened");
    }

    // The open method will close the writable database
    public void close() {

        // Close the db
        dbHelper.close();

        // Log helpful information
        Log.d(TAG, "database is closed");
    }

    // This method inserts recipes into the database
    public void createRecipe(Recipe recipe) {

        // Create a ContentValues object
        ContentValues values = new ContentValues();

        // Put the recipe name into the name column of the RecipeEntry table
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());

        // Put the recipe description into the description column of the RecipeEntry table
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());

        // Put the recipe image resource id into the image resource id column of the RecipeEntry table
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        // Insert the values into the RecipeEntry table, and get the
        // rowId of the inserted data in the RecipeEntry table
        long rowId = database.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);

        // Log the row number of the inserted data
        Log.d(TAG, "recipe with id: " + rowId);

        // Get the steps from a given recipe
        List<RecipeStep> steps = recipe.getSteps();

        // If this recipe has steps...
        if (steps != null && steps.size() > 0) {

            // Loop through the recipe's steps
            for (RecipeStep step : steps) {

                // Add the recipe steps to the it's table in the database,
                // passing the step and the rowId of the RecipeEntry table
                createRecipeStep(step, rowId);
            }
        }

    }

    // This method inserts recipe steps into the database
    public void createRecipeStep(RecipeStep recipeStep, long recipeId) {

        // Create a ContentValues object
        ContentValues values = new ContentValues();

        // Put the recipe id into the recipe id column of the RecipeStepEntry table
        values.put(RecipeContract.RecipeStepEntry.COLUMN_RECIPE_ID, recipeId);

        // Put the recipe step instruction into the instruction column of the RecipeStepEntry table
        values.put(RecipeContract.RecipeStepEntry.COLUMN_INSTRUCTION, recipeStep.getInstruction());

        // Put the recipe step number into the step number column of the RecipeStepEntry table
        values.put(RecipeContract.RecipeStepEntry.COLUMN_STEP_NUMBER, recipeStep.getStepNumber());

        // Insert the values into the RecipeStepEntry table
        long rowId = database.insert(RecipeContract.RecipeStepEntry.TABLE_NAME, null, values);

        // Log the row number of the inserted data
        Log.d(TAG, "recipe step with id: " + rowId);

    }

    // getAllRecipes() returns a list of recipes from the database
    public List<Recipe> getAllRecipes()
    {
        // Create a list of recipes
        List<Recipe> recipes = new ArrayList<>();

        // This String query will return all the recipes
        String selectQuery = "SELECT * FROM recipe";

        // Execute the raw query and get back a cursor object
        // containing the expected data
        Cursor cursor = database.rawQuery(selectQuery, null);

        try // Try to...
        {

            // While the cursor has data to loop through...
           while (cursor.moveToNext())
           {

               // Create a new Recipe object
               Recipe recipe = new Recipe(

                       // Get the name for our recipe
                       cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME)),

                       // Get the description for our recipe
                       cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION)),

                       // Get the image resource id for our recipe
                       cursor.getInt(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID))
                       );

               // Set the Id of the recipe
               recipe.setId(cursor.getLong(cursor.getColumnIndex(RecipeContract.RecipeEntry._ID))
               );

               // Add the recipe to the list
               recipes.add(recipe);
           }

        }
        finally // Finally, close the cursor
        {

            // If the cursor is not null and is not closed
            if (cursor != null && !cursor.isClosed()) {

                // Close the cursor
                cursor.close();
            }
        }

        // Return the list of recipes
        return recipes;
    }

    // updateRecipe() deletes a single recipe from the database
    public void updateRecipe(Recipe recipe)
    {
        // Create a ContentValues object
        ContentValues values = new ContentValues();

        // Put the recipe name into the name column of the RecipeEntry table
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());

        // Put the recipe description into the description column of the RecipeEntry table
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());

        // Put the recipe image resource id into the image resource id column of the RecipeEntry table
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        // Create selection String
        String selection = RecipeContract.RecipeEntry._ID + " = ?";

        // Create selection args array
        String[] selectionArgs = {String.valueOf(recipe.getId())};

        // Make database update call, passing the table name, values,
        // selection, and selection args
        // Also get the count of the rows that were updated
        int count = database.update(RecipeContract.RecipeEntry.TABLE_NAME, values, selection, selectionArgs);

        // Log the number of records updated
        Log.d(TAG, "number of records updated: " + count);
    }

    // deleteRecipe() deletes a single recipe from the database
    public void deleteRecipe(Recipe recipe)
    {

        // Create selection String
        String selection = RecipeContract.RecipeEntry._ID + " = ?";

        // Create selection args array
        String[] selectionArgs = {String.valueOf(recipe.getId())};

        // Make database delete call, passing the table name,
        // selection, and selection args
        // Also get the count of the rows that were deleted
        int count = database.delete(RecipeContract.RecipeEntry.TABLE_NAME, selection, selectionArgs);

        // Log the number of records deleted
        Log.d(TAG, "number of records deleted: " + count);
    }

    // deleteAllRecipes() deletes all recipes from the database
    public void deleteAllRecipes()
    {

        // Make database delete call, passing ONLY the table name
        // Also get the count of the rows that were deleted
        int count = database.delete(RecipeContract.RecipeEntry.TABLE_NAME, null, null);

        // Log the number of records deleted
        Log.d(TAG, "number of records deleted: " + count);
    }
}
