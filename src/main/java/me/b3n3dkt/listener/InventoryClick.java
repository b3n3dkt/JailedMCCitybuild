package me.b3n3dkt.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener{

    @EventHandler
    public void onHandle(InventoryClickEvent event){
        if(event.getInventory() == null){ return; }
        if(event.getCurrentItem() == null){ return;}
        if(event.getCurrentItem().getAmount() < 1){
            event.getCurrentItem().setAmount(1);
        }
    }

}
