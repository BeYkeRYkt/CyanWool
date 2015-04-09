package net.CyanWool.api.scheduler;

import java.util.concurrent.ScheduledFuture;

public class Task {

    private ScheduledFuture<?> scheduledFuture;
    private int id;

    public Task(int id, ScheduledFuture<?> scheduledFuture) {
        this.id = id;
        this.scheduledFuture = scheduledFuture;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.scheduledFuture.cancel(mayInterruptIfRunning);
    }

    public boolean cancel() {
        return cancel(false);
    }

    public ScheduledFuture<?> getScheduledFuture() {
        return this.scheduledFuture;
    }

    public boolean isCancelled() {
        return getScheduledFuture().isCancelled();
    }

    public boolean isDone() {
        return getScheduledFuture().isDone();
    }

    public int getID() {
        return id;
    }
}