package info.adavis.topsy.turvey.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

@Database(entities = {Recipe.class, RecipeStep.class}, version = 2) // Upgrade the db version to 2
public abstract class TopsyTurveyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "topsy_turvey";
    private static TopsyTurveyDatabase INSTANCE = null;

    // Create an instance of a Room Migration class
    // Provide the start and end version for the Migration in it's
    // constructor method
    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
             /*
              Add SQL statement to update the database
              */

             // Alter the Recipe table, adding the "number_of_stars"
             // column of type "INTEGER"
             database.execSQL("ALTER TABLE recipe"
             + " ADD COLUMN number_of_stars INTEGER");
        }
    };

    public static TopsyTurveyDatabase getInstance(Context context)
    {
        if (INSTANCE == null) {
            synchronized (TopsyTurveyDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                TopsyTurveyDatabase.class,
                                                DATABASE_NAME)
                                .addMigrations(MIGRATION_1_2) // Add the Migration to Room
                                .build();
            }
        }
        return INSTANCE;
    }

    public abstract RecipeDao recipeDao();

    public abstract RecipeStepDao recipeStepDao();
}
