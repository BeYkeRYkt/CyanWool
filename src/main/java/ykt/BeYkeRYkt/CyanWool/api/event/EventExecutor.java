package ykt.BeYkeRYkt.CyanWool.api.event;

public interface EventExecutor {

    public void execute(Event event) throws EventException;
}