package pl.btwarog.fangame.domain.api;

import pl.btwarog.fangame.domain.result.GetPlayersResult;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public interface FanGameService {
    @GET("liamjdouglas/bb40ee8721f1a9313c22c6ea0851a105/raw/6b6fc89d55ebe4d9b05c1469349af33651d7e7f1/Player.json")
    Call<GetPlayersResult> getPlayers();
}
