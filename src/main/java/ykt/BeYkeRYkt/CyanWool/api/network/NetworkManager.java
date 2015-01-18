package ykt.BeYkeRYkt.CyanWool.api.network;

import ykt.BeYkeRYkt.CyanWool.api.Server;

public interface NetworkManager {

    public Server getServer();

    public org.spacehq.packetlib.Server getProtocolServer();

    public int getPort();

    public String getHostAddress();
}