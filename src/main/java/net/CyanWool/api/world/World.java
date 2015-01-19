package net.CyanWool.api.world;

import java.util.List;

import net.CyanWool.api.Difficulty;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.Server;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.chunks.Chunk;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;
import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;

public interface World {

    public Dimension getDimension();

    public String getName();

    public Block getBlock(Location location);

    public Chunk getChunkFromChunkCoords(int x, int z);

    public Chunk getChunkFromBlockCoords(int x, int z);

    public Location getSpawnLocation();

    public void setSpawnLocation(Location loc);

    public boolean isDaytime();

    public void playSoundAtEntity(Entity entity, GenericSound sound, float volume, float pitch);

    public void playSoundEffect(Location location, GenericSound sound, float volume, float pitch);

    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch);

    public void playSoundEffect(Location location, String sound, float volume, float pitch);

    public void playEffect(Location location, Particle effect, float velocityOffset, int amount, int data);

    public void playEffect(Location location, WorldEffect effect, WorldEffectData data);

    public void playRecord(String name, int x, int y, int z);

    public boolean spawnEntity(Entity entity);

    public void removeEntity(Entity entity);

    public void createExplosion(Entity entity, Location location, float strength, boolean isFlaming);

    public void createExplosion(Location location, float strength, boolean isFlaming);

    public long getSeed();

    public long getTotalWorldTime();

    public long getWorldTime();

    public void setWorldTime(long time);

    public boolean isThundering();

    public void setThundering(boolean flag);

    public int getThunderTime();

    public void setThunderTime(int time);

    public boolean isRaining();

    public void setRaining(boolean rain);

    public int getRainTime();

    public void setRainTime(int time);

    public int getHeight();

    public int getActualHeight();

    public Entity[] getEntities();

    public EntityLivingBase[] getLivingEntities();

    public List<Player> getPlayers();

    public void onTick();

    public void dropItemStack(ItemStack item);

    public Server getServer();
    
    public Chunk generateChunk(int x, int z);
    
    public Difficulty getDifficulty();
    
    public Gamemode getDefaultGamemode();

    public boolean getGamerule(String rule);
    
    public void saveAll();
}