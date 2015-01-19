package net.CyanWool.api.theards;

import net.CyanWool.api.Server;

public class SchedulerThread extends Thread {

    private boolean running;
    private Server server;
    
    public SchedulerThread(Server server) {
        this.server = server;
        this.running = true;
        setName("CyanScheduler");
    }

    public void shutdown() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            // Scheduler ticks...
        }
    }

}