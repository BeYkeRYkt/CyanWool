package ykt.BeYkeRYkt.CyanWool.api.network;

import org.spacehq.packetlib.event.session.SessionListener;
import org.spacehq.packetlib.packet.Packet;

import ykt.BeYkeRYkt.CyanWool.api.Server;

public interface PlayerNetwork {

    public void sendPacket(Packet packet);

    public void disconnect(String reason);

    public Server getServer();

    public void addListener(SessionListener listener);

    public void removeListener(SessionListener listener);

    public String getHost();
}