package net.CyanWool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Register;
import net.CyanWool.api.Server;
import net.CyanWool.api.ServerConfiguration;
import net.CyanWool.api.command.CommandManager;
import net.CyanWool.api.command.ConsoleCommandSender;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.entity.EntityManager;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.plugin.PluginManager;
import net.CyanWool.api.theards.SchedulerThread;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.WorldManager;
import net.CyanWool.block.blocks.BlockAir;
import net.CyanWool.block.blocks.BlockBedrock;
import net.CyanWool.block.blocks.BlockDirt;
import net.CyanWool.block.blocks.BlockGrass;
import net.CyanWool.io.CyanPlayerIOService;
import net.CyanWool.io.CyanWorldIOService;
import net.CyanWool.management.PlayerManager;
import net.CyanWool.network.NetworkServer;
import net.CyanWool.world.CyanWorld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CyanServer implements Server {

    private static final String MC_VERSION = "1.8";
    private static final String MOD_NAME = "CyanWool";
    private static final String MOD_VERSION = "0.0.1-Dev";

    private final Logger logger = LogManager.getLogger(CyanServer.class);
    private ServerConfiguration config;
    private NetworkServer network;
    private WorldManager worlds;
    private ICommandSender consoleSender;
    private CommandManager cmdManager;
    private PluginManager pluginManager;

    private SchedulerThread schedulert;
    private ConsoleThread console;
    private PlayerManager playerManager;
    private EntityManager entityManager;

    private static BufferedImage icon;

    public static void main(String[] args) {
        CyanServer mc = new CyanServer();
        CyanWool.setServer(mc);
        mc.init();
    }

    private void init() {
        // init
        long timeStart = System.currentTimeMillis();
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            getLogger().warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar <server_name>.jar\"");
            return;
        }

        File configFile = new File("server.yml");
        getLogger().info("Loading server.yml");
        this.config = new ServerConfiguration(configFile);
        this.config.init();

        this.playerManager = new PlayerManager(this);
        this.entityManager = new EntityManager();

        this.console = new ConsoleThread(this);
        this.console.start();
        this.consoleSender = new ConsoleCommandSender();
        NetworkServer network = new NetworkServer(this);
        this.network = network;
        network.init();

        try {
            icon = ImageIO.read(new File("server-icon.png"));
        } catch (Exception ignored) {

        }

        this.worlds = new WorldManager(this, new CyanWorldIOService());
        this.cmdManager = new CommandManager();
        this.pluginManager = new PluginManager();
        this.pluginManager.loadPlugins();

        // load worlds...
        loadWorlds();

        this.pluginManager.enablePlugins();

        this.schedulert = new SchedulerThread(this);
        this.schedulert.start();

        long timeEnd = System.currentTimeMillis();
        long time = (timeEnd - timeStart);
        double seconds = ((double) time / 1000);
        getLogger().info("Done! ( " + seconds + " sec)");
    }

    /**
     * @return the logger
     */
    @Override
    public Logger getLogger() {
        return logger;
    }

    public ServerConfiguration getConfiguration() {
        return config;
    }

    @Override
    public String getMCVersion() {
        return MC_VERSION;
    }

    @Override
    public String getModName() {
        return MOD_NAME;
    }

    @Override
    public String getModVersion() {
        return MOD_VERSION;
    }

    @Override
    public int getMaxPlayers() {
        return getServerConfiguration().getMaxPlayers();
    }

    @Override
    public ServerConfiguration getServerConfiguration() {
        return config;
    }

    @Override
    public List<World> getWorlds() {
        return worlds.getWorlds();
    }

    @Override
    public World getWorld(int i) {
        return worlds.getWorlds().get(i);
    }

    @Override
    public void shutdown() {
        getLogger().info("Shutdown!");

        pluginManager.unloadPlugins();
        getLogger().info("Plugins unloaded!");

        network.getProtocolServer().close();
        getLogger().info("NetworkServer is closed!");

        schedulert.shutdown();
        console.shutdown();
        getLogger().info("Scheduler's shutdown!");

        config.save();
        getLogger().info("Config is saved!");

        if (!worlds.getWorlds().isEmpty()) {
            for (World world : worlds.getWorlds()) {
                worlds.removeWorld(world);
                getLogger().info(world.getName() + " is unloaded!");
            }
        }
        System.exit(1);
        // TODO
    }

    @Override
    public List<Player> getPlayers() {
        return getPlayerManager().getPlayers();
    }

    @Override
    public ICommandSender getConsoleCommandSender() {
        return consoleSender;
    }

    @Override
    public CommandManager getCommandManager() {
        return cmdManager;
    }

    @Override
    public void broadcastMessage(String message) {
        for (Player player : getPlayers()) {
            player.sendMessage(message);
        }
        getConsoleCommandSender().sendMessage(message);
    }

    @Override
    public Player getPlayer(String name) {
        for (Player player : getPlayers()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public WorldManager getWorldManager() {
        return worlds;
    }

    @Override
    public BufferedImage getIcon() {
        return icon;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    // NOT API

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public NetworkServer getNetworkManager() {
        return network;
    }

    private void loadWorlds() {
        // TODO: TESTING!
        Register.registerItem(new ItemStack(0));
        Register.registerItem(new ItemStack(2));
        Register.registerItem(new ItemStack(3));
        Register.registerItem(new ItemStack(7));

        Register.registerBlock(new BlockAir());
        Register.registerBlock(new BlockDirt());
        Register.registerBlock(new BlockGrass());
        Register.registerBlock(new BlockBedrock());

        World world = new CyanWorld("world", new CyanPlayerIOService());
        getWorldManager().loadWorld(world);
        getWorldManager().addWorld(world);
        world.getChunkManager().loadChunk(world.getSpawnLocation().getBlockX() >> 4, world.getSpawnLocation().getBlockZ() >> 4, false);

        int centerX = world.getSpawnLocation().getBlockX() >> 4;
        int centerZ = world.getSpawnLocation().getBlockZ() >> 4;
        int radius = 4 * 8 / 3;
        long loadTime = System.currentTimeMillis();
        int total = (radius * 2 + 1) * (radius * 2 + 1), current = 0;
        for (int x = centerX - radius; x <= centerX + radius; ++x) {
            for (int z = centerZ - radius; z <= centerZ + radius; ++z) {
                ++current;
                world.getChunkManager().loadChunk(x, z, false);
                // spawnChunkLock.acquire(new GlowChunk.Key(x, z));
                if (System.currentTimeMillis() >= loadTime + 1000) {
                    int progress = 100 * current / total;
                    getLogger().info("Preparing spawn for " + world.getName() + ": " + progress + "%");
                    loadTime = System.currentTimeMillis();
                }
            }
        }
    }

}