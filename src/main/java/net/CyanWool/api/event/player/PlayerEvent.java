package net.CyanWool.api.event.player;

import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.event.Event;

public abstract class PlayerEvent extends Event {

    protected Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    PlayerEvent(final Player who, boolean async) {
        super(async);
        player = who;

    }

    public Player getPlayer() {
        return player;
    }
}