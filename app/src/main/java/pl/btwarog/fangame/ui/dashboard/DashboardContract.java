package pl.btwarog.fangame.ui.dashboard;

import java.util.List;

import pl.btwarog.fangame.base.BasePresenter;
import pl.btwarog.fangame.base.BaseView;
import pl.btwarog.fangame.database.model.LocalPlayer;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class DashboardContract {

    interface View extends BaseView {
        void finish();

        void setAdapterData(List<LocalPlayer> localPlayers);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData();
    }
}
