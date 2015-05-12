package net.devwool.cyanwool.api;

import net.devwool.cyanwool.api.management.OperatorsManager;
import net.devwool.cyanwool.api.management.PlayerManager;
import net.devwool.cyanwool.api.management.WhitelistManager;

import org.apache.logging.log4j.Logger;

public class CyanWool {
    
    private static Server server;
    
    public static void initServer(Server init){
        if(getServer() != null){
            return;
        }
        server = init;
    }
    
    public static Server getServer() {
        return server;
    }
    
    public static String getModName(){
        return getServer().getModName();
    }
    
    public static Logger getLogger(){
        return getServer().getLogger();
    }
    
    public WhitelistManager getWhitelistManager(){
        return getServer().getWhitelistManager();
    }
    
    public OperatorsManager getOperatorsManager(){
        return getServer().getOperatorsManager();
    }
    
    public PlayerManager getPlayerManager(){
        return getServer().getPlayerManager();
    }
    
}