package net.CyanWool.api.event.player;

import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.event.HandlerList;

public class PlayerLeaveEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    public PlayerLeaveEvent(final Player who) {
        super(who);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
