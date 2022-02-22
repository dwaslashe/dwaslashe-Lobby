package xyz.dwaslashe.lobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.help.HelpTopic;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.Arrays;
import java.util.List;

public class OthersListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        message = message.toLowerCase();
        List<String> wordsInMessage = Arrays.asList(message.split(" "));
        for (String word : Main.pluginConfig.getChat().getBlockwords().getWords()) {
            if (wordsInMessage.contains(word.toLowerCase())) {
                Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.pluginConfig.getChat().getBlockwords().getCommand().replace("{PLAYER}", p.getName()));
                });
                break;
            } else if (p.hasPermission("core.chat.block.bypass")) {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onCommandTabSend(PlayerCommandSendEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPermission("core.command.tabcomplete.bypass")) {
            for (String string : Main.pluginConfig.getChat().getBlocktabcommands()) {
                event.getCommands().remove(string);
            }
        }
    }

    @EventHandler
    public void onUnknownCommand(PlayerCommandPreprocessEvent e) {
        if (!(e.isCancelled())) {
            Player p = e.getPlayer();
            String msg = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if (topic == null) {
                p.sendTitle(Api.fixColor(Main.pluginConfig.getMessages().getIp()), Api.fixColor("&8>> &7Komenda &f" + msg + " &7nie istnieje &8<<"), 10, 40, 10);
                e.setCancelled(true);
            }
        }
    }
}
