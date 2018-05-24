package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseSQLiteHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "topsy_turvey.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = DatabaseSQLiteHelper.class.getSimpleName();

    public DatabaseSQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d( TAG, "onCreate: database created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d( TAG, "onUpgrade: database updated" );
    }
}
