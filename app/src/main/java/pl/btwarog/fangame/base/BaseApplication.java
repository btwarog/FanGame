package pl.btwarog.fangame.base;

import android.app.Application;

import pl.btwarog.fangame.injector.component.ApplicationComponent;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public abstract class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    abstract protected void initializeInjector();

    public ApplicationComponent getApplictaionComponent() {
        return applicationComponent;
    }
}
