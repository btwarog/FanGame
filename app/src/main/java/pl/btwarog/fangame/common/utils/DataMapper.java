package pl.btwarog.fangame.common.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import pl.btwarog.fangame.database.model.LocalPlayer;
import pl.btwarog.fangame.domain.model.Player;

/**
 * Created by bartlomiejtwarog on 14.05.2017.
 */

public class DataMapper {

    public static List<LocalPlayer> convertDomainPlayersToLocalPlayers(List<Player> domainPlayers) {
        List<LocalPlayer> localPlayers = new ArrayList<>();
        for (Player player : domainPlayers) {
            if (player.getFppg() != null) {
                LocalPlayer localPlayer = new LocalPlayer();
                localPlayer.setFirstName(player.getFirstName());
                localPlayer.setLastName(player.getLastName());
                localPlayer.setId(domainPlayers.indexOf(player));
                localPlayer.setRemoteId(player.getId());
                localPlayer.setPoints(player.getFppg() != null ? player.getFppg().toPlainString() : "");
                if (
                        player.getImages() != null &&
                                player.getImages().getDefaultImage() != null &&
                                !TextUtils.isEmpty(player.getImages().getDefaultImage().getUrl())
                        ) {
                    localPlayer.setImageUrl(player.getImages().getDefaultImage().getUrl());

                }
                localPlayers.add(localPlayer);
            }
        }
        return localPlayers;
    }
}
