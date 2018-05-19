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

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("topsy_turvey.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.deleteRealm(configuration);

        Realm.setDefaultConfiguration(configuration);
    }
}
