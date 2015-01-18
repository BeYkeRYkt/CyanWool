package ykt.BeYkeRYkt.CyanWool.api.event;

import ykt.BeYkeRYkt.CyanWool.api.plugin.Plugin;

public class RegListener {

    private Listener listener;
    private EventPriority priority;
    private EventExecutor executor;
    private Plugin owner;

    public RegListener(Listener listener, EventExecutor executor, EventPriority priority, Plugin owner) {
        this.listener = listener;
        this.executor = executor;
        this.priority = priority;
        this.owner = owner;
    }

    public EventExecutor getExecutor() {
        return executor;
    }

    public Plugin getOwner() {
        return owner;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public Listener getListener() {
        return listener;
    }
}