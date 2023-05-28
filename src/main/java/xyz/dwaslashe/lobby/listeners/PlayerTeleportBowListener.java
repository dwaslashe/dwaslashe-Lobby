package xyz.dwaslashe.lobby.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import xyz.dwaslashe.lobby.utils.ItemApi;

import java.util.Arrays;

public class PlayerTeleportBowListener implements Listener {

    public static ItemStack teleport = new ItemApi(Material.BOW, (short) 0)
            .setName("&#349cebTeleport")
            .addEnchant(Enchantment.ARROW_INFINITE, 1)
            .setUnbreakable(true)
            .setLore(Arrays.asList("", " &#FBFD8C&nNaciągnij łuk aby przeteleportować się!"))
            .toIS();

    @EventHandler
    public void onArrowShoot(ProjectileHitEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player))
            return;
        if (!(e.getEntity() instanceof Arrow))
            return;
        Player player = (Player) e.getEntity().getShooter();
        Location loc = e.getEntity().getLocation();
        loc.setYaw(player.getLocation().getYaw());
        loc.setPitch(player.getLocation().getPitch());
        player.playSound(loc, Sound.ENTITY_CAT_AMBIENT, 1.0F, 1.0F);
        player.teleport(loc);
        e.getEntity().remove();
    }

    @EventHandler
    public void onHitShoot(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow) {
            e.setCancelled(true);
        }
    }
}
