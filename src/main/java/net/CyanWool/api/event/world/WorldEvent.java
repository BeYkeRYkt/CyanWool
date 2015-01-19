package net.CyanWool.api.event.world;

import net.CyanWool.api.event.Event;
import net.CyanWool.api.world.World;

public abstract class WorldEvent extends Event {

    private final World world;

    public WorldEvent(final World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}