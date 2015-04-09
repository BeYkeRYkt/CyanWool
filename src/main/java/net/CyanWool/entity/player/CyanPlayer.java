package net.CyanWool.entity.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.CyanWool.CyanServer;
import net.CyanWool.Transform;
import net.CyanWool.api.Server;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.component.DisplayNameComponent;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.network.PlayerNetwork;
import net.CyanWool.api.world.Chunk;
import net.CyanWool.api.world.ChunkCoords;
import net.CyanWool.api.world.Location;
import net.CyanWool.entity.CyanEntity;
import net.CyanWool.entity.meta.ClientSettings;
import net.CyanWool.entity.meta.CyanMetadataMap;
import net.CyanWool.world.CyanChunk;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.data.game.EntityMetadata;
import org.spacehq.mc.protocol.data.game.Position;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.world.CustomSound;
import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.Sound;
import org.spacehq.mc.protocol.data.game.values.world.WorldType;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;
import org.spacehq.mc.protocol.data.game.values.world.notify.ClientNotification;
import org.spacehq.mc.protocol.packet.ingame.server.ServerChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerRespawnPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityHeadLookPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.player.ServerUpdateHealthPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerNotifyClientPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerPlayEffectPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerPlaySoundPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerSpawnParticlePacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerUpdateTimePacket;
import org.spacehq.opennbt.tag.builtin.ByteTag;
import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.FloatTag;
import org.spacehq.opennbt.tag.builtin.IntTag;
import org.spacehq.opennbt.tag.builtin.ShortTag;
import org.spacehq.packetlib.packet.Packet;

public class CyanPlayer extends CyanHuman implements Player {

    private CyanServer server;
    private PlayerNetwork connection;
    private List<Entity> knowEntities;
    private List<ChunkCoords> chunks;
    private ClientSettings settings;
    private CompoundTag compTag;

