package me.b3n3dkt.commands;

import java.util.ArrayList;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee implements CommandExecutor {
    public static ArrayList<Player> getuser = new ArrayList();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (p.hasPermission("jailedmc.command.invsee")) {
                if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t != null) {
                        if (!t.getName().equals(p.getName())) {
                            if (!t.hasPermission("jailedmc.command.invsee.bypass")) {
                                getuser.add(p);
                                p.openInventory(t.getInventory());
                            } else {
                                p.sendMessage(Citybuild.getPrefix() + "§cDu kannst nicht in das Inventar von dem Spieler gucken!");
                            }
                        } else {
                            p.sendMessage(Citybuild.getPrefix() + "§cDu kannst nicht in dein eigenes Inventar gucken!");
                        }
                    } else {
                        p.sendMessage(Citybuild.getPrefix() + "§cDer angegebene Spieler ist nicht online!");
                    }
                } else {
                    p.sendMessage(Citybuild.getPrefix() + "§cNutze bitte /invsee <Spieler>!");
                }
            } else {
                p.sendMessage(Citybuild.getNoperm());
            }
        }

        return false;
    }
}
