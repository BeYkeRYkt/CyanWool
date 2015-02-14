package net.CyanWool.api.command.cyanwool;

import java.util.ArrayList;

import net.CyanWool.api.CyanWool;
import net.CyanWool.api.command.Command;
import net.CyanWool.api.command.ICommandSender;
import net.CyanWool.api.plugin.Plugin;
import net.CyanWool.api.utils.ChatColors;

public class PluginsCommand extends Command {

    public PluginsCommand() {
        super("plugins", "Plugin list", true);
    }

    @Override
    public void execute(ICommandSender sender, String[] args) {
        ArrayList<Plugin> pluginList = CyanWool.getPluginManager().getPlugins();
        String plugins = "";

        if (sender.isPlayer()) {
            plugins = ChatColors.AQUA + "Plugins" + ChatColors.WHITE + " (" + ChatColors.AQUA + pluginList.size() + ChatColors.WHITE + "): ";

            if (pluginList.size() >= 2) {
                for (Plugin plugin : pluginList) {
                    String at = plugin.getFileDescription().getName();
                    if (plugin.isEnable()) {
                        at = ChatColors.GREEN + at;
                    } else {
                        at = ChatColors.RED + at;
                    }

                    if (pluginList.indexOf(plugin) == (pluginList.size() - 1)) {
                        plugins += at;
                    } else {
                        plugins += at + ChatColors.WHITE + ", ";
                    }

                }
            } else if (pluginList.size() == 1) {
                Plugin plugin = pluginList.get(0);
                String at = plugin.getFileDescription().getName();
                if (plugin.isEnable()) {
                    at = ChatColors.GREEN + at;
                } else {
                    at = ChatColors.RED + at;
                }
                plugins += at;
            }
        } else {
            plugins = "Plugins (" + pluginList.size() + "): ";

            if (pluginList.size() >= 2) {
                for (Plugin plugin : pluginList) {
                    String at = plugin.getFileDescription().getName();
                    if (pluginList.indexOf(plugin) == (pluginList.size() - 1)) {
                        plugins += at;
                    } else {
                        plugins += at + ", ";
                    }

                }
            } else if (pluginList.size() == 1) {
                Plugin plugin = pluginList.get(0);
                String at = plugin.getFileDescription().getName();
                plugins += at;
            }
        }

        sender.sendMessage(plugins);
    }

}