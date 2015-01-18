package ykt.BeYkeRYkt.CyanWool.world;

import java.util.List;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;
import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;
import org.spacehq.opennbt.tag.builtin.CompoundTag;

import ykt.BeYkeRYkt.CyanWool.CyanServer;
import ykt.BeYkeRYkt.CyanWool.api.Difficulty;
import ykt.BeYkeRYkt.CyanWool.api.Gamemode;
import ykt.BeYkeRYkt.CyanWool.api.Server;
import ykt.BeYkeRYkt.CyanWool.api.block.Block;
import ykt.BeYkeRYkt.CyanWool.api.entity.Entity;
import ykt.BeYkeRYkt.CyanWool.api.entity.EntityLivingBase;
import ykt.BeYkeRYkt.CyanWool.api.entity.Player;
import ykt.BeYkeRYkt.CyanWool.api.inventory.ItemStack;
import ykt.BeYkeRYkt.CyanWool.api.world.Chunk;
import ykt.BeYkeRYkt.CyanWool.api.world.Dimension;
import ykt.BeYkeRYkt.CyanWool.api.world.Location;
import ykt.BeYkeRYkt.CyanWool.api.world.World;

public class CyanWorld implements World {

    private WorldInfo info;
    private CyanServer server;

    public CyanWorld(WorldInfo info) {
        this.info = info;
    }

    public void loadNBT(CompoundTag tag) {
        // from http://minecraft.gamepedia.com/Level.dat#level.dat_format
        CompoundTag data = tag.get("Data");
    }

    public void saveNBT(CompoundTag tag) {
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Block getBlock(Location location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chunk getChunkFromChunkCoords(int x, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chunk getChunkFromBlockCoords(int x, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Location getSpawnLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSpawnLocation(Location loc) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDaytime() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void playSoundAtEntity(Entity entity, GenericSound sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSoundEffect(Location location, GenericSound sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playRecord(String name, int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean spawnEntity(Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeEntity(Entity entity) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getTotalWorldTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getWorldTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldTime(long time) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isThundering() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setThundering(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getThunderTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setThunderTime(int time) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isRaining() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRaining(boolean rain) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRainTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRainTime(int time) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getActualHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Entity[] getEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntityLivingBase[] getLivingEntities() {
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Dimension getDimension() {
        // TODO Auto-generated method stub
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
}