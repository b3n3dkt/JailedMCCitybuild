package me.b3n3dkt.commands;

import java.util.Arrays;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Sign implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length >= 1) {
                if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
                    ItemStack itemnew = p.getItemInHand();
                    ItemMeta itemnewmeta = itemnew.getItemMeta();

                    try {
                        String Message;
                        int i;
                        if (itemnewmeta.hasLore()) {
                            if (!((String)itemnewmeta.getLore().get(2)).contains("§7Signiert")) {
                                Message = "";

                                for(i = 0; i < args.length; ++i) {
                                    Message = Message + args[i] + " ";
                                }

                                p.sendMessage(Citybuild.getPrefix() + "§aDein Item wurde signiert.");
                                itemnewmeta.setLore(Arrays.asList(" ", Message.replaceAll("&", "§"), "§7Signiert von §a" + p.getName()));
                                itemnew.setItemMeta(itemnewmeta);
                            } else {
                                p.sendMessage(Citybuild.getPrefix() + "§cDas Item ist bereits signiert.");
                            }
                        } else {
                            Message = "";

                            for(i = 0; i < args.length; ++i) {
                                Message = Message + args[i] + " ";
                            }

                            p.sendMessage(Citybuild.getPrefix() + "§aDein Item wurde signiert.");
                            itemnewmeta.setLore(Arrays.asList(" ", Message.replaceAll("&", "§"), "§7Signiert von §a" + p.getName()));
                            itemnew.setItemMeta(itemnewmeta);
                            p.setItemInHand(itemnew);
                        }
                    } catch (Exception var10) {
                    }
                } else {
                    p.sendMessage(Citybuild.getPrefix() + "§cDu musst ein Item in der Hand haben!");
                }
            } else {
                p.sendMessage(Citybuild.getPrefix() + "§cBenutze bitte /Sign <Signierung>!");
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(Citybuild.getPrefix() + "§cDie Console kann nichts signieren!");
        }

        return true;
    }
}
