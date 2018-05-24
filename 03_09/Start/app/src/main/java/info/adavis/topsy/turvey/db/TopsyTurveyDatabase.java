package info.adavis.topsy.turvey.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

@Database(entities = {Recipe.class, RecipeStep.class}, version = 1)
public abstract class TopsyTurveyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "topsy_turvey";
    private static TopsyTurveyDatabase INSTANCE = null;

    public static TopsyTurveyDatabase getInstance(Context context)
    {
        if (INSTANCE == null) {
            synchronized (TopsyTurveyDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                TopsyTurveyDatabase.class,
                                                DATABASE_NAME)
                                .build();
            }
        }
        return INSTANCE;
    }

    public abstract RecipeDao recipeDao();

    public abstract RecipeStepDao recipeStepDao();
}
