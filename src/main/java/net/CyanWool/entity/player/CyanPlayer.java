package net.CyanWool.entity.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.CyanWool.CyanServer;
import net.CyanWool.Transform;
import net.CyanWool.api.Effect;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.Server;
import net.CyanWool.api.Sound;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.api.world.chunks.ChunkCoords;
import net.CyanWool.entity.CyanEntity;
import net.CyanWool.entity.meta.ClientSettings;
import net.CyanWool.network.PlayerNetwork;
import net.CyanWool.world.CyanChunk;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.data.game.Position;
import org.spacehq.mc.protocol.data.game.values.world.CustomSound;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;
import org.spacehq.mc.protocol.packet.ingame.server.ServerChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerPlayEffectPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerPlaySoundPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerUpdateTimePacket;
import org.spacehq.packetlib.packet.Packet;

public class CyanPlayer extends CyanHuman implements Player{

    private CyanServer server;
    private PlayerNetwork connection;
    private List<Entity> knowEntities;
    private List<ChunkCoords> chunks;
    private ClientSettings settings;

    public CyanPlayer(CyanServer server, GameProfile profile, Location location) {
        super(profile, location);
        this.server = server;
        setGamemode(Gamemode.SURVIVAL);
        this.knowEntities = new ArrayList<Entity>();
        this.chunks = new ArrayList<ChunkCoords>();
        setSettings(ClientSettings.getDEFAULT());
        // updateChunks();
    }

    public void updateChunks() {
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

        for (ChunkCoords chunkcoords : newChunks) {
            chunks.add(chunkcoords);
            Chunk chunk = getWorld().getChunkManager().getChunk(chunkcoords.getX(), chunkcoords.getZ());
            sendChunk(chunk);
        }

        // Maybe: TODO: ServerMultiChunkDataPacket

        for (ChunkCoords key : prev) {
            ServerChunkDataPacket packet = new ServerChunkDataPacket(key.getX(), key.getZ());
            getPlayerNetwork().sendPacket(packet);
            chunks.remove(key);
        }
        prev.clear();
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
                destroy.add(e.getRegisterID());
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
            for (Packet packet : ((CyanEntity) entity).getUpdatePackets()) {
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
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        // ServerPlaySoundPacket packet = new
        // ServerPlaySoundPacket(MagicValues.key(GenericSound.class, sound),
        // getLocation().getX(), getLocation().getY(), getLocation().getZ(),
        // volume, pitch);
        CustomSound sound1 = new CustomSound(sound);
        ServerPlaySoundPacket packet = new ServerPlaySoundPacket(sound1, getLocation().getX(), getLocation().getY(), getLocation().getZ(), volume, pitch);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        org.spacehq.mc.protocol.data.game.values.world.Sound libSound = Transform.convertLibSound(sound);
        ServerPlaySoundPacket packet = new ServerPlaySoundPacket(libSound, getLocation().getX(), getLocation().getY(), getLocation().getZ(), volume, pitch);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void playEffect(Location location, Effect effect, int data) {
        WorldEffect libEffect = Transform.convertWorldEffect(effect);
        WorldEffectData libEffectData = Transform.convertWorldEffectData(effect, data);
        Position pos = new Position(getLocation().getBlockX(), getLocation().getBlockY(), getLocation().getBlockZ());
        ServerPlayEffectPacket packet = new ServerPlayEffectPacket(libEffect, pos, libEffectData);
        getPlayerNetwork().sendPacket(packet);
    }
    
    @Override
    public void setTime(long time) {
        ServerUpdateTimePacket packet = new ServerUpdateTimePacket(getAge(), time);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void sendChunk(Chunk chunk) {
        CyanChunk cchunk = (CyanChunk) chunk;
        ServerChunkDataPacket packet = new ServerChunkDataPacket(chunk.getX(), chunk.getZ(), cchunk.getSections());
        getPlayerNetwork().sendPacket(packet);
    }
    
    @Override
    public List<Packet> getSpawnPackets() {
        List<Packet> list = new ArrayList<Packet>();
        list.add(new ServerSpawnPlayerPacket(getRegisterID(), getUniqueId(), getLocation().getX(), getLocation().getY(), getLocation().getZ(), getLocation().getYaw(), getLocation().getPitch(), 0 , null)); // TODO
        return list;
    }

    // Not api
    public PlayerNetwork getPlayerNetwork() {
        return connection;
    }

    public void setPlayerNetwork(PlayerNetwork network) {
        this.connection = network;
    }

    public ClientSettings getSettings() {
        return settings;
    }

    public void setSettings(ClientSettings settings) {
        this.settings = settings;
    }
}