package ykt.BeYkeRYkt.CyanWool.api;

import java.util.List;

import org.apache.logging.log4j.Logger;

import ykt.BeYkeRYkt.CyanWool.api.command.CommandManager;
import ykt.BeYkeRYkt.CyanWool.api.command.ICommandSender;
import ykt.BeYkeRYkt.CyanWool.api.entity.Player;
import ykt.BeYkeRYkt.CyanWool.api.network.NetworkManager;
import ykt.BeYkeRYkt.CyanWool.api.plugin.PluginManager;
import ykt.BeYkeRYkt.CyanWool.api.world.World;
import ykt.BeYkeRYkt.CyanWool.api.world.WorldManager;

public interface Server {

    public String getMCVersion();

    public String getModName();

    public String getModVersion();

    public int getMaxPlayers();

    public Logger getLogger();

    public NetworkManager getNetworkManager();

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
}