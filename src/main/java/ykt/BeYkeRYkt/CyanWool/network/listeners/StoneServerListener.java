package ykt.BeYkeRYkt.CyanWool.network.listeners;

import org.spacehq.packetlib.event.server.ServerBoundEvent;
import org.spacehq.packetlib.event.server.ServerClosedEvent;
import org.spacehq.packetlib.event.server.ServerClosingEvent;
import org.spacehq.packetlib.event.server.ServerListener;
import org.spacehq.packetlib.event.server.SessionAddedEvent;
import org.spacehq.packetlib.event.server.SessionRemovedEvent;

import ykt.BeYkeRYkt.CyanWool.CyanServer;

public class StoneServerListener implements ServerListener {

    private CyanServer server;

    public StoneServerListener(CyanServer server) {
        this.server = server;
    }

    @Override
    public void serverBound(ServerBoundEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void serverClosed(ServerClosedEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void serverClosing(ServerClosingEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sessionAdded(SessionAddedEvent arg0) {
        server.getLogger().info("Session " + arg0.getSession().getHost() + ":" + arg0.getSession().getPort() + " connected!");
    }

    @Override
    public void sessionRemoved(SessionRemovedEvent arg0) {
        server.getLogger().info("Session " + arg0.getSession().getHost() + ":" + arg0.getSession().getPort() + " disconnected!");
    }
}