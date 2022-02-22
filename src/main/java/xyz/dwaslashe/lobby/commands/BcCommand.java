package xyz.dwaslashe.lobby.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.Arrays;
import java.util.List;

public class BcCommand extends Command {
    public BcCommand() {
        super("bc", "/bc <chat, title, bar, all>", "", "broadcast");
        setPermission("core.command.bc");
    }

    @Override
    public void commandExecute(CommandSender commandSender, String[] args) {
        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("chat")) {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Api.fixColor(ChatColor.of("#E8E8E8") + "&lOGLOSZENIE &8>> &r" + StringUtils.join(args, " ", 1, args.length))));
            } else if (args[0].equalsIgnoreCase("bar")) {
                String msg = "";
                for (int lenght = 1; lenght < args.length; lenght++)
                    msg = String.valueOf(msg) + args[lenght] + " ";
                for (Player all : Bukkit.getOnlinePlayers())
                    all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Api.fixColor("&8>> &r" + msg + "&8<<")));
            } else if (args[0].equalsIgnoreCase("title")) {
                String msg = "";
                for (int lenght = 1; lenght < args.length; lenght++)
                    msg = String.valueOf(msg) + args[lenght] + " ";
                for (Player all : Bukkit.getOnlinePlayers())
                    all.sendTitle(Api.fixColor(ChatColor.of("#E8E8E8") + "&f&lOGLOSZENIE"), Api.fixColor("&8>> &r" + msg + " &8<<"));
            } else if (args[0].equalsIgnoreCase("all")) {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Api.fixColor(ChatColor.of("#E8E8E8") + "&f&lOGLOSZENIE &8>> &r" + StringUtils.join(args, " ", 1, args.length))));
                String msg = "";
                for (int lenght = 1; lenght < args.length; lenght++)
                    msg = String.valueOf(msg) + args[lenght] + " ";
                for (Player all : Bukkit.getOnlinePlayers())
                    all.sendTitle(Api.fixColor(ChatColor.of("#E8E8E8") + "&f&lOGLOSZENIE"), Api.fixColor("&8>> &r" + msg + " &8<<"));
                for (Player all : Bukkit.getOnlinePlayers())
                    all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Api.fixColor("&8>> &r" + msg + "&8<<")));
            } else wrongUsage();
        }
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        if(args.length == 1) return Api.startsWith(Arrays.asList("chat", "title", "bar", "all"), args[0]);
        return null;
    }
}
