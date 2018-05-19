package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TopsyTurveyDataSource
{

    // Create TAG for logging
    private static final String TAG = "TopsyTurveyDataSource";

    // Declare Database and OpenHelper objects
    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public TopsyTurveyDataSource(Context context)
    {
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
}
