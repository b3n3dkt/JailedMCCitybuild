package me.b3n3dkt.shop;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.npc.Animation;
import me.b3n3dkt.npc.PlayerInteractNPCEvent;
import me.b3n3dkt.utils.Belohnungen;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;

public class NPCInteract implements Listener {
    
    @EventHandler
    public void onHandle(PlayerInteractNPCEvent event){
        Player player = event.getPlayer();
        Citybuild.getExecutorService().execute(() -> {
            if (event.getClickType() == ClickType.RIGHT) {
                if (event.getNpcEntity().getEntityPlayer().getName().equalsIgnoreCase("§6§lShop")) {
                    Shop_CMD.openShopInventory(player);
                }else if (event.getNpcEntity().getEntityPlayer().getName().equalsIgnoreCase("§6§lBelohnungen")) {
                    Belohnungen.openBelohnungen(player);
                }else if (event.getNpcEntity().getEntityPlayer().getName().equalsIgnoreCase("§6§lJobs")) {
                    player.chat("/job");
                }
            } else if (event.getClickType() == ClickType.LEFT) {
                event.getNpcEntity().setAnimation(player, Animation.HIT);
            }

        });
    }
    
}
