package net.CyanWool.management;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.CyanServer;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.entity.CyanPlayer;
import net.CyanWool.network.PlayerNetwork;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.WorldType;
import org.spacehq.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.packetlib.Session;

public class PlayerManager {

    private CyanServer server;
    private List<Player> players;

    public PlayerManager(CyanServer server) {
        this.server = server;
        this.players = new ArrayList<Player>();
    }

    public Player loginPlayer(Session session) {
        GameProfile profile = session.getFlag(ProtocolConstants.PROFILE_KEY);
        World world = server.getWorld(0);
        Location location = world.getSpawnLocation();
        CyanPlayer player = new CyanPlayer(server, profile, location);
        world.getEntities().add(player);
        // player.load();
        player.setPlayerNetwork(new PlayerNetwork(server, session, player));
        ServerJoinGamePacket packet = new ServerJoinGamePacket(1, false, GameMode.SURVIVAL, 1, Difficulty.NORMAL, server.getMaxPlayers(), WorldType.FLAT, true);
        session.send(packet);
        players.add(player);
        player.chat("Hello! This test message for CyanWool!");
        return player;
    }

    public void leavePlayer(Player player) {
        server.broadcastMessage(player.getName() + " left the Server!");
        ServerDestroyEntitiesPacket destroyEntitiesPacket = new ServerDestroyEntitiesPacket(player.getEntityID());
        for (Player p : getPlayers()) {
            ((CyanPlayer) p).getPlayerNetwork().sendPacket(destroyEntitiesPacket);
        }
        // Save and unload
        // player.save();
        // getPlayers().remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}