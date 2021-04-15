package me.b3n3dkt.rand;

import me.b3n3dkt.Citybuild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Rand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0){
                Inventorys.openRandMenü(player);
            }else{
                player.sendMessage(Citybuild.getPrefix() + "§cNutze /rand!");
            }
        }
        return false;
    }
}
