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
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
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
            .setName("&#ebb434Wybierz tryb")
            .setLore(Arrays.asList("", " &#FBFD8C&nKliknij aby wybrać tryb!"))
            .toIS();
    public static ItemStack changelobby = new ItemApi(Material.NETHER_STAR, (short)0)
            .setName("&#e240f7Zmień hub")
            .setLore(Arrays.asList("", " &#FBFD8C&nKliknij aby zmienić serwer hub!"))
            .toIS();
    public static ItemStack socialmedia = new ItemApi(Material.BOOK, (short)0)
            .setName("&#65eb34Media")
            .setLore(Arrays.asList("", " &#FBFD8C&nKliknij aby zobaczyć nasze socialmedia!"))
            .toIS();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() != null && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() != null && e.getItem().equals(compass)) {
                openGui(0, p);
                e.setCancelled(true);
            } else if (e.getItem() != null && e.getItem().equals(socialmedia)) {
                MediaCommand.openGui(0, p);
                e.setCancelled(true);
            } else if (e.getItem() != null && e.getItem().equals(changelobby)) {
                openGui(1, p);
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onSwapItem(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
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
            InventoryHelper inventoryHelper = new InventoryHelper(player, "Wybierz tryb", 4);

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

            ItemStack dropsmpwywrotkamc = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU1ODdkYTdmZTczMzZlOGFiOWY3OTFlYTVlMmNmYzhhODI3Y2E5NTk1NjdlYjlkNTNhNjQ3YmFiZjk0OGQ1In19fQ==");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#0394fc&lDROP SMP &8(&e%bungee_dropsmp% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &7Zalecana wersja &91.19.2",
                            " &7Serwer wspiera wersje &91.19.3",
                            " &7Data rozpoczęcia edycji &9x.09.2022",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            ItemStack dropsmphotmc = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU1ODdkYTdmZTczMzZlOGFiOWY3OTFlYTVlMmNmYzhhODI3Y2E5NTk1NjdlYjlkNTNhNjQ3YmFiZjk0OGQ1In19fQ==");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#0394fc&lDROP SMP &8(&e%bungee_dropsmp% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &7Zalecana wersja &91.19.2",
                            " &7Serwer wspiera wersje &91.18.2 - 1.19.3",
                            " &7Data rozpoczęcia edycji &912.08.2022",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            ItemStack lifestealhotmc = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM0ZDBlNTc2NDlmZDY2MTkyZTA3MzE2MDVkYjA5MzBiMTA5ZDgxMjVkOGRlYWQ4YzFlYzBmZTlmN2Y3YWJjYyJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#FF3131&lLIFESTEAL SMP &8(&e%bungee_lifesteal% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &7Zalecana wersja &c1.19.2",
                            " &7Serwer wspiera wersje &c1.19.2",
                            " &7Data rozpoczęcia edycji &cx.08.2022",
                            "",
                            " &f&nStart trybu x.08.2022!"
                    )));
                });
            });

            ItemStack lifestealwywrotkamc = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM0ZDBlNTc2NDlmZDY2MTkyZTA3MzE2MDVkYjA5MzBiMTA5ZDgxMjVkOGRlYWQ4YzFlYzBmZTlmN2Y3YWJjYyJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#FF3131&lLIFESTEAL SMP &8(&e%bungee_lifesteal% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &7Zalecana wersja &c1.19.2",
                            " &7Serwer wspiera wersje &c1.18.2 - 1.19.3",
                            " &7Data rozpoczęcia edycji &c13.01.2023",
                            "",
                            " &f&nStart trybu 13.01.2023!"
                    )));
                });
            });

            ItemStack survivalhotmc = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5MTk2YjMzMGM2Yjg5NjJmMjNhZDU2MjdmYjZlY2NlNDcyZWFmNWM5ZDQ0Zjc5MWY2NzA5YzdkMGY0ZGVjZSJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#FF3131&lSURVIVAL + EKONOMIA V2 &8(&e%bungee_survivalv2% &7graczy online&8)")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &#39FF14Serwer &#FFF01F&nhotmc.pl",
                            "",
                            " &7Zalecana wersja &a1.19.2",
                            " &7Serwer wspiera wersje &a1.18.2 - 1.19.3",
                            " &7Data rozpoczęcia edycji &a16.09.2022",
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
                            " &7Zalecana wersja &f1.8.8",
                            " &7Serwer wspiera wersje &f1.8.8 - 1.19.3",
                            " &7Data rozpoczęcia edycji &f01.06.2022",
                            "",
                            " &f&nKliknij aby dołączyć do serwera!"
                    )));
                });
            });

            ItemStack boxpvp = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTUxY2YwNzVhMWY1MzMxNWQ3NjE4MjFjZWY3MmEyNWY2YzE1ZGQ5OGE3YTM4NzJmYzliZWM1OTdhYmMzYjEifX19");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#fb98b5&lB&#f8a0b1&lO&#f5a9ad&lX&#f2b1a8&lP&#efbaa4&lV&#ecc2a0&lP")));
                    itemMeta.setLore(Api.fixColor(PlaceholderAPI.setPlaceholders(player, Arrays.asList(
                            "",
                            " &#FBFFFFGraczy online: &#FFC42E%bungee_boxpvp%",
                            " &#FBFFFFWersja: &#4ec8fc1.18.2 - 1.19.4",
                            " &#FBFFFFData rozpoczęcia: &#f55dbb02.06.2023",
                            "",
                            " &#FBFD8C&nKliknij aby dołączyć do trybu!"
                    ))));
                });
            });

            ItemStack survivalwywrotkamc = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5MTk2YjMzMGM2Yjg5NjJmMjNhZDU2MjdmYjZlY2NlNDcyZWFmNWM5ZDQ0Zjc5MWY2NzA5YzdkMGY0ZGVjZSJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#39FF14&lSURVIVAL + EKONOMIA")));
                    itemMeta.setLore(Api.fixColor(PlaceholderAPI.setPlaceholders(player, Arrays.asList(
                            "",
                            " &#FBFFFFGraczy online: &#FFC42E%bungee_survival%",
                            " &#FBFFFFWersja: &#4ec8fc1.18.2 - 1.19.4",
                            " &#FBFFFFData rozpoczęcia: &#f55dbb18.05.2023",
                            "",
                            " &#FBFD8C&nKliknij aby dołączyć do trybu!"
                    ))));
                });
            });

            ItemStack practice = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2NTM0MTM1M2MwMjllOWI2NTVmNGY1NzkzMWFlNmFkYzJjN2E3M2M2NTc5NDVkOTQ1YTMwNzY0MWQzNzc4In19fQ==");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#fc53f7&lPRACTICE 1.8.8")));
                    itemMeta.setLore(Api.fixColor(PlaceholderAPI.setPlaceholders(player, Arrays.asList(
                            "",
                            " &#FBFFFFGraczy online: &#FFC42E%bungee_practice%",
                            " &#FBFFFFWersja: &#4ec8fc1.8.8 - 1.12.2",
                            " &#FBFFFFData rozpoczęcia: &#f55dbb01.11.2022",
                            "",
                            " &#FBFD8C&nKliknij aby dołączyć do trybu!"
                    ))));
                });
            });

            ItemStack practice2 = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2NTM0MTM1M2MwMjllOWI2NTVmNGY1NzkzMWFlNmFkYzJjN2E3M2M2NTc5NDVkOTQ1YTMwNzY0MWQzNzc4In19fQ==");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#21F8F6&lPRACTICE 1.16")));
                    itemMeta.setLore(Api.fixColor(PlaceholderAPI.setPlaceholders(player, Arrays.asList(
                            "",
                            " &#FBFFFFGraczy online: &#FFC42E%bungee_practice2%",
                            " &#FBFFFFWersja: &#4ec8fc1.16.5 - 1.19.4",
                            " &#FBFFFFData rozpoczęcia: &#f55dbb26.05.2023",
                            "",
                            " &#FBFD8C&nKliknij aby dołączyć do trybu!"
                    ))));
                });
            });

            ItemStack skyblock = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjAyYzk5Y2I5NjY3NDZiMGUzZDE2OTczZmMyY2RjZTRlNDBiODdhODZjMDVlMGE1MDIxZjM1YTAxOTJhNDBiMiJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor(PlaceholderAPI.setPlaceholders(player, "&#fc5375&lSKYBLOCK")));
                    itemMeta.setLore(Api.fixColor(Arrays.asList(
                            "",
                            " &#FBFFFFGraczy online: &#FFC42E%bungee_skyblock%",
                            " &#FBFFFFWersja: &#4ec8fc1.16.5 - 1.19.4",
                            " &#FBFFFFData rozpoczęcia: &#f55dbb17.06.2023",
                            "",
                            " &#FBFD8C&nKliknij aby dołączyć do trybu!"
                    )));
                });
            });

            inventoryHelper.click(e -> {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
                //hotmc
                //if (e.getSlot() == 11) {
                //    player.getOpenInventory().close();
                //    Api.sendPlayerToServer(player, "bedwars");
                //    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                //} else if (e.getSlot() == 12) {
                //    player.getOpenInventory().close();
                //    Api.sendPlayerToServer(player, "survival");
                //    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                //} else if (e.getSlot() == 14) {
                //    player.getOpenInventory().close();
                //    Api.sendPlayerToServer(player, "dropsmp");
                //    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                //} else if (e.getSlot() == 15) {
                //    player.getOpenInventory().close();
                //    Api.sendPlayerToServer(player, "lifesteal");
                //    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                //}
                //wywrotkamc
                //if (e.getSlot() == 10) {
                //    player.getOpenInventory().close();
                //    player.updateInventory();
                //    Api.sendPlayerToServer(player, "LIFESTEAL");
                //    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                if (e.getSlot() == 11) {
                    player.getOpenInventory().close();
                    player.updateInventory();
                    Api.sendPlayerToServer(player, "BOXPVP");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                } else if (e.getSlot() == 12) {
                    player.getOpenInventory().close();
                    player.updateInventory();
                    Api.sendPlayerToServer(player, "SURVIVAL");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                } else if (e.getSlot() == 14) {
                    player.getOpenInventory().close();
                    player.updateInventory();
                    Api.sendPlayerToServer(player, "PRACTICE");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                } else if (e.getSlot() == 15) {
                    player.getOpenInventory().close();
                    player.updateInventory();
                    Api.sendPlayerToServer(player, "PRACTICE2");
                    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                //} else if (e.getSlot() == 16) {
                //    player.getOpenInventory().close();
                //    player.updateInventory();
                //    Api.sendPlayerToServer(player, "BEDWARS");
                //    Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                }
            });

            //hotmc
            //inventoryHelper.setItemRange(0, 4 * 9, glass_black);
            //inventoryHelper.setItem(11, bedwars);
            //inventoryHelper.setItem(12, survivalhotmc);
            //inventoryHelper.setItem(14, dropsmphotmc);
            //inventoryHelper.setItem(15, lifestealhotmc);
