package net.CyanWool.api.world;

import net.CyanWool.api.scheduler.Task;

public class WorldEntry {

    private final World world;
    private Task task;

    WorldEntry(World world, Task task) {
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
    public Task getTask() {
        return task;
    }
}