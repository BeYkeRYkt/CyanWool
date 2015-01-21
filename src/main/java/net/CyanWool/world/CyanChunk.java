package net.CyanWool.world;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.Register;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.block.CyanBlock;

public class CyanChunk implements Chunk{

    private org.spacehq.mc.protocol.data.game.Chunk protocolChunk; //???
    private World world;
    private int x;
    private int z;
    
    public static final int WIDTH = 16, DEPTH = 256, HEIGHT = 16;
    public static final int SIZE = WIDTH * HEIGHT * DEPTH;

    private final List<Entity> entities = new ArrayList<Entity>();
    private boolean isLoaded;
    
    public CyanChunk(World world, int x, int z) {
        this.world = world;
        this.x = x;
        this.z = z;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        int id = protocolChunk.getBlocks().getBlock(x, y, z);
        int data = protocolChunk.getBlocks().getData(x, y, z);
        BlockType type = Register.getBlock(id, data);
        Location location = new Location(world, x, y, z);
        CyanBlock block = new CyanBlock(location, type);
        return block;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for(Entity entity: entities){
            if(entity instanceof Player){//Other checker...
                Player player = (Player) entity;
                players.add(player);
            }
        }
        
        return players;
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        return protocolChunk.getSkyLight().get(x, y, z);
    }

    @Override
    public void setSkyLight(int x, int y, int z, int skylight) {
        protocolChunk.getSkyLight().set(x, y, z, skylight);
    }

    @Override
    public int getBlockLight(int x, int y, int z) {
        return protocolChunk.getBlockLight().get(x, y, z);
    }

    @Override
    public void setBlockLight(int x, int y, int z, int blocklight) {
        protocolChunk.getBlockLight().set(x, y, z, blocklight);
    }

    @Override
    public int getType(int x, int z, int y) {
        return protocolChunk.getBlocks().getBlock(x, y, z);
    }

    @Override
    public void setType(int x, int z, int y, int type) {
        protocolChunk.getBlocks().setBlock(x, y, z, type);
    }

    @Override
    public void setBlock(int x, int y, int z, BlockType type) {
        protocolChunk.getBlocks().setBlockAndData(x, y, z, type.getID(), type.getData());
    }

    @Override
    public void loadChunk() {
        // TODO Auto-generated method stub
        this.protocolChunk = new org.spacehq.mc.protocol.data.game.Chunk(true);
        //todo
    }

    @Override
    public void unloadChunk() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }
    
    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        } else if (!(other instanceof CyanChunk)) {
            return false;
        } else {
            CyanChunk chunk = (CyanChunk) other;
            return this.getX() == chunk.getX() && this.getZ() == chunk.getZ();
        }
    }
}