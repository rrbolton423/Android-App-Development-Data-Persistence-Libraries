package info.adavis.topsy.turvey;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TopsyTurveyApplication extends Application
{
    @Override
    public void onCreate ()
    {
        super.onCreate();

        // Initialize Realm
        Realm.init(this);

        // Create a new builder instance
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("topsy_turvey.realm") // Create name of the Realm file
                .deleteRealmIfMigrationNeeded() // Allow Realm to delete the Realm if it encounters any migration issues when initializing
                .build(); // Build our configuration

        // To start with an empty database each time we run the app,
        // we will delete the Realm file for now
        Realm.deleteRealm(configuration);

        // Set the Realm configuration we created
        Realm.setDefaultConfiguration(configuration);
    }
}
