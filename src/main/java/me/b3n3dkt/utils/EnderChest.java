package me.b3n3dkt.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class EnderChest {
   private static FileBuilder fb;
   private UUID uuid;

   public EnderChest(Player player) {
      this.uuid = player.getUniqueId();
      fb = new FileBuilder("plugins//SkyPvP//EnderChests", player.getUniqueId().toString() + ".yml");
   }

   public boolean exist() {
      return fb.exist();
   }

   public void saveEnderChest(Player player) {
      for(int i = 0; i < 54; ++i) {
         fb.setValue("Item." + i, player.getOpenInventory().getItem(i));
         fb.save();
      }

   }

   public HashMap<Integer, ItemStack> getEnderChest(Player player) {
      HashMap<Integer, ItemStack> temp = new HashMap();

      for(int i = 0; i < 54; ++i) {
         ItemStack stack = fb.getItemStack("Item." + i);
         temp.put(i, stack);
      }

      return temp;
   }
}
