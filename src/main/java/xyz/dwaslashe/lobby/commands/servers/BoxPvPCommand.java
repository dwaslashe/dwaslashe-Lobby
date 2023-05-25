package xyz.dwaslashe.lobby.commands.servers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.List;

public class BoxPvPCommand extends Command {
    public BoxPvPCommand() {
        super("boxpvp", "/boxpvp", "");
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender s, String[] args) {
        Player p = (Player)s;
        Api.sendPlayerToServer(p, "BOXPVP");
    }

}