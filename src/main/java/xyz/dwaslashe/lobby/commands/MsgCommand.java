package xyz.dwaslashe.lobby.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MsgCommand extends Command implements Listener {
    static HashMap<Player, Player> lastMsg = new HashMap();
    public MsgCommand() {
        super("msg", "/msg <nick> <tresc>", "");
        setOnlyPlayer(true);
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        if (args.length == 1) return Collections.singletonList("[players]");
        return null;
    }

    @Override
    public void commandExecute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length < 2) {
            wrongUsage();
        } else if (args.length > 1) {
            Player p2 = Bukkit.getPlayer(args[0]);
            if (p2 == null) {
                offlinePlayer();
                return;
            }

            String msg = "";

            for (int lenght = 1; lenght < args.length; ++lenght) {
                msg = msg + args[lenght] + " ";
            }

            lastMsg.put(p, p2);
            lastMsg.put(p2, p);
            p.sendMessage(Api.fixColor("&8[ " + ChatColor.of("#edc72f") + "TY &8> " + ChatColor.of("#edc72f") + p2.getName() + " &8] &8» " + ChatColor.of("#edc72f")) + Api.fixColor(msg));
            p2.sendMessage(Api.fixColor("&8[ " + ChatColor.of("#edc72f") + p.getName() + " &8> " + ChatColor.of("#edc72f") + "TY &8] &8» " + ChatColor.of("#edc72f")) + Api.fixColor(msg));
        }
    }

    public static HashMap<Player, Player> getLastMsg() {
        return lastMsg;
    }

}