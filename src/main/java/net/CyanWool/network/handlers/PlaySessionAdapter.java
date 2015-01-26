package net.CyanWool.network.handlers;

import net.CyanWool.CyanServer;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.entity.CyanPlayer;
import net.CyanWool.entity.meta.ClientSettings;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.packet.ingame.client.ClientChatPacket;
import org.spacehq.mc.protocol.packet.ingame.client.ClientKeepAlivePacket;
import org.spacehq.mc.protocol.packet.ingame.client.ClientSettingsPacket;
import org.spacehq.mc.protocol.packet.ingame.client.player.ClientPlayerStatePacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerKeepAlivePacket;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;

public class PlaySessionAdapter extends SessionAdapter {

    private CyanServer server;

    public PlaySessionAdapter(CyanServer server) {
        this.server = server;
    }

    @Override
    public void packetReceived(PacketReceivedEvent event) {
        if (event.getPacket() instanceof ClientSettingsPacket) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientSettingsPacket packet = event.getPacket();
            ClientSettings settings = new ClientSettings(packet);
            ((CyanPlayer) player).setSettings(settings);
        } else if (event.getPacket() instanceof ClientChatPacket) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientChatPacket packet = event.getPacket();
            if (packet.getMessage().startsWith("/")) {
                server.getCommandManager().dispatchCommand(player, packet.getMessage());
            } else {
                player.chat(packet.getMessage());
            }
        } else if (event.getPacket() instanceof ClientPlayerStatePacket) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientPlayerStatePacket packet = event.getPacket();
            switch (packet.getState()) {
                case START_SNEAKING:
                    player.setSneaking(true);
                    break;
                case STOP_SNEAKING:
                    player.setSneaking(false);
                    break;
                case START_SPRINTING:
                    player.setSprinting(true);
                    break;
                case STOP_SPRINTING:
                    player.setSprinting(false);
                    break;
                case RIDING_JUMP:
                    break;
                case OPEN_INVENTORY:
                    break;
                case LEAVE_BED:
                    player.wakeUp();
                    break;
            }
        } else if (event.getPacket() instanceof ClientKeepAlivePacket) {
            event.getSession().send(new ServerKeepAlivePacket(event.<ClientKeepAlivePacket> getPacket().getPingId()));
        }
    }
}