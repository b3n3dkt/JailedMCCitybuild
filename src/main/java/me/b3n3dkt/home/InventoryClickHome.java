package me.b3n3dkt.home;

import java.util.Arrays;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickHome implements Listener {
    public static int homesint = 0;
    public static int taskid;
    public static int cs = 3;

    @EventHandler
    public void onHandle(InventoryClickEvent e) {
       if(e.getInventory() == null){ return;}
        if(e.getCurrentItem() == null) { return;}
        Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getName().equalsIgnoreCase("§3Deine Homes")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.TRIPWIRE_HOOK) {
                try {
                    e.setCancelled(true);
                    p.teleport(MySQL.getLocation(p, e.getCurrentItem().getItemMeta().getDisplayName().replace("§b", "")));
                    p.sendMessage(Citybuild.getPrefix() + "§7Du hast dich zu deinem Home §8'§3" + e.getCurrentItem().getItemMeta().getDisplayName().replace("§b", "") + "§8' §7teleportiert!");
                    p.closeInventory();
                    cs = 3;
                    Bukkit.getScheduler().cancelTask(taskid);
                } catch (Exception var9) {
                }
            }
        } else if (e.getInventory().getName().equalsIgnoreCase("§3Homes Löschen")) {
            e.setCancelled(true);

            try {
                MySQL.deleteHome(e.getCurrentItem().getItemMeta().getDisplayName().replace("§b", ""), p);
                p.sendMessage(Citybuild.getPrefix() + "§7Du hast das Home §8'§3" + e.getCurrentItem().getItemMeta().getDisplayName().replace("§b", "") + "§8' §7gelöscht!");
                p.closeInventory();
            } catch (Exception var8) {
            }
        } else if (e.getInventory().getName().equalsIgnoreCase("§3Home GUI")) {
            e.setCancelled(true);
            Inventory inv;
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Deine Homes")) {
                if (MySQL.gethomesint(p) >= 1) {
                    inv = Bukkit.createInventory((InventoryHolder)null, 3*9, "§3Deine Homes");
                    MySQL.getHomes(p).forEach((mod) -> {
                        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName("§b" + mod);
                        meta.setLore(Arrays.asList("§7* Klicke zum teleportieren *"));
                        item.setItemMeta(meta);
                        inv.addItem(new ItemStack[]{item});
                        p.openInventory(inv);
                        for(int i = 26;i>=MySQL.gethomesint(p);i--){
                            ItemStack barrier = new ItemStack(Material.BARRIER, 1);
                            ItemMeta barriermeta = barrier.getItemMeta();
                            barriermeta.setDisplayName(" §7");
                            barrier.setItemMeta(barriermeta);
                            inv.setItem(i, barrier);
                        }
                    });
                } else {
                    p.sendMessage(Citybuild.getPrefix() + "§7Du besitzt keine Homes!");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Homes Löschen")) {
                try {
                    if (MySQL.gethomesint(p) >= 1) {
                        inv = Bukkit.createInventory((InventoryHolder)null, 3*9, "§bDelete Homes");
                        MySQL.getHomes(p).forEach((mod) -> {
                            ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName("§b" + mod);
                            meta.setLore(Arrays.asList("§7* Klicke zum löschen *"));
                            item.setItemMeta(meta);
                            inv.addItem(new ItemStack[]{item});
                            p.openInventory(inv);
                        });
                    } else {
                        p.sendMessage(Citybuild.getPrefix() + "§7Du besitzt keine Homes!");
                    }
                } catch (Exception var7) {
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Home setzen")) {
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
                AnvilGUI gui = new AnvilGUI(p, (event) -> {
                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                        if (event.getName().length() < 10) {
                            if (homesint > MySQL.gethomesint(p)) {
                                if (!MySQL.isHomeExist(p, event.getName())) {
                                    if (event.getName() != p.getName()) {
                                        event.setWillClose(true);
                                        event.setWillDestroy(true);
                                        MySQL.addHome(p, event.getName());
                                        p.sendMessage(Citybuild.getPrefix() + "§7Du hast das Home §8'§3" + event.getName() + "§8' §7erstellt!");
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
                    } else {
                        event.setWillClose(false);
                        event.setWillDestroy(false);
                    }

                });
                ItemStack i = new ItemStack(Material.PAPER);
                ItemMeta im = i.getItemMeta();
                im.setDisplayName("Home-Name");
                i.setItemMeta(im);
                gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);
                gui.open();
            }
        }

    }
}
