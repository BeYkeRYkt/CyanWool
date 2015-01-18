package ykt.BeYkeRYkt.CyanWool.api.command;

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