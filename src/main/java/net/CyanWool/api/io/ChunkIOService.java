package net.CyanWool.api.io;

import net.CyanWool.api.world.chunks.Chunk;

public interface ChunkIOService {
    //TODO: bla-bla
    public Chunk read(int x, int z);
    
    public void save(int x, int z);
}