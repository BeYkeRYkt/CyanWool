package net.CyanWool.network;

import net.CyanWool.CyanServer;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.entity.CyanPlayer;
import net.CyanWool.network.handlers.ServerInfo;
import net.CyanWool.network.handlers.ServerLogin;

import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.packet.Packet;
import org.spacehq.packetlib.tcp.TcpSessionFactory;

public class NetworkServer{

    private CyanServer server;
    // MCProtocolLib
    private Server protocol_server;

    public NetworkServer(CyanServer server) {
        this.server = server;
    }

    public void init() {
        if (protocol_server == null) {
            try {
                this.protocol_server = new Server(server.getConfiguration().getIPAdress(), server.getConfiguration().getPort(), MinecraftProtocol.class, new TcpSessionFactory());

                protocol_server.setGlobalFlag(ProtocolConstants.VERIFY_USERS_KEY, server.getConfiguration().isOnlineMode());
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_INFO_BUILDER_KEY, new ServerInfo(server));
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_LOGIN_HANDLER_KEY, new ServerLogin(server));
                protocol_server.addListener(new CyanServerListener(server));
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_COMPRESSION_THRESHOLD, 100);

                protocol_server.bind();
                server.getLogger().info("Protocol server is started!");
            } catch (Exception ex) {
                server.getLogger().info("Server crashed: ");
                ex.printStackTrace();
            }
        }
    }

    public net.CyanWool.api.Server getServer() {
        return server;
    }

    public Server getProtocolServer() {
        return protocol_server;
    }

    public int getPort() {
        return protocol_server.getPort();
    }

    public String getHostAddress() {
        return protocol_server.getHost();
    }

    public void sendPacketForAll(Packet packet) {
        for (Player player : server.getPlayers()) {
            ((CyanPlayer) player).getPlayerNetwork().sendPacket(packet);
        }
    }

    public void sendPacketDistance(int x, int y, int z, Packet packet, int radius) {
        for (Player player : server.getPlayers()) {
            // if(player.getLocation())
            ((CyanPlayer) player).getPlayerNetwork().sendPacket(packet);
            // todo
        }
    }

}