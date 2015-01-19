package net.CyanWool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Server;
import net.CyanWool.api.ServerConfiguration;
import net.CyanWool.api.command.CommandManager;
import net.CyanWool.api.command.ConsoleCommandSender;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.network.NetworkManager;
import net.CyanWool.api.plugin.PluginManager;
import net.CyanWool.api.theards.ConsoleThread;
import net.CyanWool.api.theards.SchedulerThread;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.WorldManager;
import net.CyanWool.network.CyanNetworkServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CyanServer implements Server {

    private static final String MC_VERSION = "1.8";
    private static final String MOD_NAME = "CyanWool";
    private static final String MOD_VERSION = "0.0.1-Dev";

    private final Logger logger = LogManager.getLogger(CyanServer.class);
    private ServerConfiguration config;
    private NetworkManager network;
    private WorldManager worlds;
    private ICommandSender consoleSender;
    private CommandManager cmdManager;
    private PluginManager pluginManager;

    private SchedulerThread schedulert;
    private ConsoleThread console;

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

        this.console = new ConsoleThread(this);
        this.console.start();
        this.consoleSender = new ConsoleCommandSender();
        CyanNetworkServer network = new CyanNetworkServer(this);
        this.network = network;
        network.init();

        this.worlds = new WorldManager(this);
        this.cmdManager = new CommandManager();
        this.pluginManager = new PluginManager();
        this.pluginManager.loadPlugins();

        // load worlds...

        this.pluginManager.enablePlugins();

        this.schedulert = new SchedulerThread(this);
        this.schedulert.start();
        
        long timeEnd = System.currentTimeMillis();
        long time = (timeEnd - timeStart);
        double seconds = ((double)time / 1000);
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
    public NetworkManager getNetworkManager() {
        return network;
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
        
        //TODO
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<Player>();
        for (World world : getWorlds()) {
            list.addAll(world.getPlayers());
        }
        return list;
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
}