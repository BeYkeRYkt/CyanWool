package net.devwool.cyanwool.api.management;

import java.util.List;

import net.devwool.cyanwool.api.Server;

public interface PlayerManager {

    public Server getServer();

    public void joinPlayer(PlayerData info);

    public void spawnPlayer(PlayerData info);

    public void leavePlayer(Player player);

    public List getPlayers();
}