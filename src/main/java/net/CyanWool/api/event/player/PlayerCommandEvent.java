package net.CyanWool.api.event.player;

import java.util.HashSet;
import java.util.Set;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.event.Cancellable;
import net.CyanWool.api.event.HandlerList;

import org.apache.commons.lang.Validate;

public class PlayerCommandEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private String message;
    private String format = "<%1$s> %2$s";
    private final Set<Player> recipients;

    public PlayerCommandEvent(final Player player, final String message) {
        super(player);
        this.recipients = new HashSet<Player>(CyanWool.getPlayers());
        this.message = message;
    }

    public PlayerCommandEvent(final Player player, final String message, final Set<Player> recipients) {
        super(player);
        this.recipients = recipients;
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String command) throws IllegalArgumentException {
        Validate.notNull(command, "Command cannot be null");
        Validate.notEmpty(command, "Command cannot be empty");
        this.message = command;
    }

    public void setPlayer(final Player player) throws IllegalArgumentException {
        Validate.notNull(player, "Player cannot be null");
        this.player = player;
    }

    @Deprecated
    public String getFormat() {
        return format;
    }

    @Deprecated
    public void setFormat(final String format) {
        // Oh for a better way to do this!
        try {
            String.format(format, player, message);
        } catch (RuntimeException ex) {
            ex.fillInStackTrace();
            throw ex;
        }

        this.format = format;
    }

    @Deprecated
    public Set<Player> getRecipients() {
        return recipients;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
