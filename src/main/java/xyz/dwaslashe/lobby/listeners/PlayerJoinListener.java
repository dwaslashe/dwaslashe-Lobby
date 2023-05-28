package xyz.dwaslashe.lobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import xyz.dwaslashe.lobby.utils.ItemApi;

public class PlayerJoinListener implements Listener {

    public static ItemStack arrow = new ItemApi(Material.ARROW, (short)0)
            .setAmount(1)
            .setName("&#21F8F6Szczała")
            .toIS();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World world = Bukkit.getWorld("world");
        p.setHealth(20.0);
        p.setFoodLevel(20);
        p.getInventory().clear();
        //p.teleport(new Location(world, 118, 84, 118, 0 ,0));//hotmc
        p.teleport(new Location(world, 0.5, 65, -1.5, -90 ,0));//wywrotkamc
        p.getInventory().setItem(0, PlayerHubListener.compass);
        p.getInventory().setItem(1, PlayerHubListener.socialmedia);
        p.getInventory().setItem(6, PlayerHubListener.changelobby);
        p.getInventory().setItem(2, PlayerTeleportBowListener.teleport);
        p.getInventory().setItem(8, SwordPvPListener.sword);
        p.getInventory().setItem(9, arrow);

        e.setJoinMessage(null);
    }
}
