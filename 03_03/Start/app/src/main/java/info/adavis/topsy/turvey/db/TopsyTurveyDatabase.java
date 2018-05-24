package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

// Add @Database annotation for Room db class
// Add list of Entities contained inside of our Database,
// and version number of the db
@Database(entities = {Recipe.class, RecipeStep.class}, version = 1)
// Have Room db class extend "RoomDatabase"
public abstract class TopsyTurveyDatabase extends RoomDatabase {

    // DB name constant
    private static final String DATABASE_NAME = "topsey_turvey";

    // Declare instance veriable for Room db
    private static TopsyTurveyDatabase INSTANCE = null;

    // Method that retrieves an instance of the Room db
    public static TopsyTurveyDatabase getInstance(Context context)
    {
        // If the instance is nul...
        if (INSTANCE == null) {

            // Synchronize the DB class
            synchronized (TopsyTurveyDatabase.class)
            {
                // Get an instance of the Room db by passing the application context,
                // the name of the database class, and the database file name.
                // Finally, build it.
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TopsyTurveyDatabase.class,
                        DATABASE_NAME)
                        .build();
            }
        }
        // Return the Room db instance
        return INSTANCE;
    }
}
