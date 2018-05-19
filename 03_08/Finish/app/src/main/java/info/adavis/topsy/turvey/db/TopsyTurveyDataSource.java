package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseSQLiteHelper dbHelper;

    public TopsyTurveyDataSource (Context context)
    {
        this.dbHelper = new DatabaseSQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        this.database = dbHelper.getWritableDatabase();

        Log.d( TAG, "open: database opened" );
    }

    public void close()
    {
        dbHelper.close();

        Log.d( TAG, "close: database closed" );
    }

    public void createRecipe (Recipe recipe)
    {
        long rowId = cupboard().withDatabase(database).put(recipe);

        Log.d( TAG, "createRecipe: the id: " + rowId );
    }

    public List<Recipe> getAllRecipes ()
    {
        return cupboard().withDatabase(database)
                .query(Recipe.class)
                .list();
    }

    public void updateRecipe(Recipe recipe)
    {
        cupboard().withDatabase(database).put(recipe);
    }

    public void deleteRecipe(Recipe recipe)
    {
        cupboard().withDatabase(database).delete(recipe);
    }

    public void deleteAllRecipes()
    {
        cupboard().withDatabase(database).delete(Recipe.class, null);
    }
}
