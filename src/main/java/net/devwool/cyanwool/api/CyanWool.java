package net.devwool.cyanwool.api;

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
    
    
    
}