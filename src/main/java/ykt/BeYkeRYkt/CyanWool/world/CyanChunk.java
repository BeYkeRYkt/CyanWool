package ykt.BeYkeRYkt.CyanWool.world;

import java.util.ArrayList;
import java.util.List;

import org.spacehq.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;

import ykt.BeYkeRYkt.CyanWool.api.block.Block;
import ykt.BeYkeRYkt.CyanWool.api.entity.Entity;
import ykt.BeYkeRYkt.CyanWool.api.entity.Player;
import ykt.BeYkeRYkt.CyanWool.api.world.Chunk;
import ykt.BeYkeRYkt.CyanWool.api.world.Location;
import ykt.BeYkeRYkt.CyanWool.api.world.World;

public class CyanChunk implements Chunk{

    private World world;
    private int x;
    private int z;
    
    public static final int WIDTH = 16, DEPTH = 256, HEIGHT = 16;
    public static final int SIZE = WIDTH * HEIGHT * DEPTH;

    private final List<Entity> entities = new ArrayList<Entity>();
    
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
    public Block getBlock(Location loc) {
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
    public void updateChunk() {
        ServerChunkDataPacket packet = new ServerChunkDataPacket(x, z);//...
        //For all players in chunk
    }

    @Override
    public int getSkyLight(int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setSkyLight(int x, int y, int z, int skylight) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getBlockLight(int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setBlockLight(int x, int y, int z, int blocklight) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getType(int x, int z, int y) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setType(int x, int z, int y, int type) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getMaxHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void generate() {
        // TODO Auto-generated method stub
        
    }
    
}