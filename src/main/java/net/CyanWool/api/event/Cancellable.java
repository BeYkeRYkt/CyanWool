package net.CyanWool.api.event;

public interface Cancellable {

    public boolean isCancelled();

    public void setCancelled(boolean cancel);
}
