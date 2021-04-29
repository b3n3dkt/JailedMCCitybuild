package me.b3n3dkt.home;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Home implements CommandExecutor {
    public static int homesint = 0;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(p.getWorld().getName().equalsIgnoreCase("plots")){
            p.sendMessage(Citybuild.getPrefix() + "§7Du kannst nur in der Farmwelt Homes setzen!");
            return true;
        }
        if (p.isOp()) {
            homesint = 18;
        } else if (p.hasPermission("homes.use.1")) {
            homesint = 1;
        } else if (p.hasPermission("homes.use.1")) {
            homesint = 1;
        } else if (p.hasPermission("homes.use.2")) {
            homesint = 2;
        } else if (p.hasPermission("homes.use.3")) {
            homesint = 3;
        } else if (p.hasPermission("homes.use.4")) {
            homesint = 4;
        } else if (p.hasPermission("homes.use.5")) {
            homesint = 5;
        } else if (p.hasPermission("homes.use.6")) {
            homesint = 6;
        } else if (p.hasPermission("homes.use.7")) {
            homesint = 7;
        } else if (p.hasPermission("homes.use.8")) {
            homesint = 8;
        } else if (p.hasPermission("homes.use.9")) {
            homesint = 9;
        } else if (p.hasPermission("homes.use.10")) {
            homesint = 10;
        } else if (p.hasPermission("homes.use.11")) {
            homesint = 11;
        } else if (p.hasPermission("homes.use.12")) {
            homesint = 12;
        } else if (p.hasPermission("homes.use.13")) {
            homesint = 13;
        } else if (p.hasPermission("homes.use.14")) {
            homesint = 14;
        } else if (p.hasPermission("homes.use.15")) {
            homesint = 15;
        } else if (p.hasPermission("homes.use.16")) {
            homesint = 16;
        } else if (p.hasPermission("homes.use.17")) {
            homesint = 17;
        } else if (p.hasPermission("homes.use.18")) {
            homesint = 18;
        } else {
            homesint = 0;
        }

        int gethomespermsmax = homesint;
        Inventory inv;
        if (strings.length == 0) {
            inv = Bukkit.createInventory((InventoryHolder)null, 3*9, "§3Home GUI");
            ItemStack item = new ItemStack(Material.ENDER_PEARL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§7Deine Homes");
            item.setItemMeta(meta);
            ItemStack item2 = new ItemStack(Material.BARRIER);
            ItemMeta meta2 = item2.getItemMeta();
            meta2.setDisplayName("§7Homes Löschen");
            item2.setItemMeta(meta2);
            ItemStack item22 = new ItemStack(Material.GRASS);
            ItemMeta meta22 = item22.getItemMeta();
            meta22.setDisplayName("§7Home setzen");
            ItemStack glas = new ItemStack(Material.STAINED_GLASS_PANE, 1 ,(short) 7);
            ItemMeta glasmeta = glas.getItemMeta();
            glasmeta.setDisplayName("§7");
            glas.setItemMeta(glasmeta);
            for(int i = 0;i<3*9;i++){
                inv.setItem(i, glas);
            }
            item22.setItemMeta(meta22);
            inv.setItem(10, item);
            inv.setItem(16, item2);
            inv.setItem(13, item22);
            p.openInventory(inv);
        } else if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("create")) {
                if (strings[1].length() < 10) {
                    if (homesint > MySQL.gethomesint(p)) {
                        if (!MySQL.isHomeExist(p, strings[1])) {
                            if (strings[1] != p.getName()) {
                                MySQL.addHome(p, strings[1]);
                                p.sendMessage(Citybuild.getPrefix() + "§7Du hast das Home §8'§3" + strings[1] + "§8' §7erstellt!");
                            } else {
                                p.sendMessage(Citybuild.getPrefix() + "§cDu solltest nicht deinen eigenen Namen als Home nutzen!");
                            }
                        } else {
                            p.sendMessage(Citybuild.getPrefix() + "§cDas Home existiert bereits!");
                        }
                    } else {
                        p.sendMessage(Citybuild.getPrefix() + "§cDu hast die maximale Anzahl an Homes erreicht! §7(" + gethomespermsmax + "§7 Stück)");
                    }
                } else {
                    p.sendMessage(Citybuild.getPrefix() + "§cDer Name kann nicht länger als 10 Zeichen sein!");
                }
            } else if (strings[0].equalsIgnoreCase("delete")) {
                if (MySQL.gethomesint(p) >= 1) {
                    inv = Bukkit.createInventory((InventoryHolder)null, 18, "§3Homes Löschen");
                    MySQL.getHomes(p).forEach((mod) -> {
                        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName("§b" + mod);
                        item.setItemMeta(meta);
                        inv.addItem(new ItemStack[]{item});
                        p.openInventory(inv);
                    });
                } else {
                    p.sendMessage(Citybuild.getPrefix() + "§7Du besitzt keine Homes!");
                }
            } else {
                p.sendMessage(Citybuild.getPrefix() + "§cNutze /home <create|delete|name>!");
            }
        } else if (strings.length == 1) {
            p.sendMessage(Citybuild.getPrefix() + "§cNutze /home <create|delete|name>!");
        } else {
            p.sendMessage(Citybuild.getPrefix() + "§cNutze /home <create|delete|name>!");
        }

        return false;
    }
}
