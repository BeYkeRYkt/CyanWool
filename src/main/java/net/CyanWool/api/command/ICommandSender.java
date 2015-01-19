package net.CyanWool.api.command;

import net.CyanWool.api.Server;

public interface ICommandSender {

    public void sendMessage(String message);

    public Server getServer();

    public String getName();

    public boolean isPlayer();
}