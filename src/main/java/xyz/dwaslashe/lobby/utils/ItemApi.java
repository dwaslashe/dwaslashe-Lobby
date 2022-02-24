package xyz.dwaslashe.lobby.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemApi {
    private ItemStack itemStack;

    public ItemApi(Material material, short data) {
        this.itemStack = new ItemStack(material, 1, data);
    }
    public ItemApi(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }
    public ItemApi addEnchant(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemApi setAmount(int amount){
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemApi setUnbreakable(boolean enable){
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setUnbreakable(enable);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemApi setName(String name) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(Api.fixColor(name));
        this.itemStack.setItemMeta(meta);
        return this;
    }
    public ItemApi setLore(String name) {
        ItemStack stack = this.itemStack;
        ItemMeta meta = this.itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList();
        lore.add(Api.fixColor(name));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return this;
    }

    public ItemApi setLore(List<String> lore){
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setLore(Api.fixColor(lore));
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack toIS() {
        return this.itemStack;
    }
}
