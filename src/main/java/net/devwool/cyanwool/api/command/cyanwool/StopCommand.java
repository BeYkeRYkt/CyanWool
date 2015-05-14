package net.devwool.cyanwool.api.command.cyanwool;

import net.devwool.cyanwool.api.command.Command;
import net.devwool.cyanwool.api.command.ICommandSender;

public class StopCommand extends Command {

    public StopCommand() {
        super("stop", "Shutdown command server!", true);
    }

    @Override
    public void execute(ICommandSender sender, String[] args) {
        // Maybe...
        String message = "Server shutdown!";
        if (args[0] != null) {
            message = args[0];
        }
        sender.getServer().shutdown(message);
    }

}