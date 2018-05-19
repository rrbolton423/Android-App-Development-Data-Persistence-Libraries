package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Have DatabaseSQLiteOpenHelper extend SQLiteOpenHelper
public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    // Define DB Constants
    private static final String DATABASE_NAME = "topsy_turvey.db";
    private static final int VERSION_NUMBER = 1;

    // Create constructor
    public DatabaseSQLiteOpenHelper(Context context) {

        // Call to super with updated information
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    // Override onCreate() method
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    // Override onUpgrade() method
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
