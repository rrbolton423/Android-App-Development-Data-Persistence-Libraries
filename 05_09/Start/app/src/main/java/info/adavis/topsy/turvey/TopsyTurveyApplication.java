package info.adavis.topsy.turvey;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TopsyTurveyApplication extends Application
{

    // Constant for Realm schema version
    public static final int SCHEMA_VERSION = 2;

    @Override
    public void onCreate ()
    {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION) // Provide a schema version
                .migration(new Migration()) // Provide a Migration
                .name("topsy_turvey.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
