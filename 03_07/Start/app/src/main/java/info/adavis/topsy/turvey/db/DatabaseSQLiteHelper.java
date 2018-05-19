package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import info.adavis.topsy.turvey.models.Recipe;
import nl.littlerobots.cupboard.tools.gson.GsonListFieldConverterFactory;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DatabaseSQLiteHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "topsy_turvey.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = DatabaseSQLiteHelper.class.getSimpleName();

    public DatabaseSQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static
    {
        // Register a field converter factory of type GSON list
        CupboardFactory.setCupboard(
              new CupboardBuilder()
                      .registerFieldConverterFactory(new GsonListFieldConverterFactory(new Gson()))
                      .build()
        );

        // Register our model / entity class
        cupboard().register(Recipe.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Pass the database instance to cupboard to create the tables
        cupboard().withDatabase(db).createTables();

        Log.d( TAG, "onCreate: database created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        // Drop all of the existing table and create them again
        cupboard().withDatabase(db).dropAllTables();

        // Pass the database instance to cupboard to recreate the tables
        cupboard().withDatabase(db).createTables();

        Log.d( TAG, "onUpgrade: database updated" );
    }
}
