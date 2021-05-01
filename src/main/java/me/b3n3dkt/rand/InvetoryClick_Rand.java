package me.b3n3dkt.rand;

import com.github.intellectualsites.plotsquared.api.PlotAPI;
import com.github.intellectualsites.plotsquared.plot.object.Location;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvetoryClick_Rand implements Listener {

    @EventHandler
    public void onHandle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        PlotAPI plotAPI = new PlotAPI();
        Plot plot = this.getPlot(player.getLocation());
        try{

        if(event.getInventory() == null){return;}
        if(event.getCurrentItem() == null){return;}

        if(event.getView().getTitle().equalsIgnoreCase("§6Randmenü")){
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
                    if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}
                    if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}
                    if(player.hasPermission("jailedmc.command.rand.air")){
                        setRandBlock(player, Material.AIR, plot, true, event);
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                    }
                }
        }else if(event.getView().getTitle().equalsIgnoreCase("§aStufen")){
            event.setCancelled(true);
            if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}
            event.setCancelled(true);                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")){
                    player.closeInventory();
                    Inventorys.openRandMenü(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBirkenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.birkenholzstufe")){
                    setRandBlock(player, Material.BIRCH_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAkazienholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.akazienholzstufe")){
                    setRandBlock(player, Material.ACACIA_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTropenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.tropenholzstufe")){
                    setRandBlock(player, Material.JUNGLE_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchwarzeichenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.schwarzeichenholzstufe")){
                    setRandBlock(player, Material.DARK_OAK_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEichenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.eichenholzstufe")){
                    setRandBlock(player, Material.OAK_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aFichtenholzstufe")){
                if(player.hasPermission("jailedmc.command.rand.fichtenholzstufe")){
                    setRandBlock(player, Material.SPRUCE_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSteinstufe")){
                if(player.hasPermission("jailedmc.command.rand.steinstufe")){
                    setRandBlock(player, Material.STONE_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aZiegelsteinstufe")){
                if(player.hasPermission("jailedmc.command.rand.ziegelsteinstufe")){
                    setRandBlock(player, Material.BRICK_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSandsteinstufe")){
                if(player.hasPermission("jailedmc.command.rand.sandsteinstufe")){
                    setRandBlock(player, Material.SANDSTONE_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aQuartzstufe")){
                if(player.hasPermission("jailedmc.command.rand.quartzstufe")){
                    setRandBlock(player, Material.QUARTZ_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aZiegelstufe")){
                if(player.hasPermission("jailedmc.command.rand.zieglstufe")){
                    setRandBlock(player, Material.STONE_BRICK_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aNetherziegelstufe")){
                if(player.hasPermission("jailedmc.command.rand.netherziegelstufe")){
                    setRandBlock(player, Material.NETHER_BRICK_SLAB, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }
        }else if(event.getView().getTitle().equalsIgnoreCase("§6Besondere")){
            event.setCancelled(true);
            if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")){
                player.closeInventory();
                Inventorys.openRandMenü(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEnderportal-Rahmen")){
                if(player.hasPermission("jailedmc.command.rand.spawner")){
                    setRandBlock(player, Material.END_PORTAL_FRAME, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEisengitter")){
                if(player.hasPermission("jailedmc.command.rand.eisengitter")){
                    setRandBlock(player, Material.IRON_BARS, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchnee")){
                if(player.hasPermission("jailedmc.command.rand.schnee")){
                    setRandBlock(player, Material.SNOW, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAmboss")){
                if(player.hasPermission("jailedmc.command.rand.amboss")){
                    setRandBlock(player, Material.ANVIL, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchienen")){
                if(player.hasPermission("jailedmc.command.rand.schienen")){
                    setRandBlock(player, Material.RAIL, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBeacon")){
                if(player.hasPermission("jailedmc.command.rand.beacon")){
                    setRandBlock(player, Material.BEACON, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }
        }else if(event.getView().getTitle().equalsIgnoreCase("§aTeppiche")){
            event.setCancelled(true);
            if(plot == null){player.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(!isOwner(player, plot)){player.sendMessage(Citybuild.getPrefix() + "§cDies ist nicht dein Plot!");player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F); return;}
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")){
                player.closeInventory();
                Inventorys.openRandMenü(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aWeißer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.weißerteppich")){
                    setRandBlock(player, Material.WHITE_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aOranger Teppich")){
                if(player.hasPermission("jailedmc.command.rand.orangerteppich")){
                    setRandBlock(player, Material.ORANGE_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aMagenta Teppich")){
                if(player.hasPermission("jailedmc.command.rand.magentateppich")){
                    setRandBlock(player, Material.MAGENTA_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHellblauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.hellblauerteppich")){
                    setRandBlock(player, Material.LIGHT_BLUE_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGelber Teppich")){
                if(player.hasPermission("jailedmc.command.rand.gelberteppich")){
                    setRandBlock(player, Material.YELLOW_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHellgrüner Teppich")){
                if(player.hasPermission("jailedmc.command.rand.hellgrünerteppich")){
                    setRandBlock(player, Material.LIME_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPinker Teppich")){
                if(player.hasPermission("jailedmc.command.rand.pinkerteppich")){
                    setRandBlock(player, Material.PINK_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGrauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.grauerteppich")){
                    setRandBlock(player, Material.GRAY_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHellgrauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.hellgrauerteppich")){
                    setRandBlock(player, Material.LIGHT_GRAY_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aCyan Teppich")){
                if(player.hasPermission("jailedmc.command.rand.cyanteppich")){
                    setRandBlock(player, Material.CYAN_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLila Teppich")){
                if(player.hasPermission("jailedmc.command.rand.lilateppich")){
                    setRandBlock(player, Material.PURPLE_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBlauer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.blauerteppich")){
                    setRandBlock(player, Material.BLUE_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBrauner Teppich")){
                if(player.hasPermission("jailedmc.command.rand.braunerteppich")){
                    setRandBlock(player, Material.BROWN_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGrüner Teppich")){
                if(player.hasPermission("jailedmc.command.rand.grünerteppich")){
                    setRandBlock(player, Material.GREEN_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aRoter Teppich")){
                if(player.hasPermission("jailedmc.command.rand.roterteppich")){
                    setRandBlock(player, Material.RED_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSchwarzer Teppich")){
                if(player.hasPermission("jailedmc.command.rand.schwarzerteppich")){
                    setRandBlock(player, Material.BLACK_CARPET, plot, true, event);
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cFür den Rand hast du keine Rechte!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1.0F, 2.0F);
                }
            }
        }
        }catch (Exception e1){
            System.out.println("[ERROR] InventoryCLick_Rand hat einen Fehler!");
        }
    }

    public static void setRandBlock(Player p, Material mat, final Plot plot, boolean msg, InventoryClickEvent event) {
        PlotAPI plotAPI = new PlotAPI();
            for (Plot plots : plot.getConnectedPlots()) {
                plots.setComponent("border", String.valueOf(mat));
            }
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 0.0F);
            if (msg) {
                p.sendMessage(Citybuild.getPrefix() + "§aDein Rand wurde angepasst!");
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

    public static Plot getPlot(org.bukkit.Location loc) {
        Location loc2 = new Location(loc.getWorld().getName(),
                (int)loc.getX(),
                (int)loc.getY(),
                (int)loc.getZ(),
                loc.getYaw(),
                loc.getPitch());

        return loc2.getPlot();
    }
}
