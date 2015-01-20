package net.CyanWool.world;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.chunks.Chunk;

import org.spacehq.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;

public class CyanChunk implements Chunk{

    private org.spacehq.mc.protocol.data.game.Chunk[] protocolChunk; //???
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
        return null;
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
    public void updateChunk(Player player) {
        ServerChunkDataPacket packet = new ServerChunkDataPacket(x, z, protocolChunk);//...
        player.getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        return protocolChunk[0].getSkyLight().get(x, y, z);
    }

    @Override
    public void setSkyLight(int x, int y, int z, int skylight) {
        protocolChunk[0].getSkyLight().set(x, y, z, skylight);
    }

    @Override
    public int getBlockLight(int x, int y, int z) {
        return protocolChunk[0].getBlockLight().get(x, y, z);
    }

    @Override
    public void setBlockLight(int x, int y, int z, int blocklight) {
        protocolChunk[0].getBlockLight().set(x, y, z, blocklight);
    }

    @Override
    public int getType(int x, int z, int y) {
        return protocolChunk[0].getBlocks().getBlock(x, y, z);
    }

    @Override
    public void setType(int x, int z, int y, int type) {
        protocolChunk[0].getBlocks().setBlock(x, y, z, type);
    }

    @Override
    public void setBlock(int x, int y, int z, BlockType type) {
        protocolChunk[0].getBlocks().setBlockAndData(x, y, z, type.getID(), type.getData());
    }

    @Override
    public void loadChunk() {
        // TODO Auto-generated method stub
        this.protocolChunk[0] = new org.spacehq.mc.protocol.data.game.Chunk(true);
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
    
}