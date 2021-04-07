package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.Warps;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 1){ //warp <list|Name>
                String name = args[0];
                if(args[0].equalsIgnoreCase("list")){
                    List<String> temp = Warps.getWarps();
                    if(temp.size() > 0){
                        player.sendMessage("§8§m          §r§8(§3Warps§8)§m          ");
                        for(int i = 0;i<temp.size();i++){
                            player.sendMessage("§7- §8'§3" + temp.get(i) + "§8'");
                        }
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§7Es wurden noch keine Warps gesetzt!");
                    }
                }else{
                    Warps warps = new Warps(name);
                    if(warps.warpExists() == true){
                        Location loc = warps.getWarp();
                        player.teleport(loc);
                        player.sendMessage(Citybuild.getPrefix() + "§aDu hast dich zu §8'§3" + name + "§8' §7teleportiert!");
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cDieser Warp existiert nicht!");
                    }
                }
            }else if(args.length == 2){//warp <set|remove> <name>
                if(player.hasPermission("jailedmc.command.warp")){
                    String name = args[1];
                    if(args[0].equalsIgnoreCase("set")){
                        Warps warps = new Warps(name);
                        if(warps.warpExists() != true){
                            warps.setWarp(player.getWorld().getName(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
                            player.sendMessage(Citybuild.getPrefix() + "§7Du hast den Warp §8'§3" + name + "§8' §7gesetzt!");
                        }else{
                            player.sendMessage(Citybuild.getPrefix() + "§cDer Warp existiert bereits!");
                        }
                    }else if(args[0].equalsIgnoreCase("remove")){
                        Warps warps = new Warps(name);
                        if(warps.warpExists() == true){
                            warps.removeWarp();
                           player.sendMessage(Citybuild.getPrefix() + "§7Du hast den Warp §8'§3" + name + "§8' §7gelöscht!");
                        }else{
                            player.sendMessage(Citybuild.getPrefix() + "§cDer Warp existiert nicht!");
                        }
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cNutze /warp <set|remove|list|name>!");
                    }
                }else{
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /warp <list|name>!");
                }
            }else{
                player.sendMessage(Citybuild.getPrefix() + "§cNutze /warp <list|name>!");
            }
        }
        return false;
    }
}
