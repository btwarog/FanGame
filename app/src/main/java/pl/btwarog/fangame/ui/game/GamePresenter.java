package pl.btwarog.fangame.ui.game;

import android.os.Handler;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.realm.RealmResults;
import pl.btwarog.fangame.database.AppDatabase;
import pl.btwarog.fangame.database.model.LocalPlayer;

/**
 * Created by bartlomiejtwarog on 16.05.2017.
 */

public class GamePresenter implements GameContract.Presenter {

    GameContract.View view;

    AppDatabase appDatabase;

    List<LocalPlayer> localPlayers;

    Random rand;

    Handler handler = new Handler();

    int firstPlayerIndex;

    int secondPlayerIndex;

    @Inject
    public GamePresenter(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        this.rand = new Random();
    }


    @Override
    public void fetchData() {
        appDatabase.setup();
        RealmResults<LocalPlayer> players = appDatabase.findAll(LocalPlayer.class);
        if (players == null || players.size() == 0) {
            appDatabase.close();
            view.finish();
            return;
        }
        localPlayers = appDatabase.getRealm().copyFromRealm(players);
        appDatabase.close();
        drawPlayers();
    }

    @Override
    public void drawPlayers() {
        if (localPlayers == null || localPlayers.size() == 0) {
            fetchData();
            return;
        }
        firstPlayerIndex = drawANumber();
        do {
            secondPlayerIndex = drawANumber();
        } while (firstPlayerIndex == secondPlayerIndex);

        new Runnable() {

            @Override
            public void run() {

            }
        };
        view.showProgress();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setPlayers(localPlayers.get(firstPlayerIndex), localPlayers.get(secondPlayerIndex));
            }
        }, 2000);

    }

    @Override
    public void attachView(GameContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(null);
        this.view = null;
    }

    private int drawANumber() {
        return rand.nextInt(localPlayers.size());
    }
}
