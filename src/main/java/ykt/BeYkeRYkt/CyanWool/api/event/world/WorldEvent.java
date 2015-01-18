package ykt.BeYkeRYkt.CyanWool.api.event.world;

import ykt.BeYkeRYkt.CyanWool.api.event.Event;
import ykt.BeYkeRYkt.CyanWool.api.world.World;

public abstract class WorldEvent extends Event {

    private final World world;

    public WorldEvent(final World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}