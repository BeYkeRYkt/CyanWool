package ykt.BeYkeRYkt.CyanWool.api.world;

import ykt.BeYkeRYkt.CyanWool.api.theards.WorldThread;

public class WorldEntry {

    private final World world;
    private WorldThread task;

    WorldEntry(World world) {
        this.world = world;
    }

    /**
     * @return the task
     */
    public WorldThread getTask() {
        return task;
    }

    /**
     * @param task
     *            the task to set
     */
    public void setTask(WorldThread task) {
        this.task = task;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }
}