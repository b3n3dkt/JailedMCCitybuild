package me.b3n3dkt.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
   private ItemStack item;
   private ItemMeta meta;

   public ItemBuilder(Material material) {
      this.item = new ItemStack(material);
      this.meta = this.item.getItemMeta();
   }

   public ItemBuilder(Material material, short subID) {
      this.item = new ItemStack(material, 1, subID);
      this.meta = this.item.getItemMeta();
   }

   public ItemBuilder setDisplayName(String name) {
      this.meta.setDisplayName(name);
      return this;
   }

   public ItemBuilder setLore(List lore) {
      this.meta.setLore(lore);
      return this;
   }

   public ItemBuilder setEnchantment(Enchantment enchantment, int level) {
      this.meta.addEnchant(enchantment, level, true);
      return this;
   }

   public ItemBuilder setAmount(int amount) {
      this.item.setAmount(amount);
      return this;
   }

   public ItemBuilder setItemFlag(ItemFlag flag) {
      this.meta.addItemFlags(new ItemFlag[]{flag});
      return this;
   }

   public ItemStack build() {
      this.item.setItemMeta(this.meta);
      return this.item;
   }
}
