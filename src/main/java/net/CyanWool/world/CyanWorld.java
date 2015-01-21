package net.CyanWool.world;

import java.util.List;

import net.CyanWool.CyanServer;
import net.CyanWool.api.Difficulty;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.Server;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Dimension;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.api.world.chunks.ChunkManager;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;
import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerPlaySoundPacket;
import org.spacehq.packetlib.packet.Packet;

public class CyanWorld implements World {

    //private WorldInfo info;
    private CyanServer server;
    private ChunkManager chunk;
    
    private String name;
    private Location spawn;
    private long dayTime;
    private long seed;
    private boolean thundering;
    private int thunderingTime;
    private boolean raining;
    private int rainingTime;

    public CyanWorld(CyanServer server,WorldInfo info) {
        this.server = server;
        //this.info = info;
        //this.chunk = new ChunkManager(world, service, generator);
        
        //from info
        this.name = info.name;
        Location spawn = new Location(this, info.spawnX, info.spawnY, info.spawnZ);
        this.spawn = spawn;
        this.dayTime = info.dayTime;
        this.seed = info.seed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Block getBlock(Location location) {
        return location.getChunk().getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Chunk getChunkFromChunkCoords(int x, int z) {
        return chunk.getChunk(x, z);
    }

    @Override
    public Chunk getChunkFromBlockCoords(int x, int z) {
        return chunk.getChunk(x >> 4, z >> 4);//? 
    }

    @Override
    public Location getSpawnLocation() {
        return spawn;
    }

    @Override
    public void setSpawnLocation(Location loc) {
        this.spawn = loc;
    }

    @Override
    public boolean isDaytime() {
        return false;
    }

    @Override
    public void playSoundAtEntity(Entity entity, GenericSound sound, float volume, float pitch) {
        this.playSoundEffect(entity.getLocation(), sound, volume, pitch);
    }

    @Override
    public void playSoundEffect(Location location, GenericSound sound, float volume, float pitch) {
        ServerPlaySoundPacket packet = new ServerPlaySoundPacket(sound, location.getX(), location.getY(), location.getZ(), volume, pitch);
        
        for(Player player: getPlayers()){
            player.getPlayerNetwork().sendPacket(packet);
        }
    }

    @Override
    public void playRecord(String name, int x, int y, int z) {
        //TODO
    }

    @Override
    public boolean spawnEntity(Entity entity) {
        for(Packet packet: entity.getSpawnPackets()){
            for(Player player: getPlayers()){
                player.getPlayerNetwork().sendPacket(packet);
                return true;
            }
        }
        return false;
    }


    @Override
    public void createExplosion(Entity entity, Location location, float strength, boolean isFlaming) {
        // TODO Auto-generated method stub

    }

    @Override
    public void createExplosion(Location location, float strength, boolean isFlaming) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getSeed() {
        return seed;
    }

    @Override
    public long getTotalWorldTime() {
        return 24000;
    }

    @Override
    public long getWorldTime() {
        return dayTime;
    }

    @Override
    public void setWorldTime(long time) {
        this.dayTime = time;
    }

    @Override
    public boolean isThundering() {
        return thundering;
    }

    @Override
    public void setThundering(boolean flag) {
        this.thundering = flag;
    }

    @Override
    public int getThunderTime() {
        return thunderingTime;
    }

    @Override
    public void setThunderTime(int time) {
        this.thunderingTime = time;
    }

    @Override
    public boolean isRaining() {
        return raining;
    }

    @Override
    public void setRaining(boolean rain) {
        this.raining = rain;
    }

    @Override
    public int getRainTime() {
        return rainingTime;
    }

    @Override
    public void setRainTime(int time) {
       this.rainingTime = time;
    }

    @Override
    public int getActualHeight() {
        return 256;
    }

    @Override
    public List<Entity> getEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EntityLivingBase> getLivingEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onTick() {
        for (Entity entity : getEntities()) {
            entity.onTick();
        }
    }

    @Override
    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSoundEffect(Location location, String sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playEffect(Location location, Particle effect, float velocityOffset, int amount, int data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playEffect(Location location, WorldEffect effect, WorldEffectData data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dropItemStack(ItemStack item) {
        // TODO Auto-generated method stub

    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public Dimension getDimension() {
        return null;
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Difficulty getDifficulty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Gamemode getDefaultGamemode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getGamerule(String rule) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void saveAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ChunkManager getChunkManager() {
        return chunk;
    }
}