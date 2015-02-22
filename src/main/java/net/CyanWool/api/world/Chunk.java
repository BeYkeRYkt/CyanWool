package net.CyanWool.api.world;

import java.util.List;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.player.Player;

public interface Chunk {

    public int getX();

    public int getZ();

    public Block getBlock(int x, int y, int z);

    public void setBlock(int x, int y, int z, BlockType type);

    public void loadChunk();

    public void unloadChunk();

    public World getWorld();

    public List<Entity> getEntities();

    public List<Player> getPlayers();

    public int getSkyLight(int x, int y, int z);

    public void setSkyLight(int x, int y, int z, int skylight);

    public int getBlockLight(int x, int y, int z);

    public void setBlockLight(int x, int y, int z, int blocklight);

    public void setBlock(int x, int y, int z, int type);

    public void setBlock(int x, int y, int z, int type, int data);

    public int getMaxHeight();

    // public void generate();

    public boolean isLoaded();

    public void setLoaded(boolean b);

    public byte[] getBiomeData();

    public void setNeedGenerate(boolean flag);

    public boolean isNeedGenerate();

    public boolean isLocked();

    public void setLocked(boolean flag);

    public ChunkCoords getChunkCoords();

    public int getUsedPlayers();
}