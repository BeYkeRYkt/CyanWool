package ykt.BeYkeRYkt.CyanWool.api.event;

public abstract class Event {

    private String name;
    private final boolean async;

    public Event() {
        this(false);
    }

    public Event(boolean isAsync) {
        this.async = isAsync;
    }

    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    public abstract HandlerList getHandlers();

    public final boolean isAsynchronous() {
        return async;
    }

    public enum Result {
        DENY, DEFAULT, ALLOW;
    }
}
