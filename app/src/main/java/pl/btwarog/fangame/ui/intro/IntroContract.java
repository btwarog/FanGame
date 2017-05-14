package pl.btwarog.fangame.ui.intro;

import pl.btwarog.fangame.base.BasePresenter;
import pl.btwarog.fangame.base.BaseView;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class IntroContract {

    interface View extends BaseView {
        void startFetchingRemoteDataService();
    }

    interface Presenter extends BasePresenter<View> {
        void fetchData();
    }
}
