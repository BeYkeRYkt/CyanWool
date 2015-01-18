package ykt.BeYkeRYkt.CyanWool.api.event.player;

import ykt.BeYkeRYkt.CyanWool.api.entity.Player;
import ykt.BeYkeRYkt.CyanWool.api.event.Event;

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