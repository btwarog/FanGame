package pl.btwarog.fangame;

import pl.btwarog.fangame.base.BaseApplication;
import pl.btwarog.fangame.injector.component.DaggerApplicationComponent;
import pl.btwarog.fangame.injector.module.ApplicationModule;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public class FanGameApplication extends BaseApplication {

    @Override
    protected void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
