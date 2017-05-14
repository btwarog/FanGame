package pl.btwarog.fangame.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class AppDatabase {

    private Context context;
    Realm realm;

    public AppDatabase(Context context) {
        this.context = context;
        Realm.init(context);
    }

    public AppDatabase setup() {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        return this;
    }

    public Realm getRealm() {
        return realm;
    }

    public <T extends RealmObject> T add(T model) {
        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
        return model;
    }

    public <T extends RealmObject> RealmResults<T> findAll(Class<T> clazz) {
        return realm.where(clazz).findAll();
    }

    public void close() {
        realm.close();
        realm = null;
    }
}
