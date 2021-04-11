package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MSG implements CommandExecutor {

    Citybuild plugin;


    public MSG(Citybuild plugin) {
        this.plugin = plugin;
    }
    public static HashMap<Player, Player> map = new HashMap<>();
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player && args.length > 0){
            if(Bukkit.getOfflinePlayer(args[0]).getPlayer() != null){
                Player messager = (Player) sender;
                Player reciever = Bukkit.getOfflinePlayer(args[0]).getPlayer();
                map.remove(messager);
                map.remove(reciever);
                map.put(messager, reciever);
                map.put(reciever, messager);
                args[0] = ""; //msg <Spieler> <Nachricht>
                String message = "";
                for(int i = 1; i < args.length; i++){
                    if(message == ""){
                        message = args[i];
                    }else {
                        message += " " + args[i];
                    }
                }
                messager.sendMessage("§8[§eMSG§8] §7Du §8»§7 " + reciever.getName() + ": §7" + message);
                reciever.sendMessage("§8[§eMSG§8] §7" + messager.getName() + " §8»§7Dir§8: §7" + message);
                return true;
            } else {
                sender.sendMessage(Citybuild.getPrefix() + "§cDer Spieler ist nicht online!");
                return true;
            }

        }
        return false;
    }

}