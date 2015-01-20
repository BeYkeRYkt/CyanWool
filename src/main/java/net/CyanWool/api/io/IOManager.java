package net.CyanWool.api.io;

import net.CyanWool.api.io.services.ChunkIOService;
import net.CyanWool.api.io.services.EntityIOService;
import net.CyanWool.api.io.services.WorldIOService;

public interface IOManager {
    
    public ChunkIOService getChunkIO();
    
    public WorldIOService getWorldIOService();
    
    public EntityIOService getEntityIOService();
    
}