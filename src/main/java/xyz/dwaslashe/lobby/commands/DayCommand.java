package xyz.dwaslashe.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.List;

public class DayCommand extends Command {
    public DayCommand() {
        super("day", "/day", "", "dzien");
        setPermission("core.command.day");
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender s, String[] args) {
        if (!(s instanceof Player)) {
            Bukkit.getWorld("world").setTime(0);
            Api.sendMessage(s, "&aPomyślnie zmieniłeś pogode");
        } else {
            final Player p = (Player) s;
            Api.sendMessage(p, Main.pluginConfig.getMessages().getPrefix() + "&aPomyślnie zmieniłeś pogode");
            Bukkit.getWorld("world").setTime(0);
        }
    }

}
