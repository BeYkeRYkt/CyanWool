package net.CyanWool.io;

import net.CyanWool.api.io.IOManager;
import net.CyanWool.api.io.services.ChunkIOService;
import net.CyanWool.api.io.services.EntityIOService;
import net.CyanWool.api.io.services.WorldIOService;
import net.CyanWool.io.nbt.CyanChunkIOService;
import net.CyanWool.io.nbt.CyanEntityIOService;
import net.CyanWool.io.nbt.CyanWorldIOService;

public class CyanIOManager implements IOManager{

    private ChunkIOService chunk;
    private WorldIOService world;
    private EntityIOService entity;
    
    public CyanIOManager() {
        this.chunk = new CyanChunkIOService();
        this.world = new CyanWorldIOService();
        this.entity = new CyanEntityIOService();
    }
    
    @Override
    public ChunkIOService getChunkIO() {
        return chunk;
    }

    @Override
    public WorldIOService getWorldIOService() {
        return world;
    }

    @Override
    public EntityIOService getEntityIOService() {
        return entity;
    }
    
}