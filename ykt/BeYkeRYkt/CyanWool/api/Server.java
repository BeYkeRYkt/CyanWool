package ykt.BeYkeRYkt.CyanWool.api;

import java.net.SocketAddress;
import java.util.logging.Logger;

public interface Server {

    public String getMCVersion();

    public String getModName();

    public String getModVersion();

    public int getPort();

    public SocketAddress getHostAddress();

    public int getMaxPlayers();

    public Logger getLogger();
}