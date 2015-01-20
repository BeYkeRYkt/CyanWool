package net.CyanWool.api.network;

import net.CyanWool.api.Server;

import org.spacehq.packetlib.packet.Packet;

public interface NetworkManager {

    public Server getServer();

    public org.spacehq.packetlib.Server getProtocolServer();

    public int getPort();

    public String getHostAddress();
    
    public void sendPacketForAll(Packet packet);
    
    public void sendPacketDistance(int x, int y, int z, Packet packet, int radius); //Dev
}