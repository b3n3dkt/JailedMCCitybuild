package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.Homes;
import me.b3n3dkt.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Home implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("list")) {
                        List<String> temp = Homes.getHomes(player.getUniqueId().toString());
                        if(temp.size() > 0){
                            player.sendMessage("§8§m          §r§8(§3Deine Homes§8)§m          ");
                            for(int i = 0;i<temp.size();i++){
                                player.sendMessage("§7- §8'§3" + temp.get(i) + "§8'");
                            }
                        }else{
                            player.sendMessage(Citybuild.getPrefix() + "§7Du besitzt noch keine Homes!");
                        }
                }else if(!args[0].equalsIgnoreCase("set") || !args[0].equalsIgnoreCase("remove")) {
                    String name = args[0];
                    Homes homes = new Homes(player.getUniqueId().toString(), name);
                    if(homes.homeExists() == true){
                        Location loc = homes.getHome();
                        player.teleport(loc);
                        player.sendMessage(Citybuild.getPrefix() + "§aDu hast dich zu deinem Home teleportiert!");
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cDieses Home existiert nicht!");
                        return true;
                    }
                }else {
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /home <set|remove|name|list> <name>!");
                }
            }else if(args.length == 2){
                String name = args[1];
                Homes homes = new Homes(player.getUniqueId().toString(), name);
                if (args[0].equalsIgnoreCase("set")) {
                    if (homes.homeExists() != true) {
                        homes.setHome(player.getWorld().getName(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                        player.sendMessage(Citybuild.getPrefix() + "§7Du hast erfolgreich das Home §8'§3" + name + "§8'§7 gesetzt!");
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cDas Home existiert bereits!");
                    }
                }else if (args[0].equalsIgnoreCase("remove")) {
                    homes.removeHome();
                    player.sendMessage(Citybuild.getPrefix() + "§7Du hast das Home§8'§3" + name + "§8'§7 gelöscht!");
                }else if (args[0].equalsIgnoreCase("list")) { //home list <Spieler>
                    if(!player.hasPermission("jailedmc.command.homeother")){
                        player.sendMessage(Citybuild.getPrefix() + "§cNutze /home <set|remove|name|list> <name>!");
                        return true;
                    }
                    OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
                    List<String> temp = Homes.getHomes(op.getUniqueId().toString());
                    if(temp.size() > 0){
                        player.sendMessage("§8§m          §r§8('§3" + op.getName() + "§8' §7Homes§8)§m          ");
                        for(int i = 0;i<temp.size();i++){
                            player.sendMessage("§7- §8'§3" + temp.get(i) + "§8'");
                        }
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§7Der Spieler besitzt keine Homes!");
                    }
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /home <set|remove|name|list> <name>!");
                }
            }else if(args.length == 3){ //home teleport <Spieler> <Home>
                if(!player.hasPermission("jailedmc.command.homeother")){
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /home <set|remove|name|list> <name>!");
                    return true;
                }
                if(args[0].equalsIgnoreCase("teleport")){
                    OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
                    String name = args[2];
                    Homes  homes = new Homes(op.getUniqueId().toString(), name);
                    if(homes.homeExists() != true){
                        player.sendMessage(Citybuild.getPrefix() + "§cDieses Home existiert nicht!");
                        return true;
                    }
                    Location loc = homes.getHome();
                    player.teleport(loc);
                    player.sendMessage(Citybuild.getPrefix() + "§7Du hast dich zu dem Home §8'§3" + name + "§8' §7von dem Spieler §8'§3" + op.getName() + "§8' §7teleportiert!");
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /home <set|remove|name|list> <name>!");
                }
            }else{
                player.sendMessage(Citybuild.getPrefix() + "§cNutze /home <set|remove|name|list> <name>!");
            }
        }
        return false;
    }
}