    public CyanPlayer(CyanServer server, GameProfile profile, Location location) {
        super(profile, location);
        super.setGamemode(GameMode.SURVIVAL);
        this.server = server;
        this.knowEntities = new ArrayList<Entity>();
        this.chunks = new ArrayList<ChunkCoords>();
        setSettings(ClientSettings.getDEFAULT());
        ((DisplayNameComponent) getComponentManager().getComponent("displayName")).setDisplayName(profile.getName());
        ((DisplayNameComponent) getComponentManager().getComponent("displayName")).setRenderDisplayName(true);
        // updateChunks();
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
        String name = "UNKNOWN";
        if (getComponentManager().hasComponent("displayName")) {
            name = ((DisplayNameComponent) getComponentManager().getComponent("displayName")).getDisplayName();
        }
        String format = name + ": " + message; // TODO Event's
        ServerChatPacket packet = new ServerChatPacket(format);
        // getPlayerNetwork().sendPacket(packet);
        for (Player player : getWorld().getPlayers()) {
            ((CyanPlayer) player).getPlayerNetwork().sendPacket(packet);
        }

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
        super.onTick();

        // Chunks
        updateChunks();
        updatePosition();

        // Send change metadata's
        if (((CyanMetadataMap) metadata).getChanges() != null) {
            EntityMetadata[] changes = ((CyanMetadataMap) metadata).getChanges();
            if (changes != null && changes.length > 0) {
                getPlayerNetwork().sendPacket(new ServerEntityMetadataPacket(getEntityID(), changes));
            }
        }

        // Entities
        List<Entity> list = new ArrayList<Entity>();
        List<Integer> destroy = new ArrayList<Integer>();

        // Collect visible entities...
        Iterator<Entity> it = knowEntities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            boolean canSee = e.isAlive() && canSeeEntity(e);
            if (canSee) {
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
            for (Packet packet : ((CyanEntity) entity).getUpdatePackets()) {
                getPlayerNetwork().sendPacket(packet);
            }
        }

        // Add know entities
        for (Entity entity : getWorld().getEntities()) {

            boolean canSee = entity.isAlive() && canSeeEntity(entity);
            if (canSee) {
                if (!knowEntities.contains(entity)) {
                    knowEntities.add(entity);
                    // sending...
                    for (Packet packet : ((CyanEntity) entity).getSpawnPackets()) {
                        getPlayerNetwork().sendPacket(packet);
                    }
                }
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
        ServerPlaySoundPacket packet = new ServerPlaySoundPacket(sound1, location.getX(), location.getY(), location.getZ(), volume, pitch);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        ServerPlaySoundPacket packet = new ServerPlaySoundPacket(sound, location.getX(), location.getY(), location.getZ(), volume, pitch);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void playEffect(Location location, WorldEffect effect, WorldEffectData data) {
        Position pos = new Position(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        ServerPlayEffectPacket packet = new ServerPlayEffectPacket(effect, pos, data);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void setTime(long time) {
        ServerUpdateTimePacket packet = new ServerUpdateTimePacket(getWorld().getWorldTime(), time);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void sendChunk(Chunk chunk) {
        CyanChunk cchunk = (CyanChunk) chunk;
        ServerChunkDataPacket packet = new ServerChunkDataPacket(chunk.getX(), chunk.getZ(), cchunk.getSections(), chunk.getBiomeData());
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public List<Packet> getSpawnPackets() {
        List<Packet> list = new ArrayList<Packet>();
        list.add(new ServerSpawnPlayerPacket(getEntityID(), getUniqueId(), getLocation().getX(), getLocation().getY(), getLocation().getZ(), getLocation().getYaw(), getLocation().getPitch(), 0, ((CyanMetadataMap) getMetadata()).getMetaArray()));
        list.add(new ServerEntityHeadLookPacket(getEntityID(), getLocation().getYaw()));
        // Inventory and etc.

        // TODO
        return list;
    }

    @Override
    public void setHealth(float health) {
        super.setHealth(health);
        ServerUpdateHealthPacket packet = new ServerUpdateHealthPacket(health, getFoodLevel(), 20);// ???
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void setMaxHealth(float health) {
        super.setMaxHealth(health);
        ServerUpdateHealthPacket packet = new ServerUpdateHealthPacket(health, getFoodLevel(), 20);// ???
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void setFoodLevel(int level) {
        super.setFoodLevel(level);
        ServerUpdateHealthPacket packet = new ServerUpdateHealthPacket(getHealth(), getFoodLevel(), 20);// ???
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void setGamemode(GameMode mode) {
        super.setGamemode(mode);
        ServerNotifyClientPacket packet = new ServerNotifyClientPacket(ClientNotification.CHANGE_GAMEMODE, mode);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void updateChunk(Chunk chunk) {
        CyanChunk cchunk = (CyanChunk) chunk;
        ServerChunkDataPacket packet = new ServerChunkDataPacket(chunk.getX(), chunk.getZ(), cchunk.getSections());
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void respawn() {
        isAlive = true;
        getServer().getEntityManager().register(this);

        setHealth(getMaxHealth());
        setFoodLevel(20);

        ServerRespawnPacket packet = new ServerRespawnPacket(getWorld().getDimension().getId(), getWorld().getDifficulty(), getGameMode(), WorldType.FLAT);// TEST
        getPlayerNetwork().sendPacket(packet);
    }

    // Not api
    @Override
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

    private void updatePosition() {
        for (Packet packet : getUpdatePackets()) {
            getPlayerNetwork().sendPacket(packet);
        }
    }

    private void updateChunks() {
        List<ChunkCoords> prev = new ArrayList<ChunkCoords>(chunks);
        List<ChunkCoords> newChunks = new ArrayList<ChunkCoords>();

        int centralX = getLocation().getChunk().getX();
        int centralZ = getLocation().getChunk().getZ();

        int radius = Math.min(server.getConfiguration().getViewDistance(), 1 + settings.getViewDistance());
        for (int x = (centralX - radius); x <= (centralX + radius); x++) {
            for (int z = (centralZ - radius); z <= (centralZ + radius); z++) {
                ChunkCoords key = new ChunkCoords(x, z);
                if (chunks.contains(key)) {
                    prev.remove(key);
                } else {
                    newChunks.add(key);
                }
            }
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

        for (ChunkCoords chunkcoords : newChunks) {
            chunks.add(chunkcoords);
            CyanChunk chunk = (CyanChunk) getWorld().getChunkManager().getChunk(chunkcoords.getX(), chunkcoords.getZ());
            chunk.addPlayer();
            sendChunk(chunk);
        }

        // Maybe: TODO: ServerMultiChunkDataPacket

        if (!prev.isEmpty()) {
            for (ChunkCoords key : prev) {
                ServerChunkDataPacket packet = new ServerChunkDataPacket(key.getX(), key.getZ());
                getPlayerNetwork().sendPacket(packet);

                CyanChunk chunk = (CyanChunk) getWorld().getChunkManager().getChunk(key.getX(), key.getZ());
                chunk.removePlayer();
                chunks.remove(key);
            }
        }

        prev.clear();
    }

    // @Override
    public void loadCompoundTag(CompoundTag tag) {
        this.compTag = tag;
        // start load
        IntTag gm = tag.get("playerGameType");
        setGamemode(Transform.transformGameMode(gm.getValue()));

        IntTag selectItem = tag.get("SelectedItemSlot");
        getInventory().setHeldItemSlot(selectItem.getValue());

        // CompoundTag item = tag.get("SelectedItem"); //TODO

        IntTag x = tag.get("SpawnX");
        getLocation().setX(x.getValue());

        IntTag y = tag.get("SpawnY");
        getLocation().setX(y.getValue());

        IntTag z = tag.get("SpawnZ");
        getLocation().setX(z.getValue());

        // IntTag spawnForced = tag.get("SpawnForced"); // TODO

        ByteTag sleeping = tag.get("Sleeping");
        if (sleeping.getValue() == 1) {
            sleepInBedAt(x.getValue(), y.getValue(), z.getValue());
        }

        ShortTag sleepingTicks = tag.get("SleepTimer");
        setSleepingTicks(sleepingTicks.getValue());

        IntTag foodLvl = tag.get("foodLevel");
        setFoodLevel(foodLvl.getValue());
        // TODO: foodExhaustionLevel, foodSaturationLevel, foodTickTimer
        // TODO: XpLevel, XpP, XpTotal, XpSeed,
        // TODO: Inventory, EnderItems

        CompoundTag abilities = tag.get("abilities");
        FloatTag walk = abilities.get("walkSpeed");
        setWalkSpeed(walk.getValue());

        FloatTag fly = abilities.get("flySpeed");
        setFlySpeed(fly.getValue());

        ByteTag mayfly = abilities.get("mayfly");
        if (mayfly.getValue() == 1) {
            setAllowFlying(true);
        }

        ByteTag flying = abilities.get("flying");
        if (flying.getValue() == 1) {
            setFlying(true);
        }

        // TODO: invulnerable

        ByteTag mayBuild = abilities.get("mayBuild");
        if (mayBuild.getValue() == 1) {
            setBuild(true);
        }

        // TODO: instabuild
        // End.
    }

    // @Override
    public void saveCompoundTag(CompoundTag tag) {
        // Map<String, Tag> map = tag.getValue();
        // map.put("", new IntTag("playerGameType",
        // Transform.transformGameMode(getGameMode())));
        // map.put("", new IntTag("SelectedItemSlot",
        // getInventory().getHeldItemSlot()));
        // map.put("", new IntTag("SpawnX", getLocation().getBlockX()));
        // map.put("", new IntTag("SpawnY", getLocation().getBlockY()));
        // map.put("", new IntTag("SpawnZ", getLocation().getBlockZ()));
        // TODO

        // tag.setValue(map);
        if (tag != null) {
            tag.put(new IntTag("playerGameType", Transform.transformGameMode(getGameMode())));
            tag.put(new IntTag("SelectedItemSlot", getInventory().getHeldItemSlot()));
            tag.put(new IntTag("SpawnX", getLocation().getBlockX()));
            tag.put(new IntTag("SpawnY", getLocation().getBlockY()));
            tag.put(new IntTag("SpawnZ", getLocation().getBlockZ()));

            byte sleep = 0;
            if (isSleeping()) {
                sleep = 1;
            }
            tag.put(new ByteTag("Sleeping", sleep));
            tag.put(new ShortTag("SleepTimer", (short) getSleepingTicks()));
            tag.put(new IntTag("foodLevel", getFoodLevel()));

            CompoundTag abilities = new CompoundTag("abilities");
            abilities.put(new FloatTag("walkSpeed", getWalkSpeed()));
            abilities.put(new FloatTag("flySpeed", getFlySpeed()));

            byte mayfly = 0;
            if (isAllowFlying()) {
                mayfly = 1;
            }
            abilities.put(new ByteTag("mayfly", mayfly));

            byte flying = 0;
            if (isFlying()) {
                flying = 1;
            }
            abilities.put(new ByteTag("flying", flying));

            byte mayBuild = 0;
            if (canBuild()) {
                mayBuild = 1;
            }
            abilities.put(new ByteTag("mayBuild", mayBuild));
            tag.put(abilities);

        }
    }

    @Override
    public CompoundTag getCompoundTag() {
        if (compTag == null) {
            compTag = new CompoundTag("");
        }
        return compTag;
    }

    @Override
    public boolean isMonster() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void playParticle(Location location, Particle particle, int amount, int data) {
        ServerSpawnParticlePacket packet = new ServerSpawnParticlePacket(particle, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), 0, 0, 0, 0, amount, data);
        getPlayerNetwork().sendPacket(packet);
    }

    @Override
    public void loadData() {
        getWorld().getPlayerService().readPlayer(this);
    }

    @Override
    public void saveData() {
        final Player player = this;
        getServer().getScheduler().runTask(new Runnable() {

            @Override
            public void run() {
                getWorld().getPlayerService().savePlayer(player);
            }
        }, 1);
    }
}