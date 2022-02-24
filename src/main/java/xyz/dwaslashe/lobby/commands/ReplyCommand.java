package xyz.dwaslashe.lobby.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.List;

public class ReplyCommand extends Command {
    public ReplyCommand() {
        super("reply", "/r <wiadomosc>", "", "r");
        setOnlyPlayer(true);
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length < 1) {
            wrongUsage();
        } else {
            if (args.length > 0) {
                Player p2 = (Player) xyz.dwaslashe.lobby.commands.MsgCommand.getLastMsg().get(p);
                if (p2 == null) {
                    offlinePlayer();
                    return;
                }

                String msg = "";

                for (int lenght = 0; lenght < args.length; ++lenght) {
                    msg = msg + args[lenght] + " ";
                }

                xyz.dwaslashe.lobby.commands.MsgCommand.getLastMsg().put(p, p2);
                MsgCommand.getLastMsg().put(p2, p);
                p.sendMessage(Api.fixColor("&8[ " + ChatColor.of("#edc72f") + "TY &8> " + ChatColor.of("#edc72f") + p2.getName() + " &8] &8» " + ChatColor.of("#edc72f")) + Api.fixColor(msg));
                p2.sendMessage(Api.fixColor("&8[ " + ChatColor.of("#edc72f") + p.getName() + " &8> " + ChatColor.of("#edc72f") + "TY &8] &8» " + ChatColor.of("#edc72f")) + Api.fixColor(msg));
            }
        }
    }
}
