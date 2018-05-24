package info.adavis.topsy.turvey.db;

import android.util.Log;

import info.adavis.topsy.turvey.models.Recipe;
import io.realm.Realm;

public class TopsyTurveyDataSource
{
    private static final String TAG = TopsyTurveyDataSource.class.getSimpleName();

    private Realm realm;

    public void open()
    {
        realm = Realm.getDefaultInstance();

        Log.d( TAG, "open: database opened" );
    }

    public void close()
    {
        realm.close();
        Log.d( TAG, "close: database closed" );
    }

    public void createRecipe (final Recipe recipe)
    {
        realm.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                realm.insert(recipe);
            }
        });

        Log.d(TAG, "createRecipe: the id: " + recipe.getId());
    }

}