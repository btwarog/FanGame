package pl.btwarog.fangame.domain.api;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.btwarog.fangame.domain.result.GetPlayersResult;
import retrofit2.Call;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */
@Singleton
public class FanGameApi implements FanGameService{

    private final FanGameService fanGameService;

    @Inject
    public FanGameApi(FanGameService fanGameService) {
        this.fanGameService = fanGameService;
    }
    @Override
    public Call<GetPlayersResult> getPlayers() {
        return null;
    }
}
