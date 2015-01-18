package ykt.BeYkeRYkt.CyanWool.api.command;

import ykt.BeYkeRYkt.CyanWool.api.Server;

public interface ICommandSender {

    public void sendMessage(String message);

    public Server getServer();

    public String getName();

    public boolean isPlayer();
}