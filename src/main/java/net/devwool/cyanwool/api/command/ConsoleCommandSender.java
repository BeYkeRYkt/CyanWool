package net.devwool.cyanwool.api.command;

import net.devwool.cyanwool.api.CyanWool;
import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.utils.ChatColors;

public class ConsoleCommandSender implements ICommandSender {

    @Override
    public void sendMessage(String message) {
        String mess = ChatColors.stripColor(message);
        getServer().getLogger().info(mess);
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

    @Override
    public String getLangCode() {
        return "en_US";
    }
}