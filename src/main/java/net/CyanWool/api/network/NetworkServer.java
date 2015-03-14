package net.CyanWool.api.network;

import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.world.Location;

import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.packet.Packet;

public interface NetworkServer {

    public net.CyanWool.api.Server getServer();

    public Server getProtocolServer();

    public int getPort();

    public String getHostAddress();

    public void sendPacketForAll(Packet packet);

    public void sendPacketDistance(Location location, Packet packet, int radius);

    public void sendPacketForAllExpect(Packet packet, Player expect);

    public void sendPacketDistanceExpect(Location location, Packet packet, int radius, Player expect);

}