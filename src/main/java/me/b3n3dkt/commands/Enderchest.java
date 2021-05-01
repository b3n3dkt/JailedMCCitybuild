package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.rand.Inventorys;
import me.b3n3dkt.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Enderchest implements CommandExecutor {

    public static ArrayList<Player> inEc = new ArrayList<>();
    public static HashMap<Player, Player> inOtherEC = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length == 0) {
                Inventorys.openEnderChest(p);
                inEc.add(p);
            } else if(args.length == 1){
               Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    if(target.hasPermission("jailedmc.command.ec.other.bypass")){p.sendMessage(Citybuild.getPrefix() + "§cDu kannst nicht in die Enderchest von dem Spieler gucken!");return true;}
                    if(target.getName().equalsIgnoreCase(p.getName())){
                        Inventorys.openEnderChest(p);
                        return true;
                    }
                    Inventorys.openOtherEnderChest(p, target);
                    inOtherEC.put(p, target);
                    p.sendMessage(Citybuild.getPrefix() + "§7Du siehst nun die Enderchest von §8'§e" + target.getName() +"§8'§7.");
                }else{
                    OfflinePlayer ot = Bukkit.getOfflinePlayer(Utility.getUUIDFromName(args[0]));
                    if(MySQL.isRegistered(ot.getUniqueId().toString()) == true){
                        try {
                            Inventorys.openOtherEnderChest(p, (Player) ot);
                            inOtherEC.put(p, (Player)ot);
                        }catch (Exception e1)   {
                            p.sendMessage(Citybuild.getPrefix() + "§cDer Spieler hat noch nichts in seiner Enderchest");
                        }
                        p.sendMessage(Citybuild.getPrefix() + "§7Du siehst nun die Enderchest von §8'§e" + ot.getName() +"§8'§7.");
                    }else{
                        p.sendMessage(Citybuild.getPrefix() + "§cDer Spieler war noch nie auf diesem Server!");
                    }
                }
            }else{
                p.sendMessage(Citybuild.getPrefix() + "§cNutze /enderchest <Spieler>");
            }
        }

        return false;
    }
}
