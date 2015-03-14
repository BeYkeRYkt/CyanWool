package net.CyanWool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Register;
import net.CyanWool.api.Server;
import net.CyanWool.api.command.CommandManager;
import net.CyanWool.api.command.ConsoleCommandSender;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.entity.EntityManager;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.recipes.RecipeManager;
import net.CyanWool.api.inventory.recipes.SimpleRecipeManager;
import net.CyanWool.api.network.NetworkServer;
import net.CyanWool.api.plugin.PluginManager;
import net.CyanWool.api.scheduler.Scheduler;
import net.CyanWool.api.utils.ServerConfiguration;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.WorldManager;
import net.CyanWool.block.blocks.BlockAir;
import net.CyanWool.block.blocks.BlockBedrock;
import net.CyanWool.block.blocks.BlockDirt;
import net.CyanWool.block.blocks.BlockGrass;
import net.CyanWool.block.blocks.BlockGrassTest;
import net.CyanWool.inventory.items.ItemBedrock;
import net.CyanWool.inventory.items.ItemDirt;
import net.CyanWool.inventory.items.ItemGrass;
import net.CyanWool.io.CyanPlayerIOService;
import net.CyanWool.io.CyanWorldIOService;
import net.CyanWool.management.PlayerManager;
import net.CyanWool.network.CyanNetworkServer;
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

    private ConsoleThread console;
    private PlayerManager playerManager;
    private EntityManager entityManager;
    private RecipeManager recipeManager;
    private Scheduler scheduler;

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

        registerVanilla();
        this.playerManager = new PlayerManager(this);
        this.entityManager = new EntityManager();
        this.recipeManager = new SimpleRecipeManager();

        this.console = new ConsoleThread(this);
        this.console.start();
        this.consoleSender = new ConsoleCommandSender();
        CyanNetworkServer network = new CyanNetworkServer(this);
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

        scheduler.stop();
        getLogger().info("Scheduler's shutdown!");

        pluginManager.unloadPlugins();
        getLogger().info("Plugins unloaded!");

        network.getProtocolServer().close();
        getLogger().info("NetworkServer is closed!");

        console.shutdown();

        config.save();
        getLogger().info("Config is saved!");

        worlds.saveAllWorlds();
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
    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public RecipeManager getRecipeManager() {
        return recipeManager;
    }

    @Override
    public NetworkServer getNetworkServer() {
        return network;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    // NOT API

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    private void loadWorlds() {
        // TODO: TESTING!
        World world = new CyanWorld("world", new CyanPlayerIOService());
        getWorldManager().loadWorld(world);
        getWorldManager().addWorld(world);
    }

    private void registerVanilla() {
        // TODO: TESTING!
        Register.registerItem(new ItemGrass());
        Register.registerItem(new ItemDirt());
        Register.registerItem(new ItemBedrock());

        Register.registerBlock(new BlockAir());
        Register.registerBlock(new BlockDirt());
        Register.registerBlock(new BlockGrass());
        Register.registerBlock(new BlockBedrock());
        Register.registerBlock(new BlockGrassTest());
    }

}