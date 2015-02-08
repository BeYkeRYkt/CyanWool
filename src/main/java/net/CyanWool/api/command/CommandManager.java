package net.CyanWool.api.command;

import java.util.Arrays;
import java.util.HashMap;

public class CommandManager {

    public HashMap<String, Command> commands = new HashMap<String, Command>();

    public CommandManager() {
        register(new StopCommand());
        register(new PluginsCommand());
    }

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public void unregister(String name) {
        commands.remove(name);
    }

    public void dispatchCommand(ICommandSender sender, String rawMessage) {
        String[] args = rawMessage.split(" ");
        String label = args[0];
        Command command = commands.get(label);
        if (command == null) {
            String text = sender.getServer().getServerConfiguration().getUnknownCommandMessage();
            sender.sendMessage(text);

            return;
        }
        if (sender instanceof ConsoleCommandSender && !command.isConsoleAccess()) {
            String text = sender.getServer().getServerConfiguration().getNoConsoleMessage();
            sender.sendMessage(text);
            return;
        }
        String[] rangedArgs = Arrays.copyOfRange(args, 1, args.length);
        command.execute(sender, rangedArgs);
    }
}