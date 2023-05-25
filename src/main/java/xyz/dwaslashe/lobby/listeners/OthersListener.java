package xyz.dwaslashe.lobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.commands.ChatCommand;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OthersListener implements Listener {
    HashMap<Player, String> previousMessages = new HashMap<>();

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
    public void onPlayerChatSameMessage(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (previousMessages.containsKey(player)) {
            if (message.equalsIgnoreCase(previousMessages.get(player))) {
                Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&cNie możesz wysłać znowu takiej samej wiadomości!");
                event.setCancelled(true);
            }
        }
        previousMessages.put(player, message);
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        if (!ChatCommand.disablechat.get(ChatCommand.TYPE.WRITABLE)) {
            e.setCancelled(true);
            Api.sendMessage(p, Main.pluginConfig.getMessages().getPrefix() + "&cCzat jest wyłączony!");
        }
    }

    @EventHandler
    public void onCancelFallDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            } else if (e.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                e.setCancelled(true);
            } else if (e.getCause() == EntityDamageEvent.DamageCause.CRAMMING) {
                e.setCancelled(true);
            } else if (e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
                e.setCancelled(true);
            } else if (e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void setWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(false);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("core.spawn.bypass")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onReSpawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        World world = Bukkit.getWorld("world");
        p.teleport(new Location(world, 0, 65, -0, -90 ,0));
    }

    @EventHandler
    public void onBrake(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("core.spawn.bypass")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("core.spawn.bypass")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLeveChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
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
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.getInventory().setItem(0, PlayerHubListener.compass);
        p.getInventory().setItem(1, PlayerHubListener.socialmedia);
        p.getInventory().setItem(2, PlayerTeleportBowListener.teleport);
        p.getInventory().setItem(8, SwordPvPListener.sword);
        p.getInventory().setItem(9, PlayerJoinListener.arrow);
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
