package net.CyanWool.api;

import java.awt.image.BufferedImage;
import java.util.List;

import net.CyanWool.api.command.CommandManager;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.entity.EntityManager;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.recipes.RecipeManager;
import net.CyanWool.api.plugin.PluginManager;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.WorldManager;

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

    public static ServerConfiguration getServerConfiguration() {
        return getServer().getServerConfiguration();
    }

    public static List<World> getWorlds() {
        return getServer().getWorlds();
    }

    public static World getWorld(int i) {
        return getServer().getWorld(i);
    }

    public static List<Player> getPlayers() {
        return getServer().getPlayers();
    }

    public static PluginManager getPluginManager() {
        return getServer().getPluginManager();
    }

    public static Player getPlayer(String name) {
        return getServer().getPlayer(name);
    }

    public static void shutdown() {
        getServer().shutdown();
    }

    public static ICommandSender getConsoleCommandSender() {
        return getServer().getConsoleCommandSender();
    }

    public static CommandManager getCommandManager() {
        return getServer().getCommandManager();
    }

    public static void broadcastMessage(String message) {
        getServer().broadcastMessage(message);
    }

    public WorldManager getWorldManager() {
        return getServer().getWorldManager();
    }

    public static BufferedImage getIcon() {
        return getServer().getIcon();
    }

    public static WorldManager getWordManager() {
        return getServer().getWorldManager();
    }

    public static EntityManager getEntityManager() {
        return getServer().getEntityManager();
    }

    public static RecipeManager getRecipeManager() {
        return getServer().getRecipeManager();
    }
}