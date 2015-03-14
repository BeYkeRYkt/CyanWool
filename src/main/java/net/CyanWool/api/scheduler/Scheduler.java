package net.CyanWool.api.scheduler;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Server;

public final class Scheduler {

    private static final HashMap<Integer, CyanTask> tasks = new HashMap<Integer, CyanTask>();
    private static ScheduledExecutorService es;
    private int id;
    private Server server;

    public Scheduler(Server server) {
        this.server = server;
    }

    public void stop() {
        cancelAllTasks();
        getService().shutdownNow();
    }

    public CyanTask runAsyncTaskRepeat(Runnable r, long startAfter, long delay) {
        long afterTicks = startAfter * 50;
        long ticks = delay * 50;
        ScheduledFuture<?> sch = Scheduler.getService().scheduleWithFixedDelay(r, afterTicks, ticks, TimeUnit.MILLISECONDS);
        CyanTask task = new CyanTask(id, sch);
        tasks.put(id, task);
        id++;
        return task;
    }

    public CyanTask runAsyncTask(Runnable r, long delay) {
        long ticks = delay * 50;
        ScheduledFuture<?> sch = Scheduler.getService().schedule(r, ticks, TimeUnit.MILLISECONDS);
        CyanTask task = new CyanTask(id, sch);
        tasks.put(id, task);
        id++;
        return task;
    }

    public CyanTask runCallable(Callable<?> c, long delay) {
        long ticks = delay * 50;
        ScheduledFuture<?> sch = Scheduler.getService().schedule(c, ticks, TimeUnit.MILLISECONDS);
        CyanTask task = new CyanTask(id, sch);
        tasks.put(id, task);
        id++;
        return task;
    }

    public CyanTask getTask(int id) {
        return tasks.get(id);
    }

    public boolean cancelTask(int id) {
        if (getTask(id) != null) {
            getTask(id).cancel(false);
            return true;
        }
        return false;
    }

    public void cancelAllTasks() {
        for (CyanTask task : Scheduler.tasks.values()) {
            task.getScheduledFuture().cancel(false);
        }
        Scheduler.tasks.clear();
        try {
            Scheduler.getService().awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            CyanWool.getLogger().error("Error halting scheduler service", ex);
        }
    }

    public static ScheduledExecutorService getService() {
        if (Scheduler.es == null || Scheduler.es.isShutdown()) {
            Scheduler.es = Executors.newScheduledThreadPool(10);
        }
        return Scheduler.es;
    }

}