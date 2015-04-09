package net.CyanWool.world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Server;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.block.BlockType;
import net.CyanWool.api.block.entity.TileEntity;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
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
import net.CyanWool.api.world.ChunkManager;
import net.CyanWool.api.world.Dimension;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.WorldGenerator;
import net.CyanWool.io.CyanChunkIOService;

import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.Sound;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;

public class CyanWorld implements World {

    // private WorldInfo info;
    private ChunkManager chunk;
    private WorldGenerator generator;

    private String name;
    private Location spawn;
    private long dayTime;
    private long seed;
    private boolean thundering;
    private int thunderingTime;
    private boolean raining;
    private int rainingTime;
    private PlayerIOService service;
    private String path;
    private Dimension dimension;
    private Difficulty difficulty;
    private GameMode defaultGamemode;
    private boolean autoSave;
    private boolean isHardcore;

    private boolean spawnLoaded;
    private List<Entity> entities;

    public CyanWorld(String name, PlayerIOService service) {
        this.name = name;
        this.service = service;
        this.path = "worlds/" + name;
        setGenerator(new FlatWorldGenerator(this)); // TEST
        this.chunk = new CyanChunkManager(this, new CyanChunkIOService(this));
        this.entities = new CopyOnWriteArrayList<Entity>();
        // this.chunk.loadChunk(getSpawnLocation().getBlockX() >> 4,
        // getSpawnLocation().getBlockZ() >> 4, false);
        this.dimension = Dimension.OVERWORLD;// TEST
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
    public Location getSpawnLocation() {
        return spawn;
    }

    @Override
    public void setSpawnLocation(Location loc) {
        this.spawn = loc;
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
        return entities;// TODO:FIX
    }

    @Override
    public List<EntityLivingBase> getLivingEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<Player>();
        for (Entity entity : getEntities()) {
            if (entity.getEntityType() == EntityType.PLAYER) {
                list.add((Player) entity);
            }
        }
        return list;
    }

    @Override
    public void onTick() {
        chunk.onTick();

        // Iterator<Entity> it = getEntities().iterator();
        // while (it.hasNext()) {
        // Entity entity = it.next();
        // entity.onTick();
        // }
        for (Entity entity : getEntities()) {
            entity.onTick();
        }

        dayTime++;
        if (dayTime == 24000) {
            dayTime = 0;
        }
        for (Player player : getPlayers()) {
            player.setTime(dayTime);
        }
    }

