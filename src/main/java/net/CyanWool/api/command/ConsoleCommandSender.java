package net.CyanWool.api.command;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.Server;

public class ConsoleCommandSender implements ICommandSender {

    @Override
    public void sendMessage(String message) {
        getServer().getLogger().info(message);
    }

    @Override
    public Server getServer() {
        return CyanWool.getServer();
    }

    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public boolean isPlayer() {
        return false;
    }
}