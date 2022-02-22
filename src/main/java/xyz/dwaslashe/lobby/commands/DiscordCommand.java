package xyz.dwaslashe.lobby.commands;

import org.bukkit.command.CommandSender;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.List;

public class DiscordCommand extends Command {
    public DiscordCommand() {
        super("discord", "/discord", "", "dc");
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender sender, String[] args) {
        sender.sendMessage(Api.fixColor(" &8>> &7Link do naszego discorda &b" + Main.pluginConfig.getMessages().getDiscord()));
    }
}
