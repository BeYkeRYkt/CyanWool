package net.CyanWool.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.CyanWool.CyanServer;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.Server;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.network.PlayerNetwork;
import net.CyanWool.api.world.Location;
import net.CyanWool.network.CyanPlayerNetwork;

import org.spacehq.mc.protocol.packet.ingame.server.ServerChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.packet.Packet;

public class CyanPlayer extends CyanHuman implements Player {

    private CyanServer server;
    private PlayerNetwork connection;
    private Gamemode gameMode;
    private List<Entity> knowEntities;

    public CyanPlayer(CyanServer server, Session session, Location location) {
        super(location);
        this.server = server;
        this.connection = new CyanPlayerNetwork(server, session, this);
        this.knowEntities = new ArrayList<Entity>();
        loadChunks();
    }

    public void loadChunks() {
        // load chunks
    }

    public PlayerNetwork getPlayerNetwork() {
        return connection;
    }

    @Override
    public void kickPlayer(String message) {

    }

    @Override
    public UUID getUniqueId() {
        // TODO Auto-generated method stub
        return null;
    }

    public void loadData() {
        // TODO Auto-generated method stub

    }

    public void saveData() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isOp() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setOp(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void executeCommand(String commandName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void chat(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPing() {
        // TODO Auto-generated method stub
        return 0;
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
}