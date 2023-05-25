package xyz.dwaslashe.lobby.commands.servers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dwaslashe.lobby.commands.managers.Command;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.List;

public class PracticeCommand extends Command {
    public PracticeCommand() {
        super("practice", "/practice", "");
    }

    @Override
    public List<String> tabCompleteExecute(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public void commandExecute(CommandSender s, String[] args) {
        Player p = (Player)s;
        Api.sendPlayerToServer(p, "PRACTICE");
    }

}

