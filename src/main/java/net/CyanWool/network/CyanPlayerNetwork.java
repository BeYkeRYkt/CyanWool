package net.CyanWool.network;

import net.CyanWool.CyanServer;
import net.CyanWool.api.Server;
import net.CyanWool.api.network.PlayerNetwork;
import net.CyanWool.entity.CyanPlayer;

import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.event.session.SessionListener;
import org.spacehq.packetlib.packet.Packet;

public class CyanPlayerNetwork implements PlayerNetwork {

    private CyanServer server;
    private CyanPlayer player;
    private Session session;

    public CyanPlayerNetwork(CyanServer server, Session session, CyanPlayer player) {
        this.server = server;
        this.session = session;
        this.player = player;
    }

    public CyanPlayer getPlayer() {
        return player;
    }

    @Override
    public void sendPacket(Packet packet) {
        session.send(packet);
    }

    @Override
    public void addListener(SessionListener listener) {
        session.addListener(listener);
    }

    @Override
    public void removeListener(SessionListener listener) {
        session.removeListener(listener);
    }

    @Override
    public void disconnect(String string) {
        session.disconnect(string);
    }

    @Override
    public String getHost() {
        return session.getHost();
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public void handlePacket(Packet packet) {
        // TODO Auto-generated method stub
        
    }
}