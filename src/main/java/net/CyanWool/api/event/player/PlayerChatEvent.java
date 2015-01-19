package net.CyanWool.api.event.player;

import java.util.IllegalFormatException;
import java.util.Set;

import net.CyanWool.api.entity.Player;
import net.CyanWool.api.event.Cancellable;
import net.CyanWool.api.event.HandlerList;

public class PlayerChatEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private String message;
    private String format = "<%1$s> %2$s";
    private final Set<Player> recipients;

    public PlayerChatEvent(final boolean async, final Player who, final String message, final Set<Player> players) {
        super(who, async);
        this.message = message;
        recipients = players;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) throws IllegalFormatException, NullPointerException {
        try {
            String.format(format, getPlayer(), message);
        } catch (RuntimeException ex) {
            ex.fillInStackTrace();
            throw ex;
        }

        this.format = format;
    }

    public Set<Player> getRecipients() {
        return recipients;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
