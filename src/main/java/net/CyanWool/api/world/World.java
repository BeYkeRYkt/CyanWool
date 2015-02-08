package net.CyanWool.api.world;

import java.util.List;

import net.CyanWool.api.Difficulty;
import net.CyanWool.api.Effect;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.Sound;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.io.PlayerIOService;
import net.CyanWool.api.world.chunks.ChunkManager;

public interface World {

    public Dimension getDimension();

    public String getName();

    public Block getBlock(Location location);

    public Location getSpawnLocation();

    public void setSpawnLocation(Location loc);

    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch);

    public void playSound(Location location, String sound, float volume, float pitch);

    public void playSoundAtEntity(Entity entity, Sound sound, float volume, float pitch);

    public void playSound(Location location, Sound sound, float volume, float pitch);

    public void playEffect(Location location, Effect effect, int data);

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

    public void dropItemStack(Location location, ItemStack item);

    public Difficulty getDifficulty();

    public Gamemode getDefaultGamemode();

    public boolean getGamerule(String rule);

    public void saveAll();

    public String getPath();

    public PlayerIOService getPlayerService();

    public ChunkManager getChunkManager();
}