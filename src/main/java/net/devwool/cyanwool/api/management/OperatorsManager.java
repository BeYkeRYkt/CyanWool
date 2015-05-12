package net.devwool.cyanwool.api.management;

import java.util.UUID;

import net.devwool.cyanwool.api.Server;

public interface OperatorsManager {
    
    public boolean checkPlayer(UUID uuid);
    
    public void addPlayer(UUID uuid);
    
    public void removePlayer(UUID uuid);
    
    public Server getServer();
}