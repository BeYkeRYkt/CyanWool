package ykt.BeYkeRYkt.CyanWool.network;

import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.tcp.TcpSessionFactory;

import ykt.BeYkeRYkt.CyanWool.CyanServer;
import ykt.BeYkeRYkt.CyanWool.api.network.NetworkManager;
import ykt.BeYkeRYkt.CyanWool.network.handlers.ServerInfo;
import ykt.BeYkeRYkt.CyanWool.network.handlers.ServerLogin;
import ykt.BeYkeRYkt.CyanWool.network.listeners.StoneServerListener;

public class CyanNetworkServer implements NetworkManager {

    private CyanServer server;
    // MCProtocolLib
    private Server protocol_server;

    public CyanNetworkServer(CyanServer server) {
        this.server = server;
    }

    public void init() {
        if (protocol_server == null) {
            try {
                this.protocol_server = new Server(server.getConfiguration().getIPAdress(), server.getConfiguration().getPort(), MinecraftProtocol.class, new TcpSessionFactory());

                protocol_server.setGlobalFlag(ProtocolConstants.VERIFY_USERS_KEY, server.getConfiguration().isOnlineMode());
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_COMPRESSION_THRESHOLD, 100);
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_INFO_BUILDER_KEY, new ServerInfo(server));
                protocol_server.setGlobalFlag(ProtocolConstants.SERVER_LOGIN_HANDLER_KEY, new ServerLogin(server));
                protocol_server.addListener(new StoneServerListener(server));

                protocol_server.bind();
                server.getLogger().info("Protocol server is started!");
            } catch (Exception ex) {
                server.getLogger().info("Server crashed: ");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ykt.BeYkeRYkt.CyanWool.api.Server getServer() {
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

}