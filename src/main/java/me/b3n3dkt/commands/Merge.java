package me.b3n3dkt.commands;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.config.C;
import com.intellectualcrafters.plot.object.Plot;
import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.Score;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Merge implements CommandExecutor {

    public static ArrayList<Player> merge = new ArrayList();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
                if (args.length == 0) {

                    Integer i = Integer.valueOf(0);

                    while (i.intValue() < 100) { i = Integer.valueOf(i.intValue() + 1); p.sendMessage(" "); }

                    p.sendMessage("§8§m----------§r§8(§c§lACHTUNG§8)§m----------");
                    p.sendMessage("§7-Schaue in die Richtung in der du deine Grundstücke mergen möchtest.");
                    p.sendMessage("§7-ALLE Grundstücke §fmüssen dir gehören, ansonsten funktioniert es nicht!");
                    p.sendMessage("§7-Nach dem Kauf wird dein Grundstück gemerged. Sollte etwas verbuggt sein melde dich im Discord!");
                    p.sendMessage("§7-Möchtest du dieses Plot für §a50.000 Coins §7wirklich Mergen?");
                    p.sendMessage("§7-Dann gib §6/merge confirm §7ein.");
                    p.sendMessage("§8§m----------§r§8(§c§lACHTUNG§8)§m----------");

                }
                else if (args[0].equalsIgnoreCase("confirm")) {
                    if (MySQL.getcoins(p.getUniqueId().toString()) >= 50000) {
                        PlotAPI plotAPI = new PlotAPI();
                        Plot plot = plotAPI.getPlot(p.getLocation());
                        Score sb = new Score(p);
                        if(plot == null){p.sendMessage(Citybuild.getPrefix() + "§cDu befindest dich nicht auf einem Plot");p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F); return true;}
                        if(isOwner(p, plot)){
                                merge.add(p);
                                p.chat("/p merge");
                                MySQL.setcoins(p.getUniqueId().toString(), MySQL.getcoins(p.getUniqueId().toString())-50000);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
                                sb.update();
                            } else {
                                p.sendMessage(Citybuild.getPrefix() + "§cDas ist nicht dein Plot!");
                            }
                    } else {
                        p.sendMessage(Citybuild.getPrefix() + "§cDu hast leider nicht genügend Geld!");
                    }
                } else {

                    Integer i = Integer.valueOf(0);

                    while (i.intValue() < 100) { i = Integer.valueOf(i.intValue() + 1); p.sendMessage(" "); }

                    p.sendMessage("§8§m----------§r§8(§c§lACHTUNG§8)§m----------");
                    p.sendMessage("§7-Schaue in die Richtung in der du deine Grundstücke mergen möchtest.");
                    p.sendMessage("§7-ALLE Grundstücke §fmüssen dir gehören, ansonsten funktioniert es nicht!");
                    p.sendMessage("§7-Nach dem Kauf wird dein Grundstück gemerged. Sollte etwas verbuggt sein melde dich im Discord!");
                    p.sendMessage("§7-Möchtest du dieses Plot für §a50.000 Coins §7wirklich Mergen?");
                    p.sendMessage("§7-Dann gib §6/merge confirm §7ein.");
                    p.sendMessage("§8§m----------§r§8(§c§lACHTUNG§8)§m----------");
                }
        } else {
            Bukkit.getConsoleSender().sendMessage(Citybuild.getPrefix() + "§cDie Console kann nicht mergen!");
        }

        return true;
    }

    public static boolean isOwner(Player p, Plot plot) {
        if (!plot.hasOwner())
            return false;
        if (p.hasPermission("jailedmc.rand.admin"))
            return true;
        if (plot.isOwner(p.getUniqueId())) {
            return true;
        }
        return false;
    }
}
