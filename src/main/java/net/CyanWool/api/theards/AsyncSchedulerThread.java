package net.CyanWool.api.theards;

import net.CyanWool.api.task.Task;

/**
 * TESTING...
 * @author DinDev
 *
 */
public class AsyncSchedulerThread extends Thread {

    private Task task;

    public AsyncSchedulerThread(Task task) {
        this.task = task;
        setName("CyanAsyncScheduler=" + task.getId());
    }

    public void shutdown() {
      task.stop();
    }

    @Override
    public void run() {
        while (task.isRunning()) {
            task.pulse();
        }
    }

}