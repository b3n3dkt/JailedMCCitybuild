package me.b3n3dkt.listener;

import me.b3n3dkt.Citybuild;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onHandle(BlockPlaceEvent event){
            if(event.getItemInHand().getAmount() < 1){
                event.setCancelled(true);
            }
            if(event.getItemInHand().getData().getItemType().equals(Material.HOPPER_MINECART)) {
                if(!event.getPlayer().hasPermission("jailedmc.blockplace.bypass")) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(Citybuild.getPrefix() + "§cMan kann dieses Item nicht plazieren, da man dadurch Items von anderen Spielern klauen kann!");
                }
            }
    }

}
