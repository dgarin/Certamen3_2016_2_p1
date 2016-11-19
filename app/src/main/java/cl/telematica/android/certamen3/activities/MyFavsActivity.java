package cl.telematica.android.certamen3.activities;

import android.os.Bundle;

import cl.telematica.android.certamen3.R;
import cl.telematica.android.certamen3.models.Feed;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by luxor on 18-11-16.
 */

public class MyFavsActivity extends MainActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favs);

        RealmResults<Feed> result2 = realm.where(Feed.class)
                .findAll();


    }



}
