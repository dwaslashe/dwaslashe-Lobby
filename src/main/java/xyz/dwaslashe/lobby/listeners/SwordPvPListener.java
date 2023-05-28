package xyz.dwaslashe.lobby.listeners;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.utils.Api;
import xyz.dwaslashe.lobby.utils.ItemApi;

import java.util.*;

@Getter @Setter
public class SwordPvPListener implements Listener {

    public HashMap<Player, BukkitRunnable> pvpTask = new HashMap<>();
    public HashMap<Player, BukkitRunnable> pvpTask2 = new HashMap<>();
    public ArrayList<Player> pvp = new ArrayList<>();

    public static ItemStack sword = new ItemApi(Material.DIAMOND_SWORD, (short)0)
            .addEnchant(Enchantment.DAMAGE_ALL, 6)
            .addEnchant(Enchantment.DURABILITY, 10)
            .setName("&#f72f3cWalka")
            .setUnbreakable(true)
            .setLore(Arrays.asList("", " &#FBFD8C&nNajedź na miecz aby zaczac walke!"))
            .toIS();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player dam = (Player) e.getDamager();
            if (!this.pvp.contains(p) || !this.pvp.contains(dam)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getDrops().clear();
        e.setDeathMessage(null);
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        ItemStack held = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if (sword.isSimilar(held)) {
            if (pvpTask.containsKey(p)) {
                pvpTask.get(p).cancel();
                pvpTask.remove(p);
                pvp.add(p);

                return;
            }
            if (pvpTask2.containsKey(p)) {
                return;
            }
            this.pvpTask2.put(p, new BukkitRunnable() {
                int time = 4;

                public void run() {
                    time--;
                    if (time == 0) {
                        pvpTask2.remove(p);
                        setPvP(p, true);
                        this.cancel();
                    } else {
                        if (sword.isSimilar(held)) {
                            //Api.sendMessage(p, Main.pluginConfig.getMessages().getPrefix() + "&aWalka zacznie się za &e" + time);
                            p.sendTitle(Api.fixColor("&#f72f3c&lWALKA"), Api.fixColor("&8>> &aWalka zacznie się za &#f7c52f" + time + " &8<<"));
                            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 10, 10);
                        } else this.cancel();
                    }
                }
            });
            this.pvpTask2.get(p).runTaskTimer(Main.getPlugin(), 0L, 20L);
        } else {
            setPvP(p, false);
        }
    }

    public void setPvP(Player p, boolean pvp) {
        if (pvp) {
            this.pvp.add(p);
            p.sendTitle(Api.fixColor("&#f72f3c&lWALKA"), Api.fixColor("&8>> &aWalka została &#f7c52fwłączona &8<<"));
            p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BREAK, 10, 10);

            p.getInventory().setHelmet(new ItemApi(Material.DIAMOND_HELMET, (short)0)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .setUnbreakable(true)
                    .setName("&bZbroja - Hełm")
                    .toIS());
            p.getInventory().setChestplate(new ItemApi(Material.DIAMOND_CHESTPLATE, (short)0)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .setUnbreakable(true)
                    .setName("&bZbroja - Napierśnik")
                    .toIS());
            p.getInventory().setLeggings(new ItemApi(Material.DIAMOND_LEGGINGS, (short)0)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .setUnbreakable(true)
                    .setName("&bZbroja - Spodnie")
                    .toIS());
            p.getInventory().setBoots(new ItemApi(Material.DIAMOND_BOOTS, (short)0)
                    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    .addEnchant(Enchantment.DURABILITY, 10)
                    .setUnbreakable(true)
                    .setName("&bZbroja - Buty")
                    .toIS());
        } else {
            if (this.pvp.contains(p)) {
                this.pvp.remove(p);
                p.sendTitle(Api.fixColor("&#f72f3c&lWALKA"), Api.fixColor("&8>> &aWalka została &#f7c52fwyłączona &8<<"));
                p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BREAK, 10, 10);
            }

            p.getInventory().setArmorContents(new ItemStack[4]);
            p.setHealth(20.0);
        }
        p.setHealth(p.getMaxHealth());
    }

}
