package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Rename implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length < 1 && args.length != 1) {
                p.sendMessage(Citybuild.getPrefix() + "§cNutze /rename <Name>");
            } else {
                String name = "";

                for(int i = 0; i < args.length; ++i) {
                    name = name + " " + args[i];
                }

                ItemStack stack = p.getItemInHand();
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                stack.setItemMeta(meta);
                p.setItemInHand(stack);
                p.sendMessage(Citybuild.getPrefix() + "§aDu hast das Item erfolgreich umbenannt!");
            }
        }

        return false;
    }
}
