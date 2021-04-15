package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.TPS;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;

public class Lag implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("jailedmc.command.lag")){
            if(args.length == 0){
                double tps = me.b3n3dkt.utils.TPS.getTPS();
                DecimalFormat TPSFormat = new DecimalFormat("#.##");

                if(tps > 20){
                    sender.sendMessage(Citybuild.getPrefix() + "§2TPS: " + TPSFormat.format(tps));
                }else if(tps > 19){
                    sender.sendMessage(Citybuild.getPrefix() + "§aTPS: " + TPSFormat.format(tps));
                }else if(tps > 14){
                    sender.sendMessage(Citybuild.getPrefix() + "§eTPS: " + TPSFormat.format(tps));
                }else if(tps > 9){
                    sender.sendMessage(Citybuild.getPrefix() + "§cTPS: " + TPSFormat.format(tps));
                }else if(tps < 9){
                    sender.sendMessage(Citybuild.getPrefix() + "§4TPS: " + TPSFormat.format(tps));
                }
            }else{
                sender.sendMessage(Citybuild.getPrefix() + "§cNutze /lag!");
            }
        }else{
            sender.sendMessage(Citybuild.getNoperm());
        }
        return false;
    }
}
