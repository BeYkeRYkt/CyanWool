package ykt.BeYkeRYkt.CyanWool.network.handlers;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.data.message.TextMessage;
import org.spacehq.mc.protocol.data.status.PlayerInfo;
import org.spacehq.mc.protocol.data.status.ServerStatusInfo;
import org.spacehq.mc.protocol.data.status.VersionInfo;
import org.spacehq.mc.protocol.data.status.handler.ServerInfoBuilder;
import org.spacehq.packetlib.Session;

import ykt.BeYkeRYkt.CyanWool.CyanServer;

public class ServerInfo implements ServerInfoBuilder {

    private CyanServer server;

    public ServerInfo(CyanServer server) {
        this.server = server;
    }

    @Override
    public ServerStatusInfo buildInfo(Session session) {
        VersionInfo version = new VersionInfo(ProtocolConstants.GAME_VERSION, ProtocolConstants.PROTOCOL_VERSION);
        PlayerInfo player = new PlayerInfo(server.getConfiguration().getMaxPlayers(), server.getPlayers().size(), new GameProfile[0]);
        Message message = new TextMessage(server.getConfiguration().getMotd());

        BufferedImage icon = null;
        try {
            icon = ImageIO.read(new File("server-icon.png"));
        } catch (Exception ignored) {}
        return new ServerStatusInfo(version, player, message, icon);
    }

}