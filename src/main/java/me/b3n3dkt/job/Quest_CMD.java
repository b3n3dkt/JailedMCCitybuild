package me.b3n3dkt.job;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.PlayerData;
import me.b3n3dkt.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Quest_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("jailedmc.command.quest")){
            if(args.length == 1){ //quest <new> <Spieler>
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    PlayerData data = new PlayerData(player);
                    Quest quest = new Quest(data.getJob(), player);
                    quest.getNewQuest(data.getJob());
                    player.sendMessage(Citybuild.getPrefix() + "§7Du hast nun eine neue Quest bekommen, eventuell musst du reconnecten!");
                }else{
                    sender.sendMessage(Citybuild.getPrefix() + "§cDu bist die Konsole!");
                }
            }else if(args.length == 2){
                OfflinePlayer op = Bukkit.getOfflinePlayer(Utility.getUUIDFromName(args[1]));
                PlayerData data = new PlayerData((Player)op);
                if(data.exist() != true){ return true;}
                Quest quest = new Quest(data.getJob(), (Player)op);
                sender.sendMessage(Citybuild.getPrefix() + "§aDer angegebene Spieler bekommt nun eine neue Quest, er muss eventuell reconnecten!");
                quest.getNewQuest(data.getJob());
            }else{
                sender.sendMessage(Citybuild.getPrefix() + "§cNutze /quest <new> <Spieler>!");
            }
        }else{
            sender.sendMessage(Citybuild.getNoperm());
        }
        return false;
    }
}
