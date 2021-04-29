package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.Score;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;


public class Pay implements CommandExecutor {
    private static DecimalFormat df = new DecimalFormat("0.00");

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cDu bist die Konsole, die darf das nicht!");
            return true;
        }

        Player p = (Player)sender;
        if (args.length == 2) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                if (args[1].startsWith("-")) {
                    sender.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cDu darfst keine Minus beträge bezahlen!");
                    return true;
                }
                if (args[0].equalsIgnoreCase(p.getName())) {
                    p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cDu darfst dir selber nichts Payen!");
                    return true;
                }
                Double amount = Double.valueOf(args[1]);

                Score scoreTBoard = new Score(t);

                Score scorePBoard = new Score(p);
                if (MySQL.hasEnoughMoney(p.getUniqueId().toString(), amount.doubleValue())) {
                    Double pamount = Double.valueOf(MySQL.getcoins(p.getUniqueId().toString()) - amount.doubleValue());
                    Double tamount = Double.valueOf(MySQL.getcoins(t.getUniqueId().toString()) + amount.doubleValue());
                    if (MySQL.isRegistered(t.getUniqueId().toString())) {
                        MySQL.setcoins(p.getUniqueId().toString(), pamount.doubleValue());
                        MySQL.setcoins(t.getUniqueId().toString(), tamount.doubleValue());
                        p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§7Du hast dem Spieler §8'§e" + t.getName() + "§8 §e" + df.format(amount) + "§8' §7 Coins gegeben!");
                        t.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§7Du hast von §8'§e" + p.getName() + "§8 §e" + df.format(amount) + "§8' §7Coins bekommen!");
                        scoreTBoard.update();
                        scorePBoard.update();
                    } else if (!MySQL.isRegistered(t.getUniqueId().toString())) {
                        p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cDieser Spieler existiert nicht");
                    }
                } else {
                    p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cDu hast nicht genug Geld!");
                }

            }
            else if (t == null) {
                p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cDer Spieler ist nicht Online!");
            }
        } else if (args.length != 2) {
            p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cNutze /pay <Spieler> <Anzahl>");
        }


        return false;
    }
}
