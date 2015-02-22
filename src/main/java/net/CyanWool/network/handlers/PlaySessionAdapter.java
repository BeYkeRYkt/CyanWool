package net.CyanWool.network.handlers;

import net.CyanWool.CyanServer;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.entity.component.InventoryComponent;
import net.CyanWool.api.entity.component.MovementComponent;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.utils.ChatColors;
import net.CyanWool.entity.meta.ClientSettings;
import net.CyanWool.entity.player.CyanPlayer;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.data.game.values.ClientRequest;
import org.spacehq.mc.protocol.data.game.values.entity.player.PlayerAction;
import org.spacehq.mc.protocol.packet.ingame.client.ClientChatPacket;
import org.spacehq.mc.protocol.packet.ingame.client.ClientRequestPacket;
import org.spacehq.mc.protocol.packet.ingame.client.ClientSettingsPacket;
import org.spacehq.mc.protocol.packet.ingame.client.player.ClientPlayerActionPacket;
import org.spacehq.mc.protocol.packet.ingame.client.player.ClientPlayerPositionPacket;
import org.spacehq.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.client.player.ClientPlayerRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.client.player.ClientPlayerStatePacket;
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
            String message = ChatColors.translateAlternateColorCodes('&', packet.getMessage());

            if (packet.getMessage().startsWith("/")) {
                String cmd = packet.getMessage().substring(1);
                server.getCommandManager().dispatchCommand(player, cmd);
            } else {
                player.chat(message);
            }
        } else if (event.getPacket() instanceof ClientPlayerStatePacket) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientPlayerStatePacket packet = event.getPacket();
            switch (packet.getState()) {
                case START_SNEAKING:
                    if (player.getComponentManager().hasComponent("movement")) {
                        MovementComponent component = (MovementComponent) player.getComponentManager().getComponent("movement");
                        component.setSneaking(true);
                    }
                    break;
                case STOP_SNEAKING:
                    if (player.getComponentManager().hasComponent("movement")) {
                        MovementComponent component = (MovementComponent) player.getComponentManager().getComponent("movement");
                        component.setSneaking(false);
                    }
                    break;
                case START_SPRINTING:
                    if (player.getComponentManager().hasComponent("movement")) {
                        MovementComponent component = (MovementComponent) player.getComponentManager().getComponent("movement");
                        component.setSprinting(true);
                    }
                    break;
                case STOP_SPRINTING:
                    if (player.getComponentManager().hasComponent("movement")) {
                        MovementComponent component = (MovementComponent) player.getComponentManager().getComponent("movement");
                        component.setSprinting(false);
                    }
                    break;
                case RIDING_JUMP:
                    if (player.getComponentManager().hasComponent("movement")) {
                        MovementComponent component = (MovementComponent) player.getComponentManager().getComponent("movement");
                        component.setJumping(true);
                    }
                    break;
                case OPEN_INVENTORY:
                    break;
                case LEAVE_BED:
                    player.wakeUp();
                    break;
            }
        } else if (event.getPacket() instanceof ClientPlayerPositionPacket) {
            // TODO
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientPlayerPositionPacket pack = event.getPacket();
            if (player == null || !player.isMoveable())
                return;
            player.getLocation().setX(pack.getX());
            player.getLocation().setY(pack.getY());
            player.getLocation().setZ(pack.getZ());
        } else if (event.getPacket() instanceof ClientPlayerRotationPacket) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientPlayerRotationPacket pack = event.getPacket();
            if (player == null || !player.isMoveable())
                return;
            player.getLocation().setPitch((float) pack.getPitch());
            player.getLocation().setYaw((float) pack.getYaw());
        } else if (event.getPacket() instanceof ClientPlayerPositionRotationPacket) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientPlayerPositionRotationPacket pack = event.getPacket();
            if (player == null || !player.isMoveable())
                return;
            player.getLocation().setX(pack.getX());
            player.getLocation().setY(pack.getY());
            player.getLocation().setZ(pack.getZ());
            player.getLocation().setPitch((float) pack.getPitch());
            player.getLocation().setYaw((float) pack.getYaw());
        } else if (event.getPacket() instanceof ClientPlayerActionPacket) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            ClientPlayerActionPacket packet = event.getPacket();
            if (packet.getAction() == PlayerAction.START_DIGGING) {
                Block block = player.getChunk().getBlock(packet.getPosition().getX(), packet.getPosition().getY(), packet.getPosition().getZ());
                block.getBlockType().onBlockLeftInteract(player);
                block.getBlockType().onPlayerBreaking(player);

                // Item
                if (player.getComponentManager().hasComponent("inventory")) {
                    InventoryComponent component = (InventoryComponent) player.getComponentManager().getComponent("inventory");
                    ItemStack item = component.getInventory().getItemInHand();
                    item.getItemType().onItemLeftClick(item, player);
                }
            } else if (packet.getAction() == PlayerAction.FINISH_DIGGING) {
                Block block = player.getChunk().getBlock(packet.getPosition().getX(), packet.getPosition().getY(), packet.getPosition().getZ());
                block.breakBlock();

                // Item
                if (player.getComponentManager().hasComponent("inventory")) {
                    InventoryComponent component = (InventoryComponent) player.getComponentManager().getComponent("inventory");
                    ItemStack item = component.getInventory().getItemInHand();
                    item.getItemType().onBlockDestroyed(item, block, player);
                }

            } else if (packet.getAction() == PlayerAction.RELEASE_USE_ITEM) {
                if (player.getComponentManager().hasComponent("inventory")) {
                    InventoryComponent component = (InventoryComponent) player.getComponentManager().getComponent("inventory");
                    ItemStack item = component.getInventory().getItemInHand();
                    item.getItemType().onItemRightClick(item, player);
                }
                Block block = player.getChunk().getBlock(packet.getPosition().getX(), packet.getPosition().getY(), packet.getPosition().getZ());
                block.getBlockType().onBlockRightInteract(player);
            }
        } else if (event.getPacket() instanceof ClientRequestPacket) {
            ClientRequestPacket packet = event.getPacket();
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayer(profile.getName());
            if (packet.getRequest() == ClientRequest.RESPAWN) {
                player.respawn();
            }
        }
    }
}