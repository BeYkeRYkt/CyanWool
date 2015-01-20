package net.CyanWool.management;

import java.util.ArrayList;
import java.util.List;

import net.CyanWool.CyanServer;
import net.CyanWool.api.entity.Player;
import net.CyanWool.api.management.PlayerManager;
import net.CyanWool.entity.CyanPlayer;
import net.CyanWool.network.CyanPlayerNetwork;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.WorldType;
import org.spacehq.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;
import org.spacehq.packetlib.Session;

public class CyanPlayerManager implements PlayerManager{

    private CyanServer server;
    private List<Player> players;

    public CyanPlayerManager(CyanServer server) {
        this.server = server;
        this.players = new ArrayList<Player>();
    }
    
    @Override
    public Player loginPlayer(Session session) {
        GameProfile profile = session.getFlag(ProtocolConstants.PROFILE_KEY);
        CyanPlayer player = new CyanPlayer(server, profile);
        player.load();
        player.setPlayerNetwork(new CyanPlayerNetwork(server, session, player));
        ServerJoinGamePacket packet = new ServerJoinGamePacket(1, false, GameMode.SURVIVAL, 1, Difficulty.NORMAL, server.getMaxPlayers(), WorldType.FLAT, true);
        session.send(packet);
        players.add(player);
        player.chat("Hello! This test message for CyanWool!");
        return player;
    }

    @Override
    public void leavePlayer(Player player) {
        server.broadcastMessage(player.getName() + " left the Server!");
        ServerDestroyEntitiesPacket destroyEntitiesPacket = new ServerDestroyEntitiesPacket(player.getEntityID());
        for (Player p : getPlayers()){
        p.getPlayerNetwork().sendPacket(destroyEntitiesPacket);
        }
        //Save and unload
        player.save();
        //getPlayers().remove(player);
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }
}