package xyz.dwaslashe.lobby.listeners;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.dwaslashe.lobby.Main;
import xyz.dwaslashe.lobby.commands.MediaCommand;
import xyz.dwaslashe.lobby.helpers.InventoryHelper;
import xyz.dwaslashe.lobby.utils.Api;
import xyz.dwaslashe.lobby.utils.ItemApi;

import java.util.Arrays;

@Getter @Setter
public class PlayerHubListener implements Listener {

    public static ItemStack compass = new ItemApi(Material.COMPASS, (short)0)
            .setName("&#FFF01FWybierz tryb &8(&7prawy przycisk&8)")
            .setLore(Arrays.asList("", " &f&nKliknij aby wybrać tryb!"))
            .toIS();
    public static ItemStack socialmedia = new ItemApi(Material.BOOK, (short)0)
            .setName("&#39FF14Media &8(&7prawy przycisk&8)")
            .setLore(Arrays.asList("", " &f&nKliknij aby zobaczyć nasze socialmedia!"))
            .toIS();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() != null && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() != null && e.getItem().equals(compass)) {
                openGui(0, p);
                e.setCancelled(true);
            } else if (e.getItem() != null &&e.getItem().equals(socialmedia)) {
                MediaCommand.openGui(0, p);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getClickedInventory().getType() != null && e.getClickedInventory().getType() == InventoryType.PLAYER) {
            if (!p.hasPermission("core.spawn.bypass")) {
                e.setResult(Event.Result.DENY);
                e.setCancelled(true);
            }
        }
    }

    private void openGui(int guiID, Player player) {
        //0
        if (guiID == 0) {
            InventoryHelper inventoryHelper = new InventoryHelper(player, "Wybierz Tryb", 4);

            ItemStack glass_black = inventoryHelper.prepareItemStack(Material.BLACK_STAINED_GLASS_PANE, itemStack -> {
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(" ");
                });
            });

            ItemStack glass_lime = inventoryHelper.prepareItemStack(Material.LIME_STAINED_GLASS_PANE, itemStack -> {
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor("&#39FF14WŁĄCZONY"));
                });
            });

            ItemStack glass_red = inventoryHelper.prepareItemStack(Material.RED_STAINED_GLASS_PANE, itemStack -> {
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor("&#FF3131WYŁĄCZONY"));
                });
            });

            ItemStack survival1 = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5MTk2YjMzMGM2Yjg5NjJmMjNhZDU2MjdmYjZlY2NlNDcyZWFmNWM5ZDQ0Zjc5MWY2NzA5YzdkMGY0ZGVjZSJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#39FF14&lSURVIVAL + EKONOMIA &8(&e%bungee_survival% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &#39FF14Serwer &#FFF01F&nwywrotkamc.pl",
                            "",
                            " &7Zalecana wersja &a1.18.1",
                            " &7Serwer wspiera wersje &a1.16.5 - 1.18.1",
                            " &7Data rozpoczęcia edycji &a17.12.2021",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            ItemStack survival2 = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5MTk2YjMzMGM2Yjg5NjJmMjNhZDU2MjdmYjZlY2NlNDcyZWFmNWM5ZDQ0Zjc5MWY2NzA5YzdkMGY0ZGVjZSJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#39FF14&lSURVIVAL + EKONOMIA &8(&e%bungee_survivalpajacemc% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &#39FF14Serwer &#FFF01F&npajacemc.pl",
                            "",
                            " &7Zalecana wersja &a1.18.1",
                            " &7Serwer wspiera wersje &a1.16.5 - 1.18.1",
                            " &7Data rozpoczęcia edycji &a18.02.2022",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            ItemStack bedwars = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmZiMjkwYTEzZGY4ODI2N2VhNWY1ZmNmNzk2YjYxNTdmZjY0Y2NlZTVjZDM5ZDQ2OTcyNDU5MWJhYmVlZDFmNiJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&f&lBEDWARS &8(&e%bungee_bedwars% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &7Serwer wspiera wersje &f1.8.8 - 1.18.1",
                            " &7Data rozpoczęcia edycji &f11.02.2022",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            ItemStack practice = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2NTM0MTM1M2MwMjllOWI2NTVmNGY1NzkzMWFlNmFkYzJjN2E3M2M2NTc5NDVkOTQ1YTMwNzY0MWQzNzc4In19fQ==");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#FF3131&lPRACTICE &8(&e%bungee_practice% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &#39FF14Serwer &#FFF01F&npvpplay.pl",
                            "",
                            " &7Zalecana wersja &a1.8.8",
                            " &7Serwer wspiera wersje &a1.8.8 - 1.18.1",
                            " &7Data rozpoczęcia edycji &ax.02.2022",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            ItemStack skyblock = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjAyYzk5Y2I5NjY3NDZiMGUzZDE2OTczZmMyY2RjZTRlNDBiODdhODZjMDVlMGE1MDIxZjM1YTAxOTJhNDBiMiJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&d&lSKYBLOCK &8(&e%bungee_skyblock% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &#39FF14Serwer &#FFF01F&nskymrowa.pl",
                            "",
                            " &7Zalecana wersja &a1.8.8",
                            " &7Serwer wspiera wersje &a1.8.8 - 1.18.1",
                            " &7Data rozpoczęcia edycji &ax.02.2022",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            inventoryHelper.click(e -> {
                e.setCancelled(true);
                if (e.getSlot() == 11) {
                    player.getOpenInventory().close();
                    Api.sendPlayerToServer(player, "bedwars");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                } else if (e.getSlot() == 12) {
                    player.closeInventory();
                    Api.sendPlayerToServer(player, "survival");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                } else if (e.getSlot() == 13) {
                    player.closeInventory();
                    Api.sendPlayerToServer(player, "survivalpajacemc");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                } else if (e.getSlot() == 14) {
                    player.closeInventory();
                    Api.sendPlayerToServer(player, "practice");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                } else if (e.getSlot() == 15) {
                    player.closeInventory();
                    Api.sendPlayerToServer(player, "skyblock");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                }
            });

            inventoryHelper.setItemRange(0, 4 * 9, glass_black);
            inventoryHelper.setItem(11, bedwars);
            inventoryHelper.setItem(12, survival1);
            inventoryHelper.setItem(13, survival2);
            inventoryHelper.setItem(14, practice);
            inventoryHelper.setItem(15, skyblock);

            inventoryHelper.setItem(20, glass_lime);
            inventoryHelper.setItem(21, glass_lime);
            inventoryHelper.setItem(22, glass_lime);
            inventoryHelper.setItem(23, glass_red);
            inventoryHelper.setItem(24, glass_red);

            inventoryHelper.open(player);
        }
    }
}
