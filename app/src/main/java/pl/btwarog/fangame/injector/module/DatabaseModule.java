package pl.btwarog.fangame.injector.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.btwarog.fangame.database.AppDatabase;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(final Context context) {
        return new AppDatabase(context);
    }
}
