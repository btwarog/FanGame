package pl.btwarog.fangame.ui.intro;

import android.os.Handler;

import javax.inject.Inject;

import io.realm.RealmResults;
import pl.btwarog.fangame.database.AppDatabase;
import pl.btwarog.fangame.database.model.LocalPlayer;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class IntroPresenter implements IntroContract.Presenter {

    IntroContract.View view;

    AppDatabase appDatabase;

    Handler handler;

    @Inject
    public IntroPresenter(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        handler = new Handler();

    }


    @Override
    public void fetchData() {
        appDatabase.setup();
        RealmResults<LocalPlayer> players =  appDatabase.findAll(LocalPlayer.class);
        if(players != null && players.size() > 0)
            return;
        appDatabase.close();
        view.startFetchingRemoteDataService();
    }

    @Override
    public void attachView(IntroContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        this.view = null;
        handler.removeCallbacks(null);
    }
}
