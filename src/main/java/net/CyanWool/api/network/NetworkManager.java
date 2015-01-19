package net.CyanWool.api.network;

import net.CyanWool.api.Server;

public interface NetworkManager {

    public Server getServer();

    public org.spacehq.packetlib.Server getProtocolServer();

    public int getPort();

    public String getHostAddress();
}