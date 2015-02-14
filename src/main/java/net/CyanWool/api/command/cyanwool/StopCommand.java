package net.CyanWool.api.command.cyanwool;

import net.CyanWool.api.command.Command;
import net.CyanWool.api.command.ICommandSender;

public class StopCommand extends Command {

    public StopCommand() {
        super("stop", "Shutdown command server!", true);
    }

    @Override
    public void execute(ICommandSender sender, String[] args) {
        // Maybe...
        sender.getServer().shutdown();
    }

}