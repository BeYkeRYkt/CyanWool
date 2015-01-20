package net.CyanWool.api.management;

import java.util.List;

import net.CyanWool.api.entity.Player;

import org.spacehq.packetlib.Session;

public interface PlayerManager {
    
    public Player loginPlayer(Session session);
    
    public void leavePlayer(Player player);
    
    public List<Player> getPlayers();
}