package net.devwool.cyanwool.api.network;

import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.world.Position;

import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.packet.Packet;

public interface NetworkServer {

    public net.devwool.cyanwool.api.Server getServer();

    public Server getProtocolServer();

    public int getPort();

    public String getHostAddress();

    public void sendPacketForAll(Packet packet);

    public void sendPacketDistance(Position pos, Packet packet, int radius);

    public void sendPacketForAllExpect(Packet packet, Player expect);

    public void sendPacketDistanceExpect(Position pos, Packet packet, int radius, Player expect);

}