package ykt.BeYkeRYkt.CyanWool.network.handlers;

import org.spacehq.mc.protocol.ServerLoginHandler;
import org.spacehq.packetlib.Session;

import ykt.BeYkeRYkt.CyanWool.CyanServer;

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