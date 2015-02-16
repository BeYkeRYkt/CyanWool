package net.CyanWool.api.task;

public abstract class Task {

    private int ticks;
    private int counter;
    private boolean running = true;
    private int id;

    private boolean repeat;

    public Task(int ticks, int id) {
        this.setTicks(ticks);
        this.counter = ticks;
        this.id = id;
    }

    public void setTicks(int ticks) {
        if (ticks < 1)
            throw new IllegalArgumentException("The number of ticks must be positive.");

        this.ticks = ticks;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStopped() {
        return !running;
    }

    public abstract void execute();

    public boolean pulse() {
        if (!running)
            return false;

        if (--counter == 0) {
            execute();
            if (isRepeat()) {
                counter = ticks;
            } else {
                stop();
            }
        }

        return running;
    }

    public int getId() {
        return id;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

}