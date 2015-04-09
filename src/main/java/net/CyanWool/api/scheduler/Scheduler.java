package net.CyanWool.api.scheduler;

import net.CyanWool.api.Server;

public interface Scheduler {

    public void shutdown();

    public Task runTask(Runnable r, long delay);

    public Task runTaskRepeat(Runnable r, long startAfter, long delay);

    // public Task runAsyncTask(Runnable r, long delay);

    // public Task runAsyncTaskRepeat(Runnable r, long startAfter, long delay);

    public Task getTask(int id);

    public boolean cancelTask(int id);

    public void cancelAllTasks();

    public Server getServer();

    public int getLastId();

}