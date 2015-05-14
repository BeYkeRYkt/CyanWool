package net.devwool.cyanwool.api.command;

public abstract class Command {

    private String name;
    private String description;
    private boolean consoleAccess;

    public Command(String cmdName, String desc, boolean consoleAccess) {
        this.name = cmdName;
        this.description = desc;
        this.consoleAccess = consoleAccess;
    }

    public abstract void execute(ICommandSender sender, String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isConsoleAccess() {
        return consoleAccess;
    }
}