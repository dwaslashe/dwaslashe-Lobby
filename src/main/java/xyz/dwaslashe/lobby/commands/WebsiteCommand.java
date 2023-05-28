package xyz.dwaslashe.lobby.commands;

import org.bukkit.command.CommandSender;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.List;

public class WebsiteCommand extends Command {
    public WebsiteCommand() {
        super("website", "/strona", "", "strona");
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender sender, String[] args) {
        if (args.length >= 0) {
            sender.sendMessage(Api.fixColor(" &8>> &aStrona WWW &#e6cf3c" + Main.pluginConfig.getMessages().getWebsite()));
        }
    }
}
