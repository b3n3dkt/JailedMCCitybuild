package me.b3n3dkt.job;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.PlayerData;
import me.b3n3dkt.utils.Score;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.Scoreboard;

public class InventoryClick_Job implements Listener {

    @EventHandler
    public void onHandle(InventoryClickEvent event) {
        if (event.getInventory() == null) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        PlayerData data = new PlayerData(player);
        Score sb = new Score(player);
        if (event.getInventory().getTitle().equalsIgnoreCase("§3Job-Auswählen")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Holzfäller")) {
                Quest quest = new Quest("holzfäller", player);
                data.setJob("holzfäller");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3" + event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", "") + "§8' §7!");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Fischer")) {
                data.setJob("fischer");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3" + event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", "") + "§8' §7!");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Miner")) {
                data.setJob("miner");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3" + event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", "") + "§8' §7!");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Farmer")) {
                data.setJob("farmer");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3" + event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", "") + "§8' §7!");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Forscher")) {
                //data.setJob("forscher");
                //player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§cDer Job ist noch nicht verfügbar!");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Techniker")) {
                //data.setJob("techniker");
                //player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§cDer Job ist noch nicht verfügbar!");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Schmied")) {
                //data.setJob("schmied");
                //player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§cDer Job ist noch nicht verfügbar!");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Arbeitslos")) {
                data.setJob("arbeitslos");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3" + event.getCurrentItem().getItemMeta().getDisplayName().replace("§7", "") + "§8' §7!");
            }
            sb.update();
            /*
            JOB INFO GUI
             */
        } else if (event.getInventory().getTitle().equalsIgnoreCase("§3Holzfäller")) { //HOLZFÄLLER
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Kündige deinen Job")) {
                data.setJob("arbeitslos");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3Arbeitslos§8' §7!");
            }
            sb.update();
        } else if (event.getInventory().getTitle().equalsIgnoreCase("§3Fischer")) { //FISCHER
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Kündige deinen Job")) {
                data.setJob("arbeitslos");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3Arbeitslos§8' §7!");
            }
            sb.update();
        } else if (event.getInventory().getTitle().equalsIgnoreCase("§3Miner")) { //MINER
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Kündige deinen Job")) {
                data.setJob("arbeitslos");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3Arbeitslos§8' §7!");
            }
            sb.update();
        } else if (event.getInventory().getTitle().equalsIgnoreCase("§3Farmer")) { //FARMER
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Kündige deinen Job")) {
                data.setJob("arbeitslos");
                player.closeInventory();
                player.sendMessage(Citybuild.getPrefix() + "§7Du bist nun §8'§3Arbeitslos§8' §7!");
            }
            sb.update();
        }
    }

}
