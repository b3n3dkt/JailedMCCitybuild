package me.b3n3dkt.listener;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.commands.Enderchest;
import me.b3n3dkt.commands.Invsee;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.EnderChest;
import me.b3n3dkt.utils.PlayerData;
import me.b3n3dkt.utils.Score;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClick implements Listener{

    @EventHandler
    public void onHandle(InventoryClickEvent event){
        try{
            if(event.getInventory() == null){ return; }
            if(event.getCurrentItem() == null){ return;}
            if(event.getCurrentItem().getAmount() < 1){
                event.getCurrentItem().setAmount(1);
            }
            if (Invsee.getuser.contains(event.getWhoClicked())) {
                if (!event.getWhoClicked().hasPermission("jailedmc.command.invsee.click")) {
                    event.setCancelled(true);
                }

            }
            if(Enderchest.inEc.contains(event.getWhoClicked())){
                if(!event.getWhoClicked().hasPermission("jailedmc.command.enderchest.click")) {
                    event.setCancelled(true);
                }
            }
        }catch (Exception e1){
            System.out.println("[ERROR] InventoryClick hat einen Fehler");
        }

    }
    @EventHandler
    public void onLeave(InventoryCloseEvent e) {
        if(e.getInventory().getTitle().equalsIgnoreCase("§5EnderChest")){
            EnderChest ec = new EnderChest((Player) e.getPlayer());
            ec.saveEnderChest((Player) e.getPlayer());
        }
        if (Invsee.getuser.contains(e.getPlayer())) {
            Invsee.getuser.remove(e.getPlayer());
        }
        if(Enderchest.inEc.contains(e.getPlayer())){
            Enderchest.inEc.remove(e.getPlayer());
        }

    }

}
