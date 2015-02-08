package net.CyanWool.network;

import net.CyanWool.CyanServer;
import net.CyanWool.api.Server;
import net.CyanWool.entity.player.CyanPlayer;

import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.event.session.SessionListener;
import org.spacehq.packetlib.packet.Packet;

public class PlayerNetwork {

    private CyanServer server;
    private CyanPlayer player;
    private Session session;

    public PlayerNetwork(CyanServer server, Session session, CyanPlayer player) {
        this.server = server;
        this.session = session;
        this.player = player;
    }

    public CyanPlayer getPlayer() {
        return player;
    }

    public void sendPacket(Packet packet) {
        session.send(packet);
    }

    public void addListener(SessionListener listener) {
        session.addListener(listener);
    }

    public void removeListener(SessionListener listener) {
        session.removeListener(listener);
    }

    public void disconnect(String string) {
        session.disconnect(string);
    }

    public String getHost() {
        return session.getHost();
    }

    public Server getServer() {
        return server;
    }

    public void handlePacket(Packet packet) {
        // TODO Auto-generated method stub

    }
}