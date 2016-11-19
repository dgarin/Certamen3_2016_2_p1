package cl.telematica.android.certamen3.activities;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by luxor on 18-11-16.
 */

public class DataBaseActivity extends Application {
    private Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        realm.init(this);

    }

}
