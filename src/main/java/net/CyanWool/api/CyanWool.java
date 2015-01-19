package net.CyanWool.api;

import java.util.List;

import net.CyanWool.api.entity.Player;
import net.CyanWool.api.network.NetworkManager;
import net.CyanWool.api.plugin.PluginManager;
import net.CyanWool.api.world.World;

import org.apache.logging.log4j.Logger;

public class CyanWool {

    private static Server mcserver;

    public static void setServer(Server server) {
        if (mcserver != null) {
            server.getLogger().warn("Cannot redefine singleton Server");
            return;
        }
        mcserver = server;
        getServer().getLogger().info("Starting " + getServer().getModName() + " version " + getServer().getModVersion() + " for Minecraft: " + mcserver.getMCVersion());
    }

    public static Server getServer() {
        return mcserver;
    }

    public static String getMCVersion() {
        return getServer().getMCVersion();
    }

    public static String getModName() {
        return getServer().getModName();
    }

    public static String getModVersion() {
        return getServer().getModVersion();
    }

    public static int getPort() {
        return getServer().getServerConfiguration().getPort();
    }

    public static String getHostAddress() {
        return getServer().getServerConfiguration().getIPAdress();
    }

    public static int getMaxPlayers() {
        return getServer().getMaxPlayers();
    }

    public static Logger getLogger() {
        return getServer().getLogger();
    }

    public static NetworkManager getNetworkManager() {
        return getServer().getNetworkManager();
    }

    public static ServerConfiguration getServerConfiguration() {
        return getServer().getServerConfiguration();
    }

    public List<World> getWorlds() {
        return getServer().getWorlds();
    }

    public World getWorld(int i) {
        return getServer().getWorld(i);
    }

    public static List<Player> getPlayers() {
        return getServer().getPlayers();
    }

    public static PluginManager getPluginManager() {
        return getServer().getPluginManager();
    }
}