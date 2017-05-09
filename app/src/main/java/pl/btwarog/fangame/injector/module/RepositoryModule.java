package pl.btwarog.fangame.injector.module;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.btwarog.fangame.FanGameApplication;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

@Module
public class RepositoryModule {
    private final FanGameApplication fanGameApplication;

    public RepositoryModule(FanGameApplication fanGameApplication) {
        this.fanGameApplication = fanGameApplication;
    }

    @Provides
    @Singleton
    FanGameApplication provideFanGameApplication() {
        return fanGameApplication;
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }
}
