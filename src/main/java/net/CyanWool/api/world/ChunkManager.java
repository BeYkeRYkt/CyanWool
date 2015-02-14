package net.CyanWool.api.world;

import java.util.List;

public interface ChunkManager {

    public Chunk getChunk(int x, int z);

    public boolean isChunkLoaded(int x, int z);
    
    public boolean isChunkInUse(int x, int z);

    public boolean loadChunk(int x, int z, boolean generate);
    
    public void saveChunk(Chunk chunk);

    public void generateChunk(Chunk chunk);

    public List<Chunk> getLoadedChunks();

    public Chunk getChunkFromChunkCoords(int x, int z);

    public Chunk getChunkFromBlockCoords(int x, int z);

    public void saveChunks();
}