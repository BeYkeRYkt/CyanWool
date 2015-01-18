package ykt.BeYkeRYkt.CyanWool.api.event.player;

import ykt.BeYkeRYkt.CyanWool.api.entity.Player;
import ykt.BeYkeRYkt.CyanWool.api.event.HandlerList;

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
