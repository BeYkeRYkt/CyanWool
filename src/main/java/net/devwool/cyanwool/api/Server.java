package net.devwool.cyanwool.api;

public interface Server {
    
    public String getModName();
    
    public String getMCVersion();
    
    public void broadcastMessage(String string);
}