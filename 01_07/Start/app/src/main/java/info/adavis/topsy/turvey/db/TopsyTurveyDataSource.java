package info.adavis.topsy.turvey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import info.adavis.topsy.turvey.models.Recipe;

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

        // Insert the values into the RecipeEntry table
        long rowId = database.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);

        // Log the row number of the inserted data
        Log.d(TAG, "recipe with id: " + rowId);

    }
}
