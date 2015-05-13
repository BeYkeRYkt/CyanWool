package net.devwool.cyanwool.api.world;

import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.world.chunk.ChunkManager;

public interface World {

    public Block getBlock(Position position);

    public Block getBlock(int x, int y, int z);

    public ChunkManager getChunkManager();

}