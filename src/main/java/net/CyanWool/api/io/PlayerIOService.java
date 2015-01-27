package net.CyanWool.api.io;

import net.CyanWool.api.entity.player.Player;

public interface PlayerIOService {

    public void readPlayer(Player player);

    public void savePlayer(Player player);
}