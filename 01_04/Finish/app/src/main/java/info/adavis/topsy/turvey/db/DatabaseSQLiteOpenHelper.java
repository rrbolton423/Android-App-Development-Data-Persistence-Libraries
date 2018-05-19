package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "topsy_turvey.db";
    private static final int VERSION_NUMBER = 1;

    public DatabaseSQLiteOpenHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
