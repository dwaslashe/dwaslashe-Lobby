package xyz.dwaslashe.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.List;

public class ListCommand extends Command {
    public ListCommand() {
        super("list", "/list", "", "online");
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                stringBuilder.append(", ").append(onlinePlayer.getName());
            }
            sender.sendMessage(Api.fixColor(" &8>> &7Graczy &f" + Bukkit.getOnlinePlayers().size() + "&8/&7100 &8(&f" + stringBuilder.toString().replaceFirst(", ", "") + "&8)"));
        }

        if (args.length > 0) {
            wrongUsage();
        }
    }
}