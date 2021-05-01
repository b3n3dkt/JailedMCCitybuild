package me.b3n3dkt.auktionshaus;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.Items;
import me.b3n3dkt.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

public class InventoryClick_YourItems implements Listener {

    public static Inventory invYItem = Bukkit.createInventory(null, 6*9, "§eDeine Items");
    public static Integer sindex = null;
    public static boolean isInDelMen = false;
    public static boolean isInPrChMen = false;
    public static double currentValue = 0;

    @EventHandler
    public void onHandle(InventoryClickEvent event){
        if (event.getInventory() == null) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack glas = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
        ItemMeta glasmeta = glas.getItemMeta();
        glasmeta.setDisplayName("§6");
        glas.setItemMeta(glasmeta);
        if(event.getView().getTitle().equals("§eDeine Items")){ //Your ITems
            if(event.getInventory() != player.getInventory()) {
                event.setCancelled(true);
            }
            if(event.getCurrentItem().getItemMeta().hasLore() != true){return;}
            if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
                if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§aNächste Seite")) {
                    PlayerData data = new PlayerData(player, player.getUniqueId());
                    String tempLore = event.getCurrentItem().getItemMeta().getLore().get(1);
                    String temp = tempLore.replace("§7Momentane Seite: ", "");
                    Integer currentPage = Integer.parseInt(temp);
                    Integer currentPageNew = currentPage + 1;

                    if(currentPage < data.getPages()) {
                        for(int i = 0; i<45;i++) {
                            invYItem.setItem(i, new ItemStack(Material.AIR));
                        }

                        ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                        ItemMeta greenglasmeta = greenglas.getItemMeta();
                        greenglasmeta.setDisplayName("§aNächste Seite");
                        ArrayList<String> greenlore = new ArrayList<String>();
                        greenlore.add("§7");
                        greenlore.add("§7Momentane Seite: " + currentPageNew);
                        greenlore.add("§7");
                        greenglasmeta.setLore(greenlore);
                        greenglas.setItemMeta(greenglasmeta);

                        ItemStack redglas= new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                        ItemMeta redglasmeta = redglas.getItemMeta();
                        redglasmeta.setDisplayName("§cLetzte Seite");
                        ArrayList<String> redlore = new ArrayList<String>();
                        redlore.add("§7");
                        redlore.add("§7Momentane Seite: " + currentPageNew);
                        redlore.add("§7");
                        redglasmeta.setLore(redlore);
                        redglas.setItemMeta(redglasmeta);

                        invYItem.setItem(45, redglas);
                        invYItem.setItem(53, new ItemStack(Material.AIR));
                        invYItem.setItem(53, greenglas);
                        Integer index = data.getIndex();
                        int slot = 0;
                        Integer tempSlots = currentPage*46;
                        for(int i = tempSlots;i<index;i++) {
                            if(data.getItem(i) != null) {
                                if(slot > 44) {
                                }else {
                                    ItemStack stack = data.getItem(i);
                                    ItemMeta stackmeta = stack.getItemMeta();
                                    ArrayList<String> stacklore = new ArrayList<String>();
                                    stacklore.add("§7");
                                    stacklore.add("§7-Preis: §a" + data.getPrice(i) + " Coins");
                                    stacklore.add("§7-Verkäufer: §a" + data.getPlayerName(i));
                                    stacklore.add("§7-ID: §a" + i);
                                    stacklore.add("§7");
                                    stackmeta.setLore(stacklore);
                                    stack.setItemMeta(stackmeta);

                                    invYItem.setItem(slot, stack);
                                    slot++;
                                }
                            }
                        }
                    }

                }else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("§cLetzte Seite")) {
                    PlayerData data = new PlayerData(player, player.getUniqueId());
                    String tempLore = event.getCurrentItem().getItemMeta().getLore().get(1);
                    String temp = tempLore.replace("§7Momentane Seite: ", "");
                    Integer currentPage = Integer.parseInt(temp);
                    Integer currentPageNew = currentPage -1;
                    for(int i = 0; i<45;i++) {
                        invYItem.setItem(i, new ItemStack(Material.AIR));
                    }
                    ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                    ItemMeta greenglasmeta = greenglas.getItemMeta();
                    greenglasmeta.setDisplayName("§aNächste Seite");
                    ArrayList<String> greenlore = new ArrayList<String>();
                    greenlore.add("§7");
                    greenlore.add("§7Momentane Seite: " + currentPageNew);
                    greenlore.add("§7");
                    greenglasmeta.setLore(greenlore);
                    greenglas.setItemMeta(greenglasmeta);

                    ItemStack redglas= new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                    ItemMeta redglasmeta = redglas.getItemMeta();
                    redglasmeta.setDisplayName("§cLetzte Seite");
                    ArrayList<String> redlore = new ArrayList<String>();
                    redlore.add("§7");
                    redlore.add("§7Momentane Seite: " + currentPageNew);
                    redlore.add("§7");
                    redglasmeta.setLore(redlore);
                    redglas.setItemMeta(redglasmeta);

                    ItemStack backglas = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                    ItemMeta backglasmeta = backglas.getItemMeta();
                    backglasmeta.setDisplayName("§cZurück"); //§cZurück
                    backglas.setItemMeta(backglasmeta);

                    Integer index = data.getIndex();
                    int slot = 0;
                    Integer tempSlots = (currentPageNew - 1)*46;

                    if(currentPageNew == 1) {
                        invYItem.setItem(45, backglas);
                        tempSlots = 0;
                    }else {
                        invYItem.setItem(45, redglas);
                    }
                    invYItem.setItem(53, new ItemStack(Material.AIR));
                    invYItem.setItem(53, greenglas);
                    for(int i = tempSlots;i<index;i++) {
                        if(data.getItem(i) != null) {
                            if(slot > 44) {
                            }else {
                                ItemStack stack = data.getItem(i);
                                ItemMeta stackmeta = stack.getItemMeta();
                                ArrayList<String> stacklore = new ArrayList<String>();
                                stacklore.add("§7");
                                stacklore.add("§7-Preis: §a" + data.getPrice(i) + " Coins");
                                stacklore.add("§7-Verkäufer: §a" + data.getPlayerName(i));
                                stacklore.add("§7-ID: §a" + i);
                                stacklore.add("§7");
                                stackmeta.setLore(stacklore);
                                stack.setItemMeta(stackmeta);

                                invYItem.setItem(slot, stack);
                                slot++;
                            }
                        }
                    }
                }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cZurück")) {
                    CMD_Auktionshaus.openAuktionshaus(player);
                }
            }else if(event.getCurrentItem().getItemMeta().hasLore() == true){
                PlayerData data = new PlayerData(player, player.getUniqueId());
                String tempLore = event.getCurrentItem().getItemMeta().getLore().get(3);
                String temp = tempLore.replace("§7-ID: §a", "");
                Integer index = Integer.parseInt(temp);
                Inventory inv = Bukkit.createInventory(null, 3*9, "§6Item Einstellungen"); //§6Item Einstellungen
                ItemStack stack = new ItemStack(data.getItem(index));

                ItemStack redglas= new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta redglasmeta = redglas.getItemMeta();
                redglasmeta.setDisplayName("§cAbbruch"); //§cAbbruch
                redglas.setItemMeta(redglasmeta);

                ItemStack buysign = new ItemStack(Material.OAK_SIGN, 1);
                ItemMeta buysignmeta = buysign.getItemMeta();
                buysignmeta.setDisplayName("§7Aktuelles Verkaufsangebot"); //§7Aktuelles Verkaufsangebot
                ArrayList<String> buylore = new ArrayList<String>();
                buylore.add("§7");
                buylore.add("§7-Preis: §a" + data.getPrice(index) + " Coins");
                buylore.add("§7-ID: §a" + index);
                buylore.add("§7");
                buysignmeta.setLore(buylore);
                buysign.setItemMeta(buysignmeta);

                ItemStack anvil= new ItemStack(Material.ANVIL, 1);
                ItemMeta anvilmeta = redglas.getItemMeta();
                anvilmeta.setDisplayName("§aPreis ändern"); //§aPreis ändern
                anvil.setItemMeta(anvilmeta);

                ItemStack barrier= new ItemStack(Material.BARRIER, 1);
                ItemMeta barriermeta = redglas.getItemMeta();
                barriermeta.setDisplayName("§4Verkaufsangebot löschen"); //§4Verkaufsangebot löschen
                barrier.setItemMeta(barriermeta);

                player.closeInventory();
                player.openInventory(inv);
                for(int i = 0;i<3*9;i++) {
                    inv.setItem(i, glas);
                }

                inv.setItem(4, buysign);
                inv.setItem(13, stack);
                inv.setItem(22, redglas);
                inv.setItem(15, anvil);
                inv.setItem(11, barrier);

                player.updateInventory();
                sindex = index;
            }
        }else if(event.getView().getTitle().equals("§6Item Einstellungen")){ //Item Settings
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().hasDisplayName() != true){return;}
            PlayerData data = new PlayerData(player, player.getUniqueId());
            currentValue = data.getPrice(sindex);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPreis ändern")) {
                Inventory inv = Bukkit.createInventory(null, 5*9, "§6Preis ändern"); //§6Preis ändern
                ItemStack stack = event.getInventory().getItem(13);

                ItemStack p1c = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                ItemMeta p1cmeta = p1c.getItemMeta();
                p1cmeta.setDisplayName("§aPlus 0.01 Coins");
                p1c.setItemMeta(p1cmeta);

                ItemStack p1d = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                ItemMeta p1dmeta = p1d.getItemMeta();
                p1dmeta.setDisplayName("§aPlus 1 Coins");
                p1d.setItemMeta(p1dmeta);

                ItemStack p10d = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                ItemMeta p10dmeta = p10d.getItemMeta();
                p10dmeta.setDisplayName("§aPlus 10 Coins");
                p10d.setItemMeta(p10dmeta);

                ItemStack p100d = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                ItemMeta p100dmeta = p100d.getItemMeta();
                p100dmeta.setDisplayName("§aPlus 100 Coins");
                p100d.setItemMeta(p100dmeta);

                ItemStack m1c = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta m1cmeta = m1c.getItemMeta();
                m1cmeta.setDisplayName("§cMinus 0.01 Coins");
                m1c.setItemMeta(m1cmeta);

                ItemStack m1d = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta m1dmeta = m1d.getItemMeta();
                m1dmeta.setDisplayName("§cMinus 1 Coins");
                m1d.setItemMeta(m1dmeta);

                ItemStack m10d = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta m10dmeta = m10d.getItemMeta();
                m10dmeta.setDisplayName("§cMinus 10 Coins");
                m10d.setItemMeta(m10dmeta);

                ItemStack m100d = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta m100dmeta = m100d.getItemMeta();
                m100dmeta.setDisplayName("§cMinus 100 Coins");
                m100d.setItemMeta(m100dmeta);

                ItemStack sign = new ItemStack(Material.OAK_SIGN, 1);
                ItemMeta signmeta = sign.getItemMeta();
                signmeta.setDisplayName("§7Aktueller Preis: §a" + data.getPrice(sindex)); //§7Aktueller Preis: §a 
                sign.setItemMeta(signmeta);

                ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                ItemMeta greenglasmeta = greenglas.getItemMeta();
                greenglasmeta.setDisplayName("§aBestätige Preisänderung"); //§aBestätige Preisänderung
                ArrayList<String> buylore = new ArrayList<String>();
                buylore.add("§7");
                buylore.add("§7-Preis: §a" + data.getPrice(sindex) + " Coins");
                buylore.add("§7-ID: §a" + sindex);
                buylore.add("§7");
                greenglasmeta.setLore(buylore);
                greenglas.setItemMeta(greenglasmeta);

                ItemStack redglas= new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta redglasmeta = redglas.getItemMeta();
                redglasmeta.setDisplayName("§cAbbruch");
                redglas.setItemMeta(redglasmeta);

                for(int i = 0; i < 45; i++) {
                    inv.setItem(i, glas);
                }

                inv.setItem(36, redglas);
                inv.setItem(44, greenglas);
                inv.setItem(40, stack);
                inv.setItem(34, p1c);
                inv.setItem(25, p1d);
                inv.setItem(16, p10d);
                inv.setItem(7, p100d);
                inv.setItem(1, m100d);
                inv.setItem(10, m10d);
                inv.setItem(19, m1d);
                inv.setItem(28, m1c);
                inv.setItem(13, sign);

                player.openInventory(inv);
                isInPrChMen = true;

            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Verkaufsangebot löschen")) {
                Inventory inv = Bukkit.createInventory(null, 3*9, "§6Bestätigen"); //§6Bestätigen
                ItemStack stack = event.getInventory().getItem(13);

                ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                ItemMeta greenglasmeta = greenglas.getItemMeta();
                greenglasmeta.setDisplayName("§aLöschen"); //§aDelete
                greenglas.setItemMeta(greenglasmeta);

                ItemStack redglas= new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                ItemMeta redglasmeta = redglas.getItemMeta();
                redglasmeta.setDisplayName("§cAbbruch");
                redglas.setItemMeta(redglasmeta);

                ItemStack buysign = new ItemStack(Material.OAK_SIGN, 1);
                ItemMeta buysignmeta = buysign.getItemMeta();
                buysignmeta.setDisplayName("§7Aktuelles Verkaufsangebot");
                ArrayList<String> buylore = new ArrayList<String>();
                buylore.add("§7");
                buylore.add("§7-Preis: §a" + data.getPrice(sindex) + " Coins");
                buylore.add("§7-ID: §a" + sindex);
                buylore.add("§7");
                buysignmeta.setLore(buylore);
                buysign.setItemMeta(buysignmeta);

                for(int i = 0;i<3*9;i++) {
                    inv.setItem(i, glas);
                }

                inv.setItem(4, buysign);
                inv.setItem(11, redglas);
                inv.setItem(15, greenglas);
                inv.setItem(13, stack);

                player.openInventory(inv);

                isInDelMen = true;

            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAbbruch")) {
                openYourItems(player);
            }

        }else if(event.getView().getTitle().equals("§6Bestätigen")){ //Confirm
            Inventory inv = Bukkit.createInventory(null, 9*6, "§6Auktionshaus");
            event.setCancelled(true);
            PlayerData data = new PlayerData(player, player.getUniqueId());
            Items item = new Items();
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§aLöschen")) {
                if(player.getInventory().firstEmpty() != -1) {
                    player.getInventory().addItem(data.getItem(sindex));
                    Integer index = data.getID(sindex);
                    item.deleteItem(index);
                    data.deleteItem(sindex);
                    player.sendMessage(Citybuild.getPrefix() + "§aDu hast das Verkaufangebot gelöscht!");
                    sindex = null;
                    isInDelMen = false;
                    player.closeInventory();
                    inv.clear();
                    for(Player all : CMD_Auktionshaus.inAh) {
                        CMD_Auktionshaus.openAuktionshaus(all);
                    }
                }else {
                    player.sendMessage(Citybuild.getPrefix() + "§cDein Inventar ist voll!");
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAbbruch")) {
                isInDelMen = false;
                sindex = null;
                player.closeInventory();
            }
        }else if(event.getView().getTitle().equals("§6Preis ändern")){ //Change Price
            event.setCancelled(true);
            PlayerData data = new PlayerData(player, player.getUniqueId());
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 0.01 Coins")) {
                currentValue = currentValue + 0.01;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 1 Coins")) {
                currentValue = currentValue + 1;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 10 Coins")) {
                currentValue = currentValue + 10;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 100 Coins")) {
                currentValue = currentValue + 100;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 0.01 Coins")) {
                if(currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 0.01;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 1 Coins")) {
                if(currentValue >= 0 && currentValue <=1) {
                    if(currentValue == 0) {
                        player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                        return;
                    }
                    currentValue = 0;
                    updateSign(player);
                    return;
                }else if(currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 1;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 10 Coins")) {
                if(currentValue >= 0 && currentValue <=10) {
                    if(currentValue == 0) {
                        player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                        return;
                    }
                    currentValue = 0;
                    updateSign(player);
                    return;
                }else if(currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 10;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 100 Coins")) {
                if(currentValue >= 0 && currentValue <=100) {
                    if(currentValue == 0) {
                        player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                        return;
                    }
                    currentValue = 0;
                    updateSign(player);
                    return;
                }else if(currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 100;
                currentValue = round(currentValue, 2);
                updateSign(player);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBestätige Preisänderung")) {
                Items item = new Items();
                Integer index = data.getID(sindex);
                item.setPrice(currentValue, index);
                data.setPrice(currentValue, sindex);
                player.sendMessage(Citybuild.getPrefix() + "§aDu hast den Preis von einem Item geändert!!");
                currentValue = 0;
                player.closeInventory();
                isInPrChMen = false;
                for(Player all : CMD_Auktionshaus.inAh) {
                    CMD_Auktionshaus.openAuktionshaus(all);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAbbruch")) {
                currentValue = 0;
                player.closeInventory();
                isInPrChMen = false;
            }
        }
    }

    public static void openYourItems(Player p) {
        Inventory invYItem = Bukkit.createInventory(null, 6*9, "§eDeine Items");
        for(int i = 0; i<45;i++) {
            invYItem.setItem(i, new ItemStack(Material.AIR));
        }
        ItemStack glas = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
        ItemMeta glasmeta = glas.getItemMeta();
        glasmeta.setDisplayName("§6");
        glas.setItemMeta(glasmeta);
        ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta greenglasmeta = greenglas.getItemMeta();
        greenglasmeta.setDisplayName("§aNächste Seite");
        ArrayList<String> greenlore = new ArrayList<String>();
        greenlore.add("§7");
        greenlore.add("§7Momentane Seite: 1");
        greenlore.add("§7");
        greenglasmeta.setLore(greenlore);
        greenglas.setItemMeta(greenglasmeta);

        ItemStack redglas= new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta redglasmeta = redglas.getItemMeta();
        redglasmeta.setDisplayName("§cZurück");
        redglasmeta.setLore(Arrays.asList("", "§7* Klicke um zurück zu gehen! *")); //§7* Klicke um zurück zu gehen! *
        redglas.setItemMeta(redglasmeta);

        invYItem.setItem(47, glas);
        invYItem.setItem(49, glas);
        invYItem.setItem(51, glas);
        invYItem.setItem(46, glas);
        invYItem.setItem(48, glas);
        invYItem.setItem(50, glas);
        invYItem.setItem(52, glas);
        invYItem.setItem(45, redglas);
        invYItem.setItem(53, greenglas);

        int slot = 0;
        PlayerData data = new PlayerData(p, p.getUniqueId());
        for(int i = 0;i<data.getIndex();i++) {
            if(data.getItem(i) != null) {
                if(slot > 44) {
                }else {
                    ItemStack stack = data.getItem(i);
                    ItemMeta stackmeta = stack.getItemMeta();
                    ArrayList<String> stacklore = new ArrayList<String>();
                    stacklore.add("§7");
                    stacklore.add("§7-Preis: §a" + data.getPrice(i) + " Coins");
                    stacklore.add("§7-Verkäufer: §a" + data.getPlayerName(i));
                    stacklore.add("§7-ID: §a" + i);
                    stacklore.add("§7");
                    stackmeta.setLore(stacklore);
                    stack.setItemMeta(stackmeta);
                    invYItem.setItem(slot, stack);
                    slot++;
                }
            }
        }

        p.openInventory(invYItem);
    }

    public void openYourBids(Player p) {
        Inventory invYOffers = Bukkit.createInventory(null, 6*9, "§eYour Bids");
        for(int i = 0; i<45;i++) {
            invYOffers.setItem(i, new ItemStack(Material.AIR));
        }
        ItemStack glas = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
        ItemMeta glasmeta = glas.getItemMeta();
        glasmeta.setDisplayName("§6");
        glas.setItemMeta(glasmeta);
        ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta greenglasmeta = greenglas.getItemMeta();
        greenglasmeta.setDisplayName("§aNächste Seite");
        ArrayList<String> greenlore = new ArrayList<String>();
        greenlore.add("§7");
        greenlore.add("§7Momentane Seite: 1");
        greenlore.add("§7");
        greenglasmeta.setLore(greenlore);
        greenglas.setItemMeta(greenglasmeta);

        ItemStack redglas= new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta redglasmeta = redglas.getItemMeta();
        redglasmeta.setDisplayName("§cZurück");
        redglas.setItemMeta(redglasmeta);

        invYOffers.setItem(47, glas);
        invYOffers.setItem(49, glas);
        invYOffers.setItem(51, glas);
        invYOffers.setItem(46, glas);
        invYOffers.setItem(48, glas);
        invYOffers.setItem(50, glas);
        invYOffers.setItem(52, glas);
        invYOffers.setItem(45, redglas);
        invYOffers.setItem(53, greenglas);

        int slot = 0;
        PlayerData data = new PlayerData(p, p.getUniqueId());
        for(int i = 0;i<24;i++) {
            if(slot > 44) {
            }else {
                Integer tindex = data.getItemsIndex(i);
                ItemStack stack = data.getItem(i);
                ItemMeta stackmeta = stack.getItemMeta();
                ArrayList<String> stacklore = new ArrayList<String>();
                stacklore.add("§7");
                stacklore.add("§7-Your Offer: §a" + data.getPrice(i) + " Coins");
                stacklore.add("§7-Verkäufer: §a" + data.getPlayerName(i));
                stacklore.add("§7-ID: §a" + i);
                stacklore.add("§7");
                stackmeta.setLore(stacklore);
                stack.setItemMeta(stackmeta);
                invYOffers.setItem(slot, stack);
                slot++;
            }
        }

        p.openInventory(invYOffers);
    }

    public void updateSign(Player p) {
        ItemStack sign = new ItemStack(Material.OAK_SIGN, 1);
        ItemMeta signmeta = sign.getItemMeta();
        signmeta.setDisplayName("§7Verkaufe für: §a" + currentValue);
        sign.setItemMeta(signmeta);

        ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta greenglasmeta = greenglas.getItemMeta();
        greenglasmeta.setDisplayName("§aBestätige Preisänderung");
        ArrayList<String> buylore = new ArrayList<String>();
        buylore.add("§7");
        buylore.add("§7-Preis: §a" + currentValue + " Coins");
        buylore.add("§7-ID: §a" + sindex);
        buylore.add("§7");
        greenglasmeta.setLore(buylore);
        greenglas.setItemMeta(greenglasmeta);

        p.getOpenInventory().setItem(13, sign);
        p.getOpenInventory().setItem(44, greenglas);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
