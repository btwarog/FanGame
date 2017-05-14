package pl.btwarog.fangame.base;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void onDestroy();

}
