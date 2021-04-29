package me.b3n3dkt.listener;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.Belohnungen;
import me.b3n3dkt.utils.Score;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class InventoryClick_Belohnungen implements Listener {

   @EventHandler
   public void onHandle(InventoryClickEvent e){
      try{

      ItemStack tskull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
      SkullMeta tmeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.LEGACY_SKULL_ITEM);
      tmeta.setDisplayName("§6§lTägliche Belohnungen");
      tmeta.setOwner("MHF_Chest");
      tskull.setItemMeta(tmeta);
      ItemStack wskull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
      SkullMeta wmeta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.LEGACY_SKULL_ITEM);
      wmeta.setDisplayName("§6§lWöchentliche Belohnungen");
      wmeta.setOwner("MHF_Chest");
      wskull.setItemMeta(wmeta);
      ItemStack mskull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
      SkullMeta mmeta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.LEGACY_SKULL_ITEM);
      mmeta.setDisplayName("§6§lMonatliche Belohnungen");
      mmeta.setOwner("MHF_Chest");
      mskull.setItemMeta(mmeta);
      Player p = (Player)e.getWhoClicked();
      String uuid = p.getUniqueId().toString();
      Score scoreBoard = new Score(p);
      if(e.getInventory() == null){
         return;
      }
      if(e.getView().getTitle() == null){
         return;
      }
      if(e.getCurrentItem() == null){
         return;
      }
      if(e.getCurrentItem().getType() == Material.AIR){
         return;
      }
      if (e.getView().getTitle().equalsIgnoreCase("§6§lBelohnungen")) {
         e.setCancelled(true);
         if (e.getCurrentItem() != null) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lTägliche Belohnungen")) {
               if (Belohnungen.usedTägliche(uuid)) {
                  if (Belohnungen.getEndTägliche(uuid) <= System.currentTimeMillis()) {
                     Belohnungen.unsetUsedTäglich(uuid);
                     Belohnungen.setUsedTäglich(uuid, p.getName(), 86400L);
                     p.closeInventory();
                     MySQL.setcoins(uuid, MySQL.getcoins(uuid) + 250.0D);
                     scoreBoard.update();
                  } else {
                     p.closeInventory();
                     p.sendMessage(Citybuild.getPrefix() + "§7Du kannst die Täglichen Belohnungen erst wieder in §r" + Belohnungen.getRemainingTimeTägliche(uuid) + " §7abholen!");
                  }
               } else {
                  Belohnungen.setUsedTäglich(uuid, p.getName(), 86400L);
                  p.closeInventory();
                  MySQL.setcoins(uuid, MySQL.getcoins(uuid) + 250.0D);
                  scoreBoard.update();
               }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lWöchentliche Belohnungen")) {
               if (Belohnungen.usedWöchentliche(uuid)) {
                  if (Belohnungen.getEndWöchentliche(uuid) <= System.currentTimeMillis()) {
                     Belohnungen.unsetUsedWöchentlich(uuid);
                     Belohnungen.setUsedWöchentlich(uuid, p.getName(), 604800L);
                     p.closeInventory();
                     MySQL.setcoins(uuid, MySQL.getcoins(uuid) + 800.0D);
                     scoreBoard.update();
                  } else {
                     p.closeInventory();
                     p.sendMessage(Citybuild.getPrefix() + "§7Du kannst die Wöchentlichen Belohnungen erst wieder in §r" + Belohnungen.getRemainingTimeWöchentliche(uuid) + " §7abholen!");
                  }
               } else {
                  Belohnungen.setUsedWöchentlich(uuid, p.getName(), 604800L);
                  p.closeInventory();
                  MySQL.setcoins(uuid, MySQL.getcoins(uuid) + 800.0D);
                  scoreBoard.update();
               }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lMonatliche Belohnungen")) {
               if (Belohnungen.usedMonatliche(uuid)) {
                  if (Belohnungen.getEndMonatliche(uuid) <= System.currentTimeMillis()) {
                     Belohnungen.unsetUsedMonatlich(uuid);
                     Belohnungen.setUsedMontalich(uuid, p.getName(), 2419200L);
                     p.closeInventory();
                     MySQL.setcoins(uuid, MySQL.getcoins(uuid) + 2000.0D);
                     scoreBoard.update();
                  } else {
                     p.closeInventory();
                     p.sendMessage(Citybuild.getPrefix() + "§7Du kannst die Monatlichen Belohnungen erst wieder in §r" + Belohnungen.getRemainingTimeMonatliche(uuid) + " §7abholen!");
                  }
               } else {
                  Belohnungen.setUsedMontalich(uuid, p.getName(), 2419200L);
                  p.closeInventory();
                  MySQL.setcoins(uuid, MySQL.getcoins(uuid) + 2000.0D);
                  scoreBoard.update();
               }
            }
         }
      }
      }catch (Exception e1){
         System.out.println("[ERROR] InventoryClick_Belohnungen hat einen fehler!");
      }
   }
}
