package net.CyanWool.management;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.CyanServer;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.entity.player.CyanPlayer;
import net.CyanWool.network.CyanPlayerNetwork;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.data.game.Position;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.WorldType;
import org.spacehq.mc.protocol.packet.ingame.server.ServerDifficultyPacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.player.ServerUpdateHealthPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerSpawnPositionPacket;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.packet.Packet;

public class PlayerManager {

    private CyanServer server;
    private List<Player> players;

    public PlayerManager(CyanServer server) {
        this.server = server;
        this.players = new ArrayList<Player>();
    }

    public synchronized Player loginPlayer(Session session) {
        GameProfile profile = session.getFlag(ProtocolConstants.PROFILE_KEY);
        World world = server.getWorld(0);
        Location location = world.getSpawnLocation();
        CyanPlayer player = new CyanPlayer(server, profile, location);
        player.setPlayerNetwork(new CyanPlayerNetwork(server, session, player));

        // player.load();
        ServerJoinGamePacket packet = new ServerJoinGamePacket(player.getEntityID(), false, GameMode.SURVIVAL, 0, Difficulty.NORMAL, server.getMaxPlayers(), WorldType.DEFAULT, false);
        session.send(packet);
        players.add(player);

        // Send spawn packet
        ServerPlayerPositionRotationPacket ppacket = new ServerPlayerPositionRotationPacket(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
        session.send(ppacket);

        Position position = new Position(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
        ServerSpawnPositionPacket spawnPacket = new ServerSpawnPositionPacket(position);
        session.send(spawnPacket);

        // set time
        player.setTime(0);// TODO

        // Send health
        ServerUpdateHealthPacket healthPacket = new ServerUpdateHealthPacket(20, 20, 1);
        session.send(healthPacket);

        // Send Difficulty
        ServerDifficultyPacket diffPacket = new ServerDifficultyPacket(Difficulty.NORMAL);
        session.send(diffPacket);

        // Send packets for all players
        for (Packet packets : player.getSpawnPackets()) {
            server.getNetworkServer().sendPacketForAll(packets);
        }

        player.chat("Hello! This test message for CyanWool!");
        player.setMoveable(true);

        // Test sounds
        player.damage(5);
        return player;
    }

    public synchronized void leavePlayer(Player player) {
        server.broadcastMessage(player.getName() + " left the Server!");
        // ServerDestroyEntitiesPacket destroyEntitiesPacket = new
        // ServerDestroyEntitiesPacket(player.getEntityID());
        // NetworkServer.sendPacketForAll(destroyEntitiesPacket);
        player.kill();
        // Save and unload
        // player.save();
        // player.getWorld().getEntityManager().unregister(player);
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}