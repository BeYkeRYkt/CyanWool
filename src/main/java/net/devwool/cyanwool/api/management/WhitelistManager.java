package net.devwool.cyanwool.api.management;

import java.util.UUID;

import net.devwool.cyanwool.api.Server;

public interface WhitelistManager {

    public Server getServer();
    
    public boolean checkPlayer(UUID uuid);

    public void addPlayer(UUID uuid);

    public void removePlayer(UUID uuid);
}