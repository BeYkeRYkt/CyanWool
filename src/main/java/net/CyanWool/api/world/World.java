package net.CyanWool.api.world;

import java.util.List;

import net.CyanWool.api.Difficulty;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.io.PlayerIOService;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.api.world.chunks.ChunkManager;

public interface World {

    public Dimension getDimension();

    public String getName();

    public Block getBlock(Location location);

    public Chunk getChunkFromChunkCoords(int x, int z);

    public Chunk getChunkFromBlockCoords(int x, int z);

    public Location getSpawnLocation();

    public void setSpawnLocation(Location loc);

    public boolean isDaytime();

    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch);

    public void playSoundEffect(Location location, String sound, float volume, float pitch);

    //public void playEffect(Location location, Particle effect, float velocityOffset, int amount, int data);

    //public void playEffect(Location location, WorldEffect effect, WorldEffectData data);

    public void playRecord(String name, int x, int y, int z);

    public boolean spawnEntity(Entity entity);

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

    public int getActualHeight();

    public List<Entity> getEntities();

    public List<EntityLivingBase> getLivingEntities();

    public List<Player> getPlayers();

    public void onTick();

    public void dropItemStack(ItemStack item);

    public Difficulty getDifficulty();

    public Gamemode getDefaultGamemode();

    public boolean getGamerule(String rule);

    public void saveAll();

    public ChunkManager getChunkManager();

    public String getPath();

    public PlayerIOService getPlayerService();
}