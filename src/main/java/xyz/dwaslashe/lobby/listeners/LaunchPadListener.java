package xyz.dwaslashe.lobby.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LaunchPadListener implements Listener {
    public LaunchPadListener() {
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Block block = e.getTo().getBlock();

        if (block.getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
            p.setVelocity(p.getLocation().getDirection().multiply(1.5));
            p.setVelocity(new org.bukkit.util.Vector(p.getVelocity().getX(), 0.2, p.getVelocity().getZ()));
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1.0F, 1.0F);
        }
    }
}
