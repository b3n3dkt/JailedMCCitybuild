package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            float speed = Float.valueOf(args[0]);
            if(player.hasPermission("jailedmc.command.flyspeed")){
                if(args.length == 1){
                    if(player.getAllowFlight() == true){
                        try{
                            player.setFlySpeed(speed);
                            player.sendMessage(Citybuild.getPrefix() + "§7Deine Fluggeschwindigkeit beträgt nun §8'§3" + speed + "§8'§7!");
                        }catch (Exception e1){
                            player.sendMessage(Citybuild.getPrefix() + "§cDas ist kein gültiger Wert!");
                        }
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cDu musst im Flugmodus sein um schneller Fliegen zu können!");
                    }
                }else if(args.length == 2){ //flyspeed <value> <spieler>
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target != null){
                        try{
                            target.setFlySpeed(speed);
                            player.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8§3'" + target.getName() + "§8' §7hat nun eine Fluggeschwindigkeit von §8'§3" + speed + "§8'§7!");
                            target.sendMessage(Citybuild.getPrefix() + "§7Deine Fluggeschwindigkeit beträgt nun §8'§3" + speed + "§8'§7!");
                        }catch (Exception e1){
                            player.sendMessage(Citybuild.getPrefix() + "§cDas ist kein gültiger Wert!");
                        }
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cDer Spieler ist nicht online!");
                    }
                }

            }
        }
        return false;
    }
}
