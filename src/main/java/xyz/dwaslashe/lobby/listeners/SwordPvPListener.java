package xyz.dwaslashe.lobby.listeners;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.utils.Api;
import java.util.*;

@Getter @Setter
public class SwordPvPListener implements Listener {

    public HashMap<Player, BukkitRunnable> pvpTask = new HashMap<>();
    public HashMap<Player, BukkitRunnable> pvpTask2 = new HashMap<>();
    public HashMap<Player, Boolean> flying = new HashMap<>();
    public ArrayList<Player> pvp = new ArrayList<>();

    private ItemStack sword = new ItemStack((Material.DIAMOND_SWORD));

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
        if (e.getEntity().getKiller() != null) {
            Player p = e.getEntity();
            Player killer = p.getKiller();

            if (p.getMetadata("pvp").get(0).asBoolean() && killer.getMetadata("pvp").get(0).asBoolean()) {
                if (4 != -1) {
                    killer.setHealth(clamp(killer.getHealth() + 4, 0.0, killer.getMaxHealth()));
                    Api.sendMessage(killer, Main.pluginConfig.getMessages().getPrefix() + "&aZabiłeś &e" + p.getName() + " &ai zyskałeś &c4 serca");
                }
                p.setHealth(p.getMaxHealth());

                this.pvp.remove(p);
                this.pvpTask.remove(p);
                this.pvpTask2.remove(p);

                p.getInventory().setHeldItemSlot(0);

                Api.sendMessage(p, Main.pluginConfig.getMessages().getPrefix() + "&cGracz &e" + killer + " &czabił Cie!");
                Api.sendMessage(killer, Main.pluginConfig.getMessages().getPrefix() + "&aZabiłeś &e" + p);

                p.getInventory().setHelmet(new ItemStack(Material.AIR));
                p.getInventory().setChestplate(new ItemStack(Material.AIR));
                p.getInventory().setLeggings(new ItemStack(Material.AIR));
                p.getInventory().setBoots(new ItemStack(Material.AIR));
                e.setDeathMessage(null);
            }
        }
    }

    public double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        ItemStack held = e.getPlayer().getInventory().getItem(e.getNewSlot());
        System.out.println("item held: -1");
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
                            Api.sendMessage(p, Main.pluginConfig.getMessages().getPrefix() + "tol " + time);
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
            Api.sendMessage(p, Main.pluginConfig.getMessages().getPrefix() + "&aWalka została &ewłączona!");

            p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

            flying.put(p, p.getAllowFlight());
            p.setAllowFlight(false);
        } else {
            if (this.pvp.contains(p)) {
                this.pvp.remove(p);
                if (this.pvpTask.containsKey(p)) {
                    BukkitRunnable bukkitRunnable = this.pvpTask.get(p);
                    bukkitRunnable.cancel();
                }

                if (this.pvpTask2.containsKey(p)) {
                    BukkitRunnable bukkitRunnable = this.pvpTask2.get(p);
                    bukkitRunnable.cancel();
                }
                Api.sendMessage(p, Main.pluginConfig.getMessages().getPrefix() + "&cWalka została &ewyłączona!");
            }

            p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
            p.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
            p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
            p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
            p.setAllowFlight(flying.get(p) != null && flying.get(p));
            flying.remove(p);
        }
        p.setHealth(p.getMaxHealth());
    }

    @EventHandler
    public void onClick(InventoryInteractEvent e) {
        ItemStack item = e.getInventory().getItem(1);
        System.out.println("click event: 0");
        if (item != null && item.getType().equals(Material.DIAMOND_SWORD)) {
            System.out.println("click event: 1");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        ItemStack item = e.getItemDrop().getItemStack();
        System.out.println("drop item: 0");
        if (item.getType().equals(Material.DIAMOND_SWORD)) {
            System.out.println("drop item: 1");
            e.setCancelled(true);
        }
    }

}
