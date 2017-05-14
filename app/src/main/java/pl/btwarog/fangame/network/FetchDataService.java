package pl.btwarog.fangame.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.util.List;

import javax.inject.Inject;

import pl.btwarog.fangame.base.BaseApplication;
import pl.btwarog.fangame.common.Constants;
import pl.btwarog.fangame.common.utils.DataMapper;
import pl.btwarog.fangame.database.AppDatabase;
import pl.btwarog.fangame.database.model.LocalPlayer;
import pl.btwarog.fangame.domain.api.FanGameService;
import pl.btwarog.fangame.domain.result.GetPlayersResult;
import retrofit2.Response;

import static pl.btwarog.fangame.common.Constants.Status.MSG_FAILED;
import static pl.btwarog.fangame.common.Constants.Status.MSG_SUCCESS;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class FetchDataService extends IntentService {

    private static boolean isRunning = false;

    @Inject
    FanGameService fanGameService;

    @Inject
    AppDatabase appDatabase;

    public FetchDataService() {
        this("FetchDataService");
    }

    public FetchDataService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (isRunning)
            return;
        isRunning = true;

        initializeDependencyInjector();

        Intent localIntent = null;

        try {
            Response<GetPlayersResult> getPlayersResultResponse = fanGameService.getPlayers().execute();
            if (getPlayersResultResponse.isSuccessful()) {
                appDatabase.setup();
                List<LocalPlayer> localPlayers = DataMapper.convertDomainPlayersToLocalPlayers(getPlayersResultResponse.body().getPlayers());
                if (localPlayers.size() > 0) {
                    appDatabase.getRealm().beginTransaction();
                    appDatabase.getRealm().copyToRealm(localPlayers);
                    appDatabase.getRealm().commitTransaction();
                }
                appDatabase.close();
                localIntent = new Intent(Constants.BROADCAST_ACTION)
                        // Puts the status into the Intent
                        .putExtra(Constants.EXTENDED_DATA_STATUS, MSG_SUCCESS);
            } else {
                localIntent = new Intent(Constants.BROADCAST_ACTION)
                        // Puts the status into the Intent
                        .putExtra(Constants.EXTENDED_DATA_STATUS, MSG_FAILED);

            }
        } catch (Exception e) {
            localIntent = new Intent(Constants.BROADCAST_ACTION)
                    // Puts the status into the Intent
                    .putExtra(Constants.EXTENDED_DATA_STATUS, MSG_FAILED);
            e.printStackTrace();
        } finally {
            isRunning = false;
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
        }
    }

    protected void initializeDependencyInjector() {
        ((BaseApplication) getApplication()).getApplictaionComponent().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
