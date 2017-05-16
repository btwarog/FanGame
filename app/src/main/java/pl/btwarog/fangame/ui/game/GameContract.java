package pl.btwarog.fangame.ui.game;

import pl.btwarog.fangame.base.BasePresenter;
import pl.btwarog.fangame.base.BaseView;
import pl.btwarog.fangame.database.model.LocalPlayer;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public interface GameContract {

    interface View extends BaseView {
        void finish();

        void setPlayers(LocalPlayer localPlayer, LocalPlayer localPlayer2);

        void showProgress();

    }

    interface Presenter extends BasePresenter<View> {
        void fetchData();

        void drawPlayers();

    }
}
