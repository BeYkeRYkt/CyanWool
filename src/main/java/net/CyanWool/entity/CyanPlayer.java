package net.CyanWool.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.CyanWool.CyanServer;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.Server;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.entity.meta.ClientSettings;
import net.CyanWool.api.network.PlayerNetwork;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.api.world.chunks.ChunkCoords;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.data.game.values.setting.SkinPart;
import org.spacehq.mc.protocol.packet.ingame.server.ServerChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import org.spacehq.packetlib.packet.Packet;

public class CyanPlayer extends CyanHuman implements Player {

    private CyanServer server;
    private PlayerNetwork connection;
    private Gamemode gameMode;
    private List<Entity> knowEntities;
    private List<ChunkCoords> chunks;
    private ClientSettings settings;

    public CyanPlayer(CyanServer server, GameProfile profile) {
        super(profile);
        this.server = server;
        this.gameMode = Gamemode.SURVIVAL;
        this.knowEntities = new ArrayList<Entity>();
        this.chunks = new ArrayList<ChunkCoords>();
        //updateChunks();
    }
    
    @Override
    public void load(){
        
    }
    
    @Override
    public void save(){
        
    }

    private void updateChunks() {
        List<ChunkCoords> prev = new ArrayList<ChunkCoords>(chunks);
        List<ChunkCoords> newChunks = new ArrayList<ChunkCoords>();

        int centralX = getLocation().getBlockX() >> 4;
        int centralZ = getLocation().getBlockZ() >> 4;

        for (int x = (centralX - getSettings().getViewDistance()); x <= (centralX + getSettings().getViewDistance()); x++) {
            for (int z = (centralZ - getSettings().getViewDistance()); z <= (centralZ + getSettings().getViewDistance()); z++) {
                ChunkCoords key = new ChunkCoords(x, z);
                if (chunks.contains(key)) {
                    prev.remove(key);
                } else {
                    newChunks.add(key);
                }
                
                
                if (newChunks.size() == 0 && prev.size() == 0) {
                    return;
                }
                
                Collections.sort(newChunks, new Comparator<ChunkCoords>() {
                    @Override
                    public int compare(ChunkCoords a, ChunkCoords b) {
                    double dx = 16 * a.getX() + 8 - getLocation().getX();
                    double dz = 16 * a.getZ() + 8 - getLocation().getZ();
                    double da = dx * dx + dz * dz;
                    dx = 16 * b.getX() + 8 - getLocation().getX();
                    dz = 16 * b.getZ() + 8 - getLocation().getZ();
                    double db = dx * dx + dz * dz;
                    return Double.compare(da, db);
                    }
                    });
            }
        }
        
        for(ChunkCoords chunkcoords: newChunks){
            chunks.add(chunkcoords);
            Chunk chunk = getWorld().getChunkManager().getChunk(chunkcoords.getX(), chunkcoords.getZ());
            //chunk.updateChunk(this);
            //TODO: Sending
        }
        
            //Maybe: TODO: ServerMultiChunkDataPacket
        
        for (ChunkCoords key : prev) {
            ServerChunkDataPacket packet = new ServerChunkDataPacket(key.getX(), key.getZ());
            getPlayerNetwork().sendPacket(packet);
            chunks.remove(key);
        }
        prev.clear();
    }

    @Override
    public PlayerNetwork getPlayerNetwork() {
        return connection;
    }

    public void setPlayerNetwork(PlayerNetwork network) {
        this.connection = network;
    }

    @Override
    public void kickPlayer(String message) {
        getPlayerNetwork().disconnect(message);
    }

    @Override
    public UUID getUniqueId() {
        return getGameProfile().getId();
    }

    @Override
    public boolean isOp() {
        return false;
    }

    @Override
    public void setOp(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void executeCommand(String commandName) {
        getServer().getCommandManager().dispatchCommand(this, "/" + commandName);
    }

    @Override
    public void chat(String message) {
        String format = getDisplayName() + ": " + message; // TODO Event's
        ServerChatPacket packet = new ServerChatPacket(format);
        getPlayerNetwork().sendPacket(packet);
        
        server.getConsoleCommandSender().sendMessage(format);
    }

    @Override
    public boolean isBanned() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setBanned(boolean banned) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isWhitelisted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setWhitelisted(boolean whitelisted) {
        // TODO Auto-generated method stub

    }

    // @Override
    // public void displayGUI(InventoryType type) {
    // TODO Auto-generated method stub

    // }

    @Override
    public Gamemode getGameMode() {
        return gameMode;
    }

    @Override
    public void setGamemode(Gamemode mode) {
        this.gameMode = mode;
    }

    // @Override
    // public PlayerInventory getInventory() {
    // TODO Auto-generated method stub
    // return null;
    // }

    @Override
    public void onTick() {
        // Chunks
        updateChunks();

        // Entities
        List<Entity> list = new ArrayList<Entity>();
        List<Integer> destroy = new ArrayList<Integer>();

        // Collect visible entities...
        Iterator<Entity> it = knowEntities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            // if(SeeEntity(e))
            if (!e.isInvisible()) {
                list.add(e);
            } else {
                destroy.add(e.getEntityID());
                it.remove();
            }
        }

        // Send destroy packets
        if (!destroy.isEmpty()) {
            for (int id : destroy) {
                getPlayerNetwork().sendPacket(new ServerDestroyEntitiesPacket(id));
            }
        }

        // Send update packets
        for (Entity entity : list) {
            for (Packet packet : entity.getUpdatePackets()) {
                getPlayerNetwork().sendPacket(packet);
            }
        }

        // Add know entities
        for (Entity entity : getWorld().getEntities()) {
            // if(SeeEntity(entity))
            if (!knowEntities.contains(entity)) {
                knowEntities.add(entity);
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        ServerChatPacket packet = new ServerChatPacket(message);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public List<ChunkCoords> getChunks() {
        return chunks;
    }

    @Override
    public List<SkinPart> getVisibleParts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClientSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(ClientSettings settings) {
        this.settings = settings;
    }
}