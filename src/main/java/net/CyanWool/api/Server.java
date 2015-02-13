package net.CyanWool.api;

import java.awt.image.BufferedImage;
import java.util.List;

import net.CyanWool.api.command.CommandManager;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.entity.EntityManager;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.recipes.RecipeManager;
import net.CyanWool.api.plugin.PluginManager;
import net.CyanWool.api.utils.ServerConfiguration;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.WorldManager;

import org.apache.logging.log4j.Logger;

public interface Server {

    public String getMCVersion();

    public String getModName();

    public String getModVersion();

    public int getMaxPlayers();

    public Logger getLogger();

    // public NetworkManager getNetworkManager();

    public ServerConfiguration getServerConfiguration();

    public List<World> getWorlds();

    public World getWorld(int i);

    public List<Player> getPlayers();

    public ICommandSender getConsoleCommandSender();

    public CommandManager getCommandManager();

    public void broadcastMessage(String message);

    public Player getPlayer(String name);

    public PluginManager getPluginManager();

    public WorldManager getWorldManager();

    public void shutdown();

    public BufferedImage getIcon();

    // public PlayerManager getPlayerManager();

    public EntityManager getEntityManager();

    public RecipeManager getRecipeManager();
}