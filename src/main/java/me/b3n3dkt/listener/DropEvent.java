package me.b3n3dkt.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropEvent implements Listener{

     @EventHandler
    public void onHandle(PlayerDropItemEvent event){
         if(event.getItemDrop().getItemStack().getAmount() < 1){
             event.getItemDrop().remove();
         }
     }

}
