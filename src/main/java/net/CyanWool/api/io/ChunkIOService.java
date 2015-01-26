package net.CyanWool.api.io;

import net.CyanWool.api.world.chunks.Chunk;

public interface ChunkIOService {

    public Chunk readChunk(int x, int z);

    public void saveChunk(Chunk chunk);
}