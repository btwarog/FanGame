package pl.btwarog.fangame.ui.dashboard;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;
import pl.btwarog.fangame.database.AppDatabase;
import pl.btwarog.fangame.database.model.LocalPlayer;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class DashboardPresenter implements DashboardContract.Presenter {

    DashboardContract.View view;

    AppDatabase appDatabase;

    List<LocalPlayer> localPlayers;

    @Inject
    public DashboardPresenter(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }


    @Override
    public void fetchData() {
        appDatabase.setup();
        RealmResults<LocalPlayer> players =  appDatabase.findAll(LocalPlayer.class);
        if(players == null || players.size() == 0) {
            appDatabase.close();
            view.finish();
            return;
        }
        localPlayers = appDatabase.getRealm().copyFromRealm(players);
        view.setAdapterData(localPlayers);
        appDatabase.close();
    }

    @Override
    public void attachView(DashboardContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
