package me.b3n3dkt.rand;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotBlock;
import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvetoryClick_Rand implements Listener {

    @EventHandler
    public void onHandle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        PlotBlock[] plotBlock = Configuration.BLOCKLIST.parseString("STEP");
        PlotAPI plotAPI = new PlotAPI();
        Plot plot = plotAPI.getPlot(player.getLocation());

        if(event.getInventory() == null){return;}
        if(event.getCurrentItem() == null){return;}

        if(event.getInventory().getTitle().equalsIgnoreCase("§6Randmenü")){
            event.setCancelled(true);
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aStufen")){
                    player.closeInventory();
                    Inventorys.openStufenMenü(player);
                }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBesondere")){
                    player.closeInventory();
                    Inventorys.openBesondere(player);
                }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTeppiche")){
                    player.closeInventory();
                    Inventorys.openTeppiche(player);
                }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cKeinen Rand")){
                    if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}
                    if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}
                    if(player.hasPermission("jailedmc.command.rand.air")){
                        setRandBlock(player, "0", plot, true, event);
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                        player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                    }
                }
        }else if(event.getInventory().getTitle().equalsIgnoreCase("§aStufen")){
            event.setCancelled(true);
            if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}
            event.setCancelled(true);                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")){
                    player.closeInventory();
                    Inventorys.openRandMenü(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBirkenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.birkenholzstufe")){
                    setRandBlock(player, "126:2", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAkazienholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.akazienholzstufe")){
                    setRandBlock(player, "126:4", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTropenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.tropenholzstufe")){
                    setRandBlock(player, "126:3", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchwarzeichenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.schwarzeichenholzstufe")){
                    setRandBlock(player, "126:5", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEichenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.eichenholzstufe")){
                    setRandBlock(player, "126", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aFichtenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.fichtenholzstufe")){
                    setRandBlock(player, "126:1", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSteinstufe")){
                if(player.hasPermission("jailedmc.command.rand.steinstufe")){
                    setRandBlock(player, "44", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aZiegelsteinstufe")){
                if(player.hasPermission("jailedmc.command.rand.ziegelsteinstufe")){
                    setRandBlock(player, "44:5", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSandsteinstufe")){
                if(player.hasPermission("jailedmc.command.rand.sandsteinstufe")){
                    setRandBlock(player, "44:1", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aQuartzstufe")){
                if(player.hasPermission("jailedmc.command.rand.quartzstufe")){
                    setRandBlock(player, "44:7", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aZiegelstufe")){
                if(player.hasPermission("jailedmc.command.rand.zieglstufe")){
                    setRandBlock(player, "44:4", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aNetherziegelstufe")){
                if(player.hasPermission("jailedmc.command.rand.netherziegelstufe")){
                    setRandBlock(player, "44:6", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }
        }else if(event.getInventory().getTitle().equalsIgnoreCase("§6Besondere")){
            event.setCancelled(true);
            if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")){
                player.closeInventory();
                Inventorys.openRandMenü(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSpawner")){
                if(player.hasPermission("jailedmc.command.rand.spawner")){
                    setRandBlock(player, "52", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEisengitter")){
                if(player.hasPermission("jailedmc.command.rand.eisengitter")){
                    setRandBlock(player, "101", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchnee")){
                if(player.hasPermission("jailedmc.command.rand.schnee")){
                    setRandBlock(player, "78", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAmboss")){
                if(player.hasPermission("jailedmc.command.rand.amboss")){
                    setRandBlock(player, "145", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchienen")){
                if(player.hasPermission("jailedmc.command.rand.schienen")){
                    setRandBlock(player, "66", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBeacon")){
                if(player.hasPermission("jailedmc.command.rand.beacon")){
                    setRandBlock(player, "138", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }
        }else if(event.getInventory().getTitle().equalsIgnoreCase("§aTeppiche")){
            event.setCancelled(true);
            if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")){
                player.closeInventory();
                Inventorys.openRandMenü(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aWeißer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.weißerteppich")){
                    setRandBlock(player, "171", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aOranger Teppich")){
                if(player.hasPermission("jailedmc.command.rand.orangerteppich")){
                    setRandBlock(player, "171:1", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aMagenta Teppich")){
                if(player.hasPermission("jailedmc.command.rand.magentateppich")){
                    setRandBlock(player, "171:2", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHellblauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.hellblauerteppich")){
                    setRandBlock(player, "171:3", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGelber Teppich")){
                if(player.hasPermission("jailedmc.command.rand.gelberteppich")){
                    setRandBlock(player, "171:4", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHellgrüner Teppich")){
                if(player.hasPermission("jailedmc.command.rand.hellgrünerteppich")){
                    setRandBlock(player, "171:5", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPinker Teppich")){
                if(player.hasPermission("jailedmc.command.rand.pinkerteppich")){
                    setRandBlock(player, "171:6", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGrauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.grauerteppich")){
                    setRandBlock(player, "171:7", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHellgrauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.hellgrauerteppich")){
                    setRandBlock(player, "171:8", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aCyan Teppich")){
                if(player.hasPermission("jailedmc.command.rand.cyanteppich")){
                    setRandBlock(player, "171:9", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLila Teppich")){
                if(player.hasPermission("jailedmc.command.rand.lilateppich")){
                    setRandBlock(player, "171:10", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBlauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.blauerteppich")){
                    setRandBlock(player, "171:11", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBrauner Teppich")){
                if(player.hasPermission("jailedmc.command.rand.braunerteppich")){
                    setRandBlock(player, "171:12", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGrüner Teppich")){
                if(player.hasPermission("jailedmc.command.rand.grünerteppich")){
                    setRandBlock(player, "171:13", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aRoter Teppich")){
                if(player.hasPermission("jailedmc.command.rand.roterteppich")){
                    setRandBlock(player, "171:14", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchwarzer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.schwarzerteppich")){
                    setRandBlock(player, "171:15", plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                }
            }
        }
    }

    public static void setRandBlock(Player p, String id, final Plot plot, boolean msg, InventoryClickEvent event) {
        PlotBlock[] pb = (PlotBlock[])Configuration.BLOCKLIST.parseString(id);
        PlotAPI plotAPI = new PlotAPI();
        if (plot.getConnectedPlots().size() > 1) {
            for (Plot plots : plot.getConnectedPlots()) {
                plotAPI.getPlotManager(p.getWorld()).setComponent(plots.getArea(), plots.getId(), "border", pb);
            }
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 0.0F);
            if (msg) {
                p.sendMessage(Citybuild.getPrefix() + "§aDein Rand wurde angepasst!");
            }
        } else {
            plotAPI.getPlotManager(p.getWorld()).setComponent(plot.getArea(), plot.getId(), "border", pb);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 0.0F);
            if (msg) {
                p.sendMessage(Citybuild.getPrefix() + "§aDein Rand wurde angepasst!");
            }
        }
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Citybuild.getMain(), new Runnable()
        {
            public void run()
            {
                plot.setSign();
            }
        },  10L);
    }

    public static boolean isOwner(Player p, Plot plot) {
        if (!plot.hasOwner())
            return false;
        if (p.hasPermission("jailedmc.rand.admin"))
            return true;
        if (plot.isOwner(p.getUniqueId())) {
            return true;
        }
        return false;
    }


}
