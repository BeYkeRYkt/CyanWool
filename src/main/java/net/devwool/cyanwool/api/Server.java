package net.devwool.cyanwool.api;

import net.devwool.cyanwool.api.management.OperatorsManager;
import net.devwool.cyanwool.api.management.PlayerManager;
import net.devwool.cyanwool.api.management.WhitelistManager;

import org.apache.logging.log4j.Logger;

public interface Server {
    
    public String getModName();
    
    public String getMCVersion();
    
    public void broadcastMessage(String string);
    
    public Logger getLogger();
    
    public WhitelistManager getWhitelistManager();
    
    public OperatorsManager getOperatorsManager();
    
    public PlayerManager getPlayerManager();
    
    
}