    @Override
    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch) {
        this.playSound(entity.getLocation(), sound, volume, pitch);
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public GameMode getDefaultGamemode() {
        return defaultGamemode;
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

    @Override
    public PlayerIOService getPlayerService() {
        return service;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        for (Player player : getPlayers()) {
            player.playSound(location, sound, volume, pitch);
        }
    }

    @Override
    public void playSoundAtEntity(Entity entity, Sound sound, float volume, float pitch) {
        for (Player player : getPlayers()) {
            player.playSound(entity.getLocation(), sound, volume, pitch);
        }
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        for (Player player : getPlayers()) {
            player.playSound(location, sound, volume, pitch);
        }
    }

    @Override
    public void playEffect(Location location, WorldEffect effect, WorldEffectData data) {
        for (Player player : getPlayers()) {
            player.playEffect(location, effect, data);
        }
    }

    @Override
    public WorldGenerator getGenerator() {
        return generator;
    }

    @Override
    public void setGenerator(WorldGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        Location location = new Location(this, x, y, z);
        return location.getBlock();
    }

    @Override
    public void setBlock(Location location, int id, int data) {
        this.setBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ(), id, data);
    }

    @Override
    public void setBlock(int x, int y, int z, int id, int data) {
        getChunkManager().getChunkFromBlockCoords(x, z).setBlock(x, y, z, id, data);
    }

    @Override
    public void setBlock(Location location, BlockType type) {
        this.setBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ(), type);
    }

    @Override
    public void setBlock(int x, int y, int z, BlockType type) {
        getChunkManager().getChunkFromBlockCoords(x, z).setBlock(x, y, z, type);
    }

    @Override
    public void playSoundExpect(Location location, String sound, float volume, float pitch, Player player) {
        for (Player players : getPlayers()) {
            if (players.getEntityID() != player.getEntityID()) {
                players.playSound(location, sound, volume, pitch);
            }
        }
    }

    @Override
    public void playSoundExpect(Location location, Sound sound, float volume, float pitch, Player player) {
        for (Player players : getPlayers()) {
            if (players.getEntityID() != player.getEntityID()) {
                players.playSound(location, sound, volume, pitch);
            }
        }
    }

    @Override
    public void playEffectExpect(Location location, WorldEffect effect, WorldEffectData data, Player player) {
        for (Player players : getPlayers()) {
            if (players.getEntityID() != player.getEntityID()) {
                players.playEffect(location, effect, data);
            }
        }
    }

    @Override
    public Entity spawnEntity(EntityType type) {
        Entity entity = null;
        // if(type == EntityType.BAT){
        // TODO: spawn entities

        return entity;
    }

    @Override
    public Explosion createExplosion(Entity entity, Location location, float strength, boolean isFlaming) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Explosion createExplosion(Location location, float strength, boolean isFlaming) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item dropItemStack(Location location, ItemStack item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDifficulty(Difficulty diff) {
        this.difficulty = diff;
    }

    @Override
    public void setDefaultGamemode(GameMode mode) {
        this.defaultGamemode = mode;
    }

    @Override
    public boolean isAutoSave() {
        return autoSave;
    }

    @Override
    public void setAutoSave(boolean value) {
        this.autoSave = value;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, BlockType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, int id, int data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PrimedTNT spawnPrimedTNT(int fuseTicks, int power) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Arrow shootArrow(Vector vector) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Egg shootEgg(Vector vector) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) (seed ^ (seed >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CyanWorld)) {
            return false;
        }
        CyanWorld other = (CyanWorld) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (seed != other.seed) {
            return false;
        }
        return true;
    }

    @Override
    public void playParticle(Location location, Particle particle, int amount, int data) {
        for (Player players : getPlayers()) {
            players.playParticle(location, particle, amount, data);
        }
    }

    @Override
    public void playParticleExpect(Location location, Particle particle, int amount, int data, Player player) {
        for (Player players : getPlayers()) {
            if (!players.equals(player)) {
                players.playParticle(location, particle, amount, data);
            }
        }
    }

    @Override
    public TileEntity getTileEntity(int x, int y, int z) {
        return null;
    }

    @Override
    public TileEntity getTileEntity(Location location) {
        return getTileEntity(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Server getServer() {
        return CyanWool.getServer();
    }

    @Override
    public void loadSpawnChunks() {
        if (!spawnLoaded) {
            int centerX = getSpawnLocation().getBlockX() >> 4;
            int centerZ = getSpawnLocation().getBlockZ() >> 4;
            int radius = 4 * 8 / 3;
            long loadTime = System.currentTimeMillis();
            int total = (radius * 2 + 1) * (radius * 2 + 1), current = 0;
            for (int x = centerX - radius; x <= centerX + radius; ++x) {
                for (int z = centerZ - radius; z <= centerZ + radius; ++z) {
                    ++current;
                    getChunkManager().loadChunk(x, z);
                    getChunkManager().getChunk(x, z).setLocked(true);
                    // spawnChunkLock.acquire(new GlowChunk.Key(x, z));
                    if (System.currentTimeMillis() >= loadTime + 1000) {
                        int progress = 100 * current / total;
                        CyanWool.getLogger().info("Preparing spawn for " + getName() + ": " + progress + "%");
                        loadTime = System.currentTimeMillis();
                    }
                }
            }
            spawnLoaded = true;
        }
    }

    @Override
    public boolean isLoadedSpawnChunks() {
        return spawnLoaded;
    }

    @Override
    public boolean isHardcore() {
        return isHardcore;
    }

    @Override
    public void setHardcore(boolean flag) {
        this.isHardcore = flag;
    }
}