package net.CyanWool.scheduler;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Server;
import net.CyanWool.api.scheduler.Scheduler;
import net.CyanWool.api.scheduler.Task;

public final class CyanScheduler implements Scheduler {

    private final HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
    private ScheduledExecutorService es;
    private int id;
    private Server server;

    public CyanScheduler(Server server) {
        this.setServer(server);
    }

    @Override
    public Task runTaskRepeat(Runnable r, long startAfter, long delay) {
        long afterTicks = startAfter * 50;
        long ticks = delay * 50;
        ScheduledFuture<?> sch = getService().scheduleWithFixedDelay(r, afterTicks, ticks, TimeUnit.MILLISECONDS);
        Task task = new Task(id, sch);
        tasks.put(id, task);
        id++;
        return task;
    }

    @Override
    public Task runTask(Runnable r, long delay) {
        long ticks = delay * 50;
        ScheduledFuture<?> sch = getService().schedule(r, ticks, TimeUnit.MILLISECONDS);
        Task task = new Task(id, sch);
        tasks.put(id, task);
        id++;
        return task;
    }

    @Override
    public Task getTask(int id) {
        return tasks.get(id);
    }

    @Override
    public boolean cancelTask(int id) {
        if (getTask(id) != null) {
            return getTask(id).cancel(false);
        }
        return false;
    }

    @Override
    public void cancelAllTasks() {
        for (Task task : tasks.values()) {
            task.getScheduledFuture().cancel(false);
        }
        tasks.clear();
        try {
            getService().awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            CyanWool.getLogger().error("Error halting scheduler service", ex);
        }
    }

    public ScheduledExecutorService getService() {
        if (es == null || es.isShutdown()) {
            es = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        }
        return es;
    }

    /**
     * @return the server
     */
    @Override
    public Server getServer() {
        return server;
    }

    /**
     * @param server
     *            the server to set
     */
    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void shutdown() {
        cancelAllTasks();
        getService().shutdownNow();
    }

    @Override
    public int getLastId() {
        // TODO Auto-generated method stub
        return 0;
    }

}