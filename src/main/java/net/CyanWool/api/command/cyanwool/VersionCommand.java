package net.CyanWool.api.command.cyanwool;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.command.Command;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.utils.ChatColors;

public class VersionCommand extends Command {

    public VersionCommand() {
        super("version", "About server", true);
    }

    @Override
    public void execute(ICommandSender sender, String[] args) {
        String version = "";
        
        if (sender.isPlayer()) {
            version = ChatColors.WHITE + "This server is running " + ChatColors.AQUA + CyanWool.getModName() + ChatColors.WHITE + " version " + ChatColors.AQUA + CyanWool.getModVersion() + ChatColors.WHITE + " (Minecraft version: " + ChatColors.AQUA + CyanWool.getMCVersion() + ChatColors.WHITE + ")"; 
        }else{
            version = "This server is running " + CyanWool.getModName() + " version " + CyanWool.getModVersion() + " (Minecraft version: " + CyanWool.getMCVersion() + ")"; 
        }
        sender.sendMessage(version);
    }

}