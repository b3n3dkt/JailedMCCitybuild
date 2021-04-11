package me.b3n3dkt.job;

import com.sun.corba.se.spi.ior.IORTemplate;
import me.b3n3dkt.utils.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class FishingEvent implements Listener {

    @EventHandler
    public void onHandle(PlayerFishEvent event){
        Player player = event.getPlayer();
        PlayerData data = new PlayerData(player);
        if(event.getCaught() != null){
            Item d =(Item) event.getCaught();
            String dn = data.getQuestDN(data.getJob());
            if(data.getJob().equalsIgnoreCase("fischer")){
                if(d.getItemStack().equals(new ItemStack(Material.RAW_FISH, 1))){ //FISH
                    if(dn.equalsIgnoreCase("Kabeljau") || dn.equalsIgnoreCase("Fische")){

                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("fischer", 25);
                    data.setFischerItem("fish", data.getFischerItems("fish") + 1);
                }else if(d.getItemStack().equals(new ItemStack(Material.RAW_FISH, 1, (short) 1))){ //SALMON
                    if(dn.equalsIgnoreCase("Lachs") || dn.equalsIgnoreCase("Fische")){
    
                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("fischer", 60);
                    data.setFischerItem("salmon", data.getFischerItems("salmon") + 1);
                }else if(d.getItemStack().equals(new ItemStack(Material.RAW_FISH, 1, (short) 3))){ //CLOWNFISH
                    if(dn.equalsIgnoreCase("Clownfische") || dn.equalsIgnoreCase("Fische")){
    
                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("fischer", 150);
                    data.setFischerItem("clownfish", data.getFischerItems("clownfish") + 1);
                }else if(d.getItemStack().equals(new ItemStack(Material.RAW_FISH, 1, (short) 2))){ //PUFFERFISH
                    if(dn.equalsIgnoreCase("Kugelfische") || dn.equalsIgnoreCase("Fische")){
    
                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("fischer", 75);
                    data.setFischerItem("pufferfish", data.getFischerItems("pufferfish") + 1);
                }
            }
        }
    }

}
