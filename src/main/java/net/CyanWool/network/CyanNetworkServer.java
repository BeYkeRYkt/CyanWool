package net.CyanWool.network;

import net.CyanWool.CyanServer;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.network.NetworkServer;
import net.CyanWool.api.world.Location;
import net.CyanWool.entity.player.CyanPlayer;
import net.CyanWool.network.handlers.ServerInfo;
import net.CyanWool.network.handlers.ServerLogin;

import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.packet.Packet;
import org.spacehq.packetlib.tcp.TcpSessionFactory;

public class CyanNetworkServer implements NetworkServer {

    private static CyanServer server;
    // MCProtocolLib
    private Server protocol_server;

    public CyanNetworkServer(CyanServer server) {
        CyanNetworkServer.server = server;
    }

    public void init() {
        if (protocol_server == null) {
            try {
                this.protocol_server = new Server(server.getConfiguration().getIPAdress(), server.getConfiguration().getPort(), MinecraftProtocol.class, new TcpSessionFactory());

                protocol_server.setGlobalFlag(ProtocolConstants.VERIFY_USERS_KEY, server.getConfiguration().isOnlineMode());
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_INFO_BUILDER_KEY, new ServerInfo(server));
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_LOGIN_HANDLER_KEY, new ServerLogin(server));
                protocol_server.addListener(new CyanServerListener(server));
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_COMPRESSION_THRESHOLD, 256);

                protocol_server.bind();
                server.getLogger().info("Protocol server is started!");
            } catch (Exception ex) {
                server.getLogger().info("Server crashed: ");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public net.CyanWool.api.Server getServer() {
        return server;
    }

    @Override
    public Server getProtocolServer() {
        return protocol_server;
    }

    @Override
    public int getPort() {
        return protocol_server.getPort();
    }

    @Override
    public String getHostAddress() {
        return protocol_server.getHost();
    }

    @Override
    public void sendPacketForAll(Packet packet) {
        for (Player player : server.getPlayers()) {
            player.getPlayerNetwork().sendPacket(packet);
        }
    }

    @Override
    public void sendPacketDistance(Location location, Packet packet, int radius) {
        for (Player player : server.getPlayers()) {
            if (player.getWorld().getName().equals(location.getWorld().getName())) {
                if (player.getLocation().distance(location) < radius) {
                    player.getPlayerNetwork().sendPacket(packet);
                }
            }
        }
    }

    @Override
    public void sendPacketForAllExpect(Packet packet, Player expect) {
        for (Player player : server.getPlayers()) {
            if (!player.equals(expect)) {
                player.getPlayerNetwork().sendPacket(packet);
            }
        }
    }

    @Override
    public void sendPacketDistanceExpect(Location location, Packet packet, int radius, Player expect) {
        for (Player player : server.getPlayers()) {
            if (!player.equals(expect)) {
                if (player.getWorld().getName().equals(location.getWorld().getName())) {
                    if (player.getLocation().distance(location) < radius) {
                        ((CyanPlayer) player).getPlayerNetwork().sendPacket(packet);
                    }
                }
            }
        }
    }

}