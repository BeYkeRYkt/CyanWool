package net.CyanWool.api.world;

import net.CyanWool.api.scheduler.CyanTask;

public class WorldEntry {

    private final World world;
    private CyanTask task;

    WorldEntry(World world, CyanTask task) {
        this.world = world;
        this.task = task;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * @return the task
     */
    public CyanTask getTask() {
        return task;
    }
}