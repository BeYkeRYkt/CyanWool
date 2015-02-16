package net.CyanWool.api.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.CyanWool.api.Server;
import net.CyanWool.api.theards.AsyncSchedulerThread;
import net.CyanWool.api.world.World;

public class TaskManager {
    
    private static final int PULSE_EVERY = 50;
    private final Server server;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final List<Task> newTasks = new ArrayList<Task>();
    private final List<Task> tasks = new ArrayList<Task>();
    
    public TaskManager(Server server) {
        this.server = server;
    }
    
    public void start() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    pulse();
                } catch (Throwable t) {
                    server.getLogger().error("Uncaught exception in task scheduler.", t);
                }
            }
        }, 0, PULSE_EVERY, TimeUnit.MILLISECONDS);
    }
    
    public void startTask(Task task) {
        synchronized (newTasks) {
            newTasks.add(task);
        }
    }
    
    public void startAsyncTask(Task task){
        new AsyncSchedulerThread(task).start(); //TODO
    }
    
    public void pulse() {
        // handle incoming messages
        //server.getSessionRegistry().pulse();

        // handle tasks
        synchronized (newTasks) {
            for (Task task : newTasks) {
                tasks.add(task);
            }
            newTasks.clear();
        }

        for (Iterator<Task> it = tasks.iterator(); it.hasNext(); ) {
            Task task = it.next();
            if (!task.pulse()) {
                it.remove();
            }
        }

        // handle general game logic
        for(World w: server.getWorlds()){
            w.onTick();
        }
        
    }

    public void stop() {
        tasks.clear();
        executor.shutdownNow();
    }

    
}