package pl.btwarog.fangame.injector.component;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import pl.btwarog.fangame.injector.module.ApplicationModule;
import rx.Scheduler;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */


@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Gson gson();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();
}