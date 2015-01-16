package ykt.BeYkeRYkt.CyanWool.api;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.logging.Logger;

public class CyanWool {

    private static Server mcserver;

    public static void setServer(Server server) {
        if (mcserver != null) {
            server.getLogger().severe("Cannot redefine singleton Server");
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
        return getServer().getPort();
    }

    public static SocketAddress getHostAddress() {
        return getServer().getHostAddress();
    }

    public static int getMaxPlayers() {
        return getServer().getMaxPlayers();
    }

    public static Logger getLogger() {
        return getServer().getLogger();
    }
}