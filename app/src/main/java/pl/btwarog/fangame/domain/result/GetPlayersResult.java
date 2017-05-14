package pl.btwarog.fangame.domain.result;

import java.util.List;

import pl.btwarog.fangame.domain.model.Player;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class GetPlayersResult {

    List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
