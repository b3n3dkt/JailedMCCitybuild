package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reply implements CommandExecutor {

    Citybuild plugin;

    public Reply(Citybuild plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player && args.length > 0){
            Player messager = (Player) MSG.map;
            if(Bukkit.getOfflinePlayer(MSG.map.get(messager)).getPlayer() != null){
            if(MSG.map.get(messager) != null){
                Player reciever = Bukkit.getOfflinePlayer(MSG.map.get(messager)).getPlayer();
                args[0] = "";
                String message = "";
                for(int i = 0; i < args.length; i++){
                    message += " " + args[i];
                }
                messager.sendMessage("§8[§eMSG§8] §8»§7 " + reciever.getName() + ": §7" + message);
                reciever.sendMessage("§8[§eMSG§8] §8»§7 " + messager.getName() + ": §7" + message);
                return true;
                }
            } else {
                sender.sendMessage(Citybuild.getPrefix() + "§cDer Spieler ist nicht online!");
                return true;
            }

        }
        return false;
    }

}