package net.CyanWool.network.handlers;

import net.CyanWool.CyanServer;

import org.spacehq.mc.protocol.ServerLoginHandler;
import org.spacehq.packetlib.Session;

public class ServerLogin implements ServerLoginHandler {

    private CyanServer server;

    public ServerLogin(CyanServer server) {
        this.server = server;
    }

    @Override
    public void loggedIn(Session session) {
        // server.getPlayerManager().joinPlayer(session);
    }

}