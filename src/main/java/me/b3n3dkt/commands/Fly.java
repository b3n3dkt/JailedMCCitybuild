package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        if (sender instanceof Player) {
            if (p.hasPermission("jailedmc.command.fly")) {
                if (p.isFlying()) {
                    p.setAllowFlight(false);
                    p.sendMessage(Citybuild.getPrefix() + "§cDu wurdest aus dem Flugmodus gesetzt!");
                } else {
                    p.setAllowFlight(true);
                    p.sendMessage(Citybuild.getPrefix() + "§aDu wurdest in den Flugmodus versetzt!");
                }
            } else {
                p.sendMessage(Citybuild.getNoperm());
            }
        }

        return false;
    }
}
