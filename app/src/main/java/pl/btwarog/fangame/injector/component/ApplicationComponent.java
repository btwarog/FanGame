package pl.btwarog.fangame.injector.component;

import javax.inject.Singleton;

import dagger.Component;
import pl.btwarog.fangame.database.AppDatabase;
import pl.btwarog.fangame.domain.api.FanGameApi;
import pl.btwarog.fangame.injector.module.ApplicationModule;
import pl.btwarog.fangame.injector.module.DatabaseModule;
import pl.btwarog.fangame.injector.module.NetworkModule;
import pl.btwarog.fangame.network.FetchDataService;
import pl.btwarog.fangame.ui.dashboard.DashboardFragment;
import pl.btwarog.fangame.ui.game.GameFragment;
import pl.btwarog.fangame.ui.intro.IntroFragment;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */


@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    void inject(IntroFragment introFragment);

    void inject(FetchDataService fetchDataService);

    FanGameApi fanGameApi();

    AppDatabase appDatabase();

    void inject(DashboardFragment dashboardFragment);

    void inject(GameFragment gameFragment);
}