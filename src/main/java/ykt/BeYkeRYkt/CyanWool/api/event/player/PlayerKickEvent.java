package ykt.BeYkeRYkt.CyanWool.api.event.player;

import ykt.BeYkeRYkt.CyanWool.api.entity.Player;
import ykt.BeYkeRYkt.CyanWool.api.event.Cancellable;
import ykt.BeYkeRYkt.CyanWool.api.event.HandlerList;

public class PlayerKickEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private String kickReason;
    private Boolean cancel;

    public PlayerKickEvent(final Player playerKicked, final String kickReason) {
        super(playerKicked);
        this.kickReason = kickReason;
        this.cancel = false;
    }

    public String getReason() {
        return kickReason;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public void setReason(String kickReason) {
        this.kickReason = kickReason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
