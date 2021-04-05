package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (p.hasPermission("jailedmc.command.heal")) {
                if (args.length == 0) {
                    p.setHealth(20.0D);
                    p.setFoodLevel(20);
                    p.sendMessage(Citybuild.getPrefix() + "§7Du hast dich geheilt!");
                } else if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t != null) {
                        t.setHealth(20.0D);
                        t.setFoodLevel(20);
                        p.sendMessage(Citybuild.getPrefix() + "§7Du hast §8'§3" + t.getName() + "§8' §7geheilt!");
                        t.sendMessage(Citybuild.getPrefix() + "§7Du wurdest von §8'§3" + p.getName() + "§8' §7geheilt!");
                    } else {
                        p.sendMessage(Citybuild.getPrefix() + "§cDer angegebene Spieler ist nicht online!");
                    }
                } else {
                    p.sendMessage(Citybuild.getPrefix() + "§cNutze /heal <Spieler>!");
                }
            } else {
                p.sendMessage(Citybuild.getNoperm());
            }
        }

        return false;
    }
}
