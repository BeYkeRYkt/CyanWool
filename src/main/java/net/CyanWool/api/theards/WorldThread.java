package net.CyanWool.api.theards;

import net.CyanWool.api.world.World;

public class WorldThread extends Thread {

    private World world;
    private boolean running;

    public WorldThread(World world) {
        this.world = world;
        this.running = true;
        setName("CyanWorld-" + world.getName());
    }

    public void shutdown() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            world.onTick();
            try {
                sleep(50); // 1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                currentThread().interrupt();
            }
        }
    }

}