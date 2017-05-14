package pl.btwarog.fangame.base;

import android.app.Application;

import com.facebook.stetho.Stetho;

import pl.btwarog.fangame.BuildConfig;
import pl.btwarog.fangame.injector.component.ApplicationComponent;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public abstract class BaseApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initStetho();
        initializeInjector();
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    abstract protected void initializeInjector();

    public ApplicationComponent getApplictaionComponent() {
        return applicationComponent;
    }
}