//
            //inventoryHelper.setItem(20, glass_lime);
            //inventoryHelper.setItem(21, glass_lime);
            //inventoryHelper.setItem(23, glass_lime);
            //inventoryHelper.setItem(24, glass_red);
            //inventoryHelper.open(player);

            //wywrotkamc
            inventoryHelper.setItemRange(0, 4 * 9, glass_black);
            //inventoryHelper.setItem(10, lifestealwywrotkamc);
            inventoryHelper.setItem(11, boxpvp);
            inventoryHelper.setItem(12, survivalwywrotkamc);
            inventoryHelper.setItem(14, practice);
            inventoryHelper.setItem(15, practice2);
            //inventoryHelper.setItem(16, bedwars);
//
            //inventoryHelper.setItem(19, glass_lime);
            inventoryHelper.setItem(20, glass_red);
            inventoryHelper.setItem(21, glass_lime);
            inventoryHelper.setItem(23, glass_lime);
            inventoryHelper.setItem(24, glass_lime);
            //inventoryHelper.setItem(25, glass_lime);
            inventoryHelper.open(player);

        }

        if (guiID == 1) {
            InventoryHelper inventoryHelper = new InventoryHelper(player, "Wybierz hub", 4);

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

            ItemStack lobby1 = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjFkODU5ZThiMTRmNjI2NDY4NTljZjM4MDRhNjRmMTA2MGQ2ODc5MzQxYjRjMzM4NWI0NmEwZWM0MGZhZjczYyJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor("&#FBFFFF&lHUB &#FBFFFF#1"));
                    itemMeta.setLore(Api.fixColor(PlaceholderAPI.setPlaceholders(player, Arrays.asList(
                            "",
                            " &#FBFFFFGraczy online: &#FFC42E%bungee_lobby%",
                            "",
                            " &#FBFD8C&nKliknij aby dołączyć do hub!"
                    ))));
                });
            });

            ItemStack lobby2 = inventoryHelper.prepareItemStack(Material.LEGACY_SKULL_ITEM, itemStack -> {
                itemStack.setDurability((short) 3);
                inventoryHelper.editSkullMetaWithProperty(itemStack, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNkOTNlOGI1ZmIwYjVkNTBhYmQ0ZWY4ODUzMmY0Njg3NGI5OTI0ZjY2OGRkYjAxMDkxNDY4ZTRlNjFiOWM4MyJ9fX0=");
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor("&#FBFFFF&lHUB &#FBFFFF#2"));
                    itemMeta.setLore(Api.fixColor(PlaceholderAPI.setPlaceholders(player, Arrays.asList(
                            "",
                            " &#FBFFFFGraczy online: &#FFC42E%bungee_lobby2%",
                            "",
                            " &#FBFD8C&nKliknij aby dołączyć do hub!"
                    ))));
                });
            });

            ItemStack back = inventoryHelper.prepareItemStack(Material.BARRIER, itemStack -> {
                inventoryHelper.editMetaForItemStack(itemStack, itemMeta -> {
                    itemMeta.setDisplayName(Api.fixColor("&#FF3131Zamknij"));
                });
            });


            inventoryHelper.click(e -> {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
                if (e.getSlot() == 10) {
                    if (Main.pluginConfig.getMessages().getServer().equals("LOBBY")) {
                        player.getOpenInventory().close();
                        player.updateInventory();
                        Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&cNie możesz się z tym serwerem połączyć bo jesteś na nim!");
                    } else {
                        player.getOpenInventory().close();
                        player.updateInventory();
                        Api.sendPlayerToServer(player, "LOBBY");
                        Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                    }
                } else if (e.getSlot() == 11) {
                    if (Main.pluginConfig.getMessages().getServer().equals("LOBBY2")) {
                        player.getOpenInventory().close();
                        player.updateInventory();
                        Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&cNie możesz się z tym serwerem połączyć bo jesteś na nim!");
                    } else {
                        player.getOpenInventory().close();
                        player.updateInventory();
                        Api.sendPlayerToServer(player, "LOBBY2");
                        Api.sendMessage(player, Main.pluginConfig.getMessages().getPrefix() + "&aTrwa łączenie z serwerem..");
                    }
                } else if (e.getSlot() == 31) {
                    player.closeInventory();
                }
            });

            inventoryHelper.setItemRange(0, 11, glass_black);
            inventoryHelper.setItem(10, lobby1);
            inventoryHelper.setItem(11, lobby2);
            inventoryHelper.setItemRange(17, 36, glass_black);
            inventoryHelper.setItem(31, back);
            inventoryHelper.open(player);

        }
    }
}
