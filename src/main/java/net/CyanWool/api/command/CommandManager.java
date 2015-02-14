package net.CyanWool.api.command;

import java.util.Arrays;
import java.util.HashMap;

import net.CyanWool.api.command.cyanwool.PluginsCommand;
import net.CyanWool.api.command.cyanwool.StopCommand;
import net.CyanWool.api.command.cyanwool.VersionCommand;

public class CommandManager {

    public HashMap<String, Command> commands = new HashMap<String, Command>();

    public CommandManager() {
        register(new StopCommand());
        register(new PluginsCommand());
        register(new VersionCommand());
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
            String text = "Unknown command. Type /help for help.";
            sender.sendMessage(text);

            return;
        }
        if (sender instanceof ConsoleCommandSender && !command.isConsoleAccess()) {
            String text = "This command is not accesible from console.";
            sender.sendMessage(text);
            return;
        }
        String[] rangedArgs = Arrays.copyOfRange(args, 1, args.length);
        command.execute(sender, rangedArgs);
    }
}