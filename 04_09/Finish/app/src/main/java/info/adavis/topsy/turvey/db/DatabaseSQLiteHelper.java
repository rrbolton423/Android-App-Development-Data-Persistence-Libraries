package info.adavis.topsy.turvey.db;

import android.content.ContentValues;
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
    private static final int DATABASE_VERSION = 2;
    private static final String TAG = DatabaseSQLiteHelper.class.getSimpleName();

    public DatabaseSQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static
    {
        CupboardFactory.setCupboard(
                new CupboardBuilder().
                registerFieldConverterFactory(new GsonListFieldConverterFactory(new Gson())).build()
        );
        cupboard().register(Recipe.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        cupboard().withDatabase(db).createTables();

        Log.d( TAG, "onCreate: database created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        cupboard().withDatabase(db).upgradeTables();

        if (oldVersion == 1)
        {
            ContentValues values = new ContentValues();
            values.put("numberOfStars", 5);

            cupboard().withDatabase(db).update(Recipe.class, values);
        }

        Log.d( TAG, "onUpgrade: database updated" );
    }
}
