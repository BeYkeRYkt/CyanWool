package ykt.BeYkeRYkt.CyanWool.api.world;

import java.util.List;

import ykt.BeYkeRYkt.CyanWool.api.block.Block;
import ykt.BeYkeRYkt.CyanWool.api.entity.Entity;
import ykt.BeYkeRYkt.CyanWool.api.entity.Player;

public interface Chunk {

    public int getX();

    public int getZ();

    public Block getBlock(int x, int y, int z);

    public Block getBlock(Location loc);

    //public void loadChunk();

    //public void unloadChunk();

    public World getWorld();
    
    public List<Entity> getEntities();
    
    public List<Player> getPlayers();
    
    public void updateChunk();
    
    public int getSkyLight(int x, int y, int z);
    
    public void setSkyLight(int x, int y, int z, int skylight);
    
    public int getBlockLight(int x, int y, int z);
    
    public void setBlockLight(int x, int y, int z, int blocklight);
    
    public int getType(int x, int z, int y);
    
    public void setType(int x, int z, int y, int type);
    
    public int getMaxHeight();
    
    public void generate();
}