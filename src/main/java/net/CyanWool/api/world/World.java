package net.CyanWool.api.world;

import java.util.List;

import net.CyanWool.api.Gamemode;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.EntityManager;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.objects.Arrow;
import net.CyanWool.api.entity.objects.Egg;
import net.CyanWool.api.entity.objects.Explosion;
import net.CyanWool.api.entity.objects.FallingBlock;
import net.CyanWool.api.entity.objects.Item;
import net.CyanWool.api.entity.objects.PrimedTNT;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.io.PlayerIOService;
import net.CyanWool.api.utils.Vector;

public interface World {

    public Dimension getDimension();

    public String getName();

    public Block getBlock(Location location);

    public Block getBlock(int x, int y, int z);

    public void setBlock(Location location, int id, int data);

    public void setBlock(int x, int y, int z, int id, int data);

    public void setBlock(Location location, BlockType type);

    public void setBlock(int x, int y, int z, BlockType type);

    public Location getSpawnLocation();

    public void setSpawnLocation(Location loc);

    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch);

    public void playSound(Location location, String sound, float volume, float pitch);

    public void playSoundExpect(Location location, String sound, float volume, float pitch, Player player);

    public void playSoundAtEntity(Entity entity, Sound sound, float volume, float pitch);

    public void playSound(Location location, Sound sound, float volume, float pitch);

    public void playEffect(Location location, Effect effect, int data);

    public void playSoundExpect(Location location, Sound sound, float volume, float pitch, Player player);

    public void playEffectExpect(Location location, Effect effect, int data, Player player);

    public Entity spawnEntity(EntityType type);

    public Explosion createExplosion(Entity entity, Location location, float strength, boolean isFlaming);

    public Explosion createExplosion(Location location, float strength, boolean isFlaming);

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

    public Item dropItemStack(Location location, ItemStack item);

    public Difficulty getDifficulty();

    public void setDifficulty(Difficulty diff);

    public Gamemode getDefaultGamemode();

    public void setDefaultGamemode(Gamemode mode);

    public boolean getGamerule(String rule);

    public void saveAll();

    public String getPath();

    public PlayerIOService getPlayerService();

    public ChunkManager getChunkManager();

    public WorldGenerator getGenerator();

    public void setGenerator(WorldGenerator generator);

    public boolean isAutoSave();

    public void setAutoSave(boolean value);

    public FallingBlock spawnFallingBlock(Location location, BlockType type);

    public FallingBlock spawnFallingBlock(Location location, int id, int data);

    public PrimedTNT spawnPrimedTNT(int fuseTicks, int power);

    public Arrow shootArrow(Vector vector);// ???

    public Egg shootEgg(Vector vector);

    public EntityManager getEntityManager();

}