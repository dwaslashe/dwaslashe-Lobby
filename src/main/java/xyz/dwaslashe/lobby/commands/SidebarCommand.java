package xyz.dwaslashe.lobby.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dwaslashe.lobby.commands.managers.Command;

import java.util.List;

public class SidebarCommand extends Command {
    public SidebarCommand() {
        super("sidebar", "/sidebar", "", "scoreboard");
        setOnlyPlayer(true);
    }


    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if (args.length >= 0) {
            p.chat("/sb");
        }
    }
}
