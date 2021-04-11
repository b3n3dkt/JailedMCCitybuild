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
            Player messager = (Player) sender;
            if(MSG.map.get(messager) != null){
                Player reciever = MSG.map.get(messager);
                if(MSG.map.get(reciever) == null){
                    sender.sendMessage(Citybuild.getPrefix() + "§cDu wurdest von keinem Spieler angeschrieben!");
                    return true;
                }
                String message = "";
                for(int i = 0; i < args.length; i++){
                    if(message == ""){
                        message = args[i];
                    }else {
                        message += " " + args[i];
                    }
                }
                messager.sendMessage("§8[§eMSG§8] §7Du §8»§7 " + reciever.getName() + "§8: §7" + message);
                reciever.sendMessage("§8[§eMSG§8] §7" + messager.getName() + " §8»§7 Dir§8: §7" + message);
                return true;
                }else {
                sender.sendMessage(Citybuild.getPrefix() + "§cDu wurdest von keinem Spieler angeschrieben!");
                return true;
            }
        }

        return false;
    }

}