package me.b3n3dkt.auktionshaus;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.Items;
import me.b3n3dkt.utils.PlayerData;
import me.b3n3dkt.utils.Score;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
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
import java.util.UUID;

public class InventoryClick_Auktionshaus implements Listener {

    public static double currentValue = 0;
    public static double sellValue = 0;
    public static boolean canCloseInv = true;
    public static boolean isInSellMenu = false;
    public static boolean isInChooseMen = false;

    @EventHandler
    public void onHandle(InventoryClickEvent event) {
        if (event.getInventory() == null) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack glas = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
        ItemMeta glasmeta = glas.getItemMeta();
        glasmeta.setDisplayName("§6");
        glas.setItemMeta(glasmeta);

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
        signmeta.setDisplayName("§7Verkaufe für: §a" + currentValue); //§7Verkaufe für: §a
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§7");
        lore.add("§7§o*Klicke zum verkaufen!*"); //§7§o*Klicke zum verkaufen!*
        lore.add("§7");
        signmeta.setLore(lore);
        sign.setItemMeta(signmeta);

        if (event.getView().getTitle().equals("§6Auktionshaus")) { //Auktionhaus
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eDeine Items")) {
                    InventoryClick_YourItems.openYourItems(player);
                }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aVerlauf")) {
                    player.sendMessage(Citybuild.getPrefix() + "§cSoon!");
                }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bItems verkaufen")) { //Item Verkaufen
                    Inventory invAuc = Bukkit.createInventory(null, 3 * 9, "§6Sofort-/Auktionsverkauf"); //§6Sofort-/Auktionsverkauf
                    for (int i = 0; i < 27; i++) {
                        invAuc.setItem(i, glas);
                    }
                    ItemStack torch = new ItemStack(Material.TORCH, 1);
                    ItemMeta tlore = torch.getItemMeta();
                    tlore.setDisplayName("§eSofort verkauf"); //§eSofort verkauf
                    torch.setItemMeta(tlore);

                    ItemStack rtorch = new ItemStack(Material.REDSTONE_TORCH, 1);
                    ItemMeta rtlore = rtorch.getItemMeta();
                    rtlore.setDisplayName("§cAuktions verkauf"); //§cAuktions verkauf
                    rtlore.setLore(Arrays.asList("§cSoon!"));
                    rtorch.setItemMeta(rtlore);

                    invAuc.setItem(11, torch);
                    invAuc.setItem(15, rtorch);

                    player.closeInventory();
                    player.openInventory(invAuc);
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aNächste Seite")) { //Nächste Seite
                    Inventory inv = Bukkit.createInventory(null, 9 * 6, "§6Auktionshaus");
                    Items item = new Items();
                    String tempLore = event.getCurrentItem().getItemMeta().getLore().get(1);
                    String temp = tempLore.replace("§7Momentane Seite: ", "");
                    Integer currentPage = Integer.parseInt(temp);
                    Integer currentPageNew = currentPage + 1;

                    if (currentPage < item.getPages()) {
                        for (int i = 0; i < 45; i++) {
                            inv.setItem(i, new ItemStack(Material.AIR));
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

                        ItemStack redglas = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                        ItemMeta redglasmeta = redglas.getItemMeta();
                        redglasmeta.setDisplayName("§cLetzte Seite");
                        ArrayList<String> redlore = new ArrayList<String>();
                        redlore.add("§7");
                        redlore.add("§7Momentane Seite: " + currentPageNew);
                        redlore.add("§7");
                        redglasmeta.setLore(redlore);
                        redglas.setItemMeta(redglasmeta);

                        inv.setItem(45, redglas);
                        inv.setItem(53, new ItemStack(Material.AIR));
                        inv.setItem(53, greenglas);
                        Integer index = item.getIndex();
                        int slot = 0;
                        Integer tempSlots = currentPage * 46;
                        for (int i = tempSlots; i < index; i++) {
                            if (item.getItem(i) != null) {
                                if (slot > 44) {
                                } else {
                                    ItemStack stack = item.getItem(i);
                                    ItemMeta stackmeta = stack.getItemMeta();
                                    ArrayList<String> stacklore = new ArrayList<String>();
                                    stacklore.add("§7");
                                    stacklore.add("§7-Preis: §a" + item.getPrice(i) + " Coins");
                                    stacklore.add("§7-Verkäufer: §a" + item.getPlayerName(i));
                                    stacklore.add("§7-ID: §a" + i);
                                    stacklore.add("§7");
                                    stackmeta.setLore(stacklore);
                                    stack.setItemMeta(stackmeta);

                                    inv.setItem(slot, stack);
                                    slot++;
                                }
                            }
                        }
                    }

                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cLetzte Seite")) { //Letzte Seite
                    Items item = new Items();
                    String tempLore = event.getCurrentItem().getItemMeta().getLore().get(1);
                    String temp = tempLore.replace("§7Momentane Seite: ", "");
                    Integer currentPage = Integer.parseInt(temp);
                    Integer currentPageNew = currentPage - 1;
                    Inventory inv = Bukkit.createInventory(null, 9 * 6, "§6Auktionshaus");
                    for (int i = 0; i < 45; i++) {
                        inv.setItem(i, new ItemStack(Material.AIR));
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

                    ItemStack redglas = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                    ItemMeta redglasmeta = redglas.getItemMeta();
                    redglasmeta.setDisplayName("§cLetzte Seite");
                    ArrayList<String> redlore = new ArrayList<String>();
                    redlore.add("§7");
                    redlore.add("§7Momentane Seite: " + currentPageNew);
                    redlore.add("§7");
                    redglasmeta.setLore(redlore);
                    redglas.setItemMeta(redglasmeta);

                    ItemStack barrier = new ItemStack(Material.BARRIER);
                    ItemMeta barriermeta = barrier.getItemMeta();
                    barriermeta.setDisplayName("§6");
                    barrier.setItemMeta(barriermeta);

                    Integer index = item.getIndex();
                    int slot = 0;
                    Integer tempSlots = (currentPageNew - 1) * 46;

                    if (currentPageNew == 1) {
                        inv.setItem(45, barrier);
                        tempSlots = 0;
                    } else {
                        inv.setItem(45, redglas);
                    }
                    inv.setItem(53, new ItemStack(Material.AIR));
                    inv.setItem(53, greenglas);
                    for (int i = tempSlots; i < index; i++) {
                        if (item.getItem(i) != null) {
                            if (slot > 44) {
                            } else {
                                ItemStack stack = item.getItem(i);
                                ItemMeta stackmeta = stack.getItemMeta();
                                ArrayList<String> stacklore = new ArrayList<String>();
                                stacklore.add("§7");
                                stacklore.add("§7-Preis: §a" + item.getPrice(i) + " Coins");
                                stacklore.add("§7-Verkäufer: §a" + item.getPlayerName(i));
                                stacklore.add("§7-ID: §a" + i);
                                stacklore.add("§7");
                                stackmeta.setLore(stacklore);
                                stack.setItemMeta(stackmeta);

                                inv.setItem(slot, stack);
                                slot++;
                            }
                        }
                    }
                }
            }else if (event.getCurrentItem().getItemMeta().hasLore() == true) { //Item Kaufen
                if (event.getCurrentItem().getItemMeta().getLore().get(2).contains("§7-ID:")) {
                    Items item = new Items();
                    String tempLore = event.getCurrentItem().getItemMeta().getLore().get(2);
                    String temp = tempLore.replace("§7-ID: §a", "");
                    Integer index = Integer.parseInt(temp);
                    ItemStack stack = new ItemStack(item.getItem(index));

                    if (item.getInstantSell(index) == true) {
                        Inventory inv = Bukkit.createInventory(null, 3 * 9, "§6Bist du sicher?"); //§6Bist du sicher?
                        ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                        ItemMeta greenglasmeta = greenglas.getItemMeta();
                        greenglasmeta.setDisplayName("§aItem kaufen"); //§aItem kaufen
                        greenglas.setItemMeta(greenglasmeta);

                        ItemStack redglas = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                        ItemMeta redglasmeta = redglas.getItemMeta();
                        redglasmeta.setDisplayName("§cAbbruch"); //§cAbbruch
                        redglas.setItemMeta(redglasmeta);

                        ItemStack buysign = new ItemStack(Material.OAK_SIGN, 1);
                        ItemMeta buysignmeta = buysign.getItemMeta();
                        buysignmeta.setDisplayName("§7Verkaufsangebot");//§7Verkaufsangebot
                        ArrayList<String> buylore = new ArrayList<String>();
                        buylore.add("§7");
                        buylore.add("§7-Preis: §a" + item.getPrice(index) + " Coins");
                        buylore.add("§7-Verkäufer: §a" + item.getPlayerName(index));
                        buylore.add("§7-ID: " + index);
                        buylore.add("§7");
                        buysignmeta.setLore(buylore);
                        buysign.setItemMeta(buysignmeta);

                        for (int i = 0; i < 3 * 9; i++) {
                            inv.setItem(i, glas);
                        }

                        inv.setItem(4, buysign);
                        inv.setItem(11, redglas);
                        inv.setItem(13, stack);
                        inv.setItem(15, greenglas);
                        canCloseInv = false;
                        player.openInventory(inv);

                    } else if (item.getInstantSell(index) == false) {
                        Inventory inv = Bukkit.createInventory(null, 5 * 9, "§6Dein Angebot:"); //§6Dein Angebot:

                        ItemStack isign = new ItemStack(Material.OAK_SIGN, 1);
                        ItemMeta isignmeta = isign.getItemMeta();
                        isignmeta.setDisplayName("§7Du bietest: §a" + currentValue); //§7Du bietest: §a
                        ArrayList<String> ilore = new ArrayList<String>();
                        ilore.add("§7");
                        ilore.add("§7§o*Klicke um Angebot zu machen!*"); //§7§o*Klicke um Angebot zu machen!*
                        ilore.add("§7");
                        isignmeta.setLore(ilore);
                        isign.setItemMeta(isignmeta);

                        ItemStack offerItem = event.getCurrentItem();
                        ItemMeta offerItemmeta = offerItem.getItemMeta();
                        ArrayList<String> offerlore = new ArrayList<String>();
                        offerlore.add("§7");
                        offerlore.add("§7-Minimum: §a" + item.getPrice(index) + " Coins");
                        offerlore.add("§7-Verkäufer: §a" + item.getPlayerName(index));
                        offerlore.add("§7-ID: " + index);
                        offerlore.add("§7");
                        offerItemmeta.setLore(offerlore);
                        offerItem.setItemMeta(offerItemmeta);

                        for (int i = 0; i < 45; i++) {
                            inv.setItem(i, glas);
                        }
                        inv.setItem(31, offerItem);
                        inv.setItem(34, p1c);
                        inv.setItem(25, p1d);
                        inv.setItem(16, p10d);
                        inv.setItem(7, p100d);
                        inv.setItem(1, m100d);
                        inv.setItem(10, m10d);
                        inv.setItem(19, m1d);
                        inv.setItem(28, m1c);
                        inv.setItem(13, isign);

                        canCloseInv = false;
                        player.closeInventory();
                        player.openInventory(inv);
                    }
                }
            }
        } else if (event.getView().getTitle().equals("§6Bist du sicher?")) { //Are you sure
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aItem kaufen")) {
                try{
                    Items item3 = new Items();
                    String tempLore3 = event.getInventory().getItem(4).getItemMeta().getLore().get(3);
                    String temp3 = tempLore3.replace("§7-ID: ", "");
                    Integer index = Integer.parseInt(temp3);
                    Items item = new Items();

                    Double price = item.getPrice(index);
                    Player seller = Bukkit.getPlayer(item.getUUID(index));
                    UUID uuid = UUID.fromString(item.getUUID(index));
                    String playername = item.getPlayerName(index);
                    System.out.println(playername);
                    System.out.println(uuid.toString());
                    PlayerData data = new PlayerData(Bukkit.getPlayer(playername), uuid);
                    ItemStack stack = new ItemStack(item3.getItem(index));
                    if (MySQL.getcoins(player.getUniqueId().toString()) >= price) {
                        if (player.getInventory().firstEmpty() != -1) {
                            if(player == seller){
                                if(!player.hasPermission("jailedmc.command.auktionshaus")){
                                    player.sendMessage(Citybuild.getPrefix() + "§cDu kannst deine eigenen Items nicht kaufen!");
                                    return;
                                }
                            }
                            player.closeInventory();
                            player.getInventory().addItem(stack);
                            player.sendMessage(Citybuild.getPrefix() + "§aDu hast ein Item für " + item.getPrice(index) + " Coins gekauft!");
                            MySQL.setcoins(player.getUniqueId().toString(), MySQL.getcoins(player.getUniqueId().toString()) - price);
                            MySQL.setcoins(item.getUUID(index), MySQL.getcoins(item.getUUID(index))+price);
                            if(Bukkit.getServer().getPlayer(item.getUUID(index)) != null){
                                Score sb = new Score(seller);
                                sb.update();
                                seller.sendMessage(Citybuild.getPrefix() + "§aJemand hat ein Item von dir gekauft!");
                            }
                            canCloseInv = true;
                            Integer sindex = item.getID(index);
                            item.deleteItem(index);
                            item.loadDefaults();
                            for (Player all : CMD_Auktionshaus.inAh) {
                                CMD_Auktionshaus.openAuktionshaus(all);
                            }
                        } else {
                            player.sendMessage(Citybuild.getPrefix() + "§cDein Inventar ist voll!");
                        }
                    } else {
                        player.sendMessage(Citybuild.getPrefix() + "§cDu hast nicht genug Geld!");
                    }
                }catch (Exception e1){
                    e1.printStackTrace();
                    //System.out.println("INventoryCLick_Autkionshaus hat fehler");
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAbbruch")) { //Item NICHT kaufen
                player.closeInventory();
                canCloseInv = true;
                CMD_Auktionshaus.openAuktionshaus(player);
            }
        } else if (event.getView().getTitle().equalsIgnoreCase("§bWähle ein Item!") ||
                event.getView().getTitle().equalsIgnoreCase("§6Dein Angebot:")) { //Choose Item or Your offer
            if (!event.getCurrentItem().hasItemMeta()) {
                return;
            }
            if (event.getInventory() != player.getInventory()) {
                if (event.getSlot() != 31) {
                    event.setCancelled(true);
                }
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 0.01 Coins")) {
                currentValue = currentValue + 0.01;
                currentValue = round(currentValue, 2);

                updateSign(player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 1 Coins")) {
                currentValue = currentValue + 1;
                currentValue = round(currentValue, 2);

                updateSign(player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 10 Coins")) {
                currentValue = currentValue + 10;
                currentValue = round(currentValue, 2);

                updateSign(player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlus 100 Coins")) {
                currentValue = currentValue + 100;
                currentValue = round(currentValue, 2);

                updateSign(player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 0.01 Coins")) {
                if (currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 0.01;
                currentValue = round(currentValue, 2);

                updateSign(player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 1 Coins")) {
                if (currentValue >= 0 && currentValue <= 1) {
                    if (currentValue == 0) {
                        player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                        return;
                    }
                    currentValue = 0;

                    updateSign(player);
                    return;
                } else if (currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 1;
                currentValue = round(currentValue, 2);

                updateSign(player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 10 Coins")) {
                if (currentValue >= 0 && currentValue <= 10) {
                    if (currentValue == 0) {
                        player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                        return;
                    }
                    currentValue = 0;

                    updateSign(player);
                    return;
                } else if (currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 10;
                currentValue = round(currentValue, 2);

                updateSign(player);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMinus 100 Coins")) {
                if (currentValue >= 0 && currentValue <= 100) {
                    if (currentValue == 0) {
                        player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                        return;
                    }
                    currentValue = 0;

                    updateSign(player);
                    return;
                } else if (currentValue == 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cDer Wert kann nicht unter 0 liegen!");
                    return;
                }
                currentValue = currentValue - 100;
                currentValue = round(currentValue, 2);

                updateSign(player);
            }
            if (!event.getCurrentItem().getItemMeta().hasLore()) {
                return;
            }
            if (event.getCurrentItem().getItemMeta().getLore().contains("§7§o*Klicke zum sofort verkauf!*")) { //Instant Verkaufen §7§o*Klicke zum sofort verkauf!*
                ItemStack temp = event.getInventory().getItem(31);
                if (temp == null || temp == new ItemStack(Material.AIR) || temp.getType() == Material.AIR) { player.sendMessage(Citybuild.getPrefix() + "§cDu musst ein Item auswählen!");return;}
                    isInSellMenu = true;
                    canCloseInv = false;
                    ItemStack stack = event.getInventory().getItem(31);
                    String dn = stack.getType().toString();

                    sellValue = currentValue;
                    currentValue = 0;

                    Inventory sellmenu = Bukkit.createInventory(null, 3 * 9, "§eSofort Verkaufsangebot"); //§eSofort Verkaufsangebot

                    ItemStack greenglas = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
                    ItemMeta greenglasmeta = greenglas.getItemMeta();
                    greenglasmeta.setDisplayName("§eSofort verkauf"); //§eSofort verkauf
                    greenglas.setItemMeta(greenglasmeta);

                    ItemStack redglas = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
                    ItemMeta redglasmeta = redglas.getItemMeta();
                    redglasmeta.setDisplayName("§cAbbruch");
                    redglas.setItemMeta(redglasmeta);

                    ItemStack sellsign = new ItemStack(Material.OAK_SIGN, 1);
                    ItemMeta sellsignmeta = sellsign.getItemMeta();
                    sellsignmeta.setDisplayName("§7Sofort Verkaufsangebot"); //§7Sofort Verkaufsangebot
                    ArrayList<String> selllore = new ArrayList<String>();
                    selllore.add("§7");
                    selllore.add("§7-§a" + dn);
                    selllore.add("§7-§a" + sellValue + "§a Coins");
                    selllore.add("§7-" + "");
                    selllore.add("§7");
                    sellsignmeta.setLore(selllore);
                    sellsign.setItemMeta(sellsignmeta);

                    for (int i = 0; i < 27; i++) {
                        sellmenu.setItem(i, glas);
                    }

                    sellmenu.setItem(13, stack);
                    sellmenu.setItem(4, sellsign);
                    sellmenu.setItem(11, redglas);
                    sellmenu.setItem(15, greenglas);

                    isInChooseMen = false;
                    player.closeInventory();
                    player.openInventory(sellmenu);
            }
        } else if (event.getView().getTitle().equals("§eSofort Verkaufsangebot")) { //Instant selloffer
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eSofort verkauf")) { //Instant verkaufen
                isInSellMenu = false;
                player.closeInventory();
                Items item = new Items();
                PlayerData data = new PlayerData(player, player.getUniqueId());
                ItemStack stack = event.getInventory().getItem(13);
                String uuid = player.getUniqueId().toString();
                String dn = stack.getType().toString();
                String ench = stack.getEnchantments().toString();
                String stacklore =  null;
                if(stack.hasItemMeta()){
                    if(stack.getItemMeta().hasLore()){
                        stacklore = stack.getItemMeta().getLore().toString();
                    }
                }

                if(stack.getItemMeta().hasDisplayName() != false) {
                    dn = stack.getItemMeta().getDisplayName();
                }else if(stack.getItemMeta().hasDisplayName() == true) {
                    dn = stack.getType().toString();
                }
                item.addItem(player, stack, uuid, sellValue, dn, ench, stacklore, data.getIndex(),true);
                player.sendMessage(Citybuild.getPrefix() + "§aDu hast ein sofort Verkaufsangebot erstellt!");
                canCloseInv = true;
                for(Player all : CMD_Auktionshaus.inAh) {
                    CMD_Auktionshaus.openAuktionshaus(all);
                }
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAbbruch")) { //NICHT Instant vekaufen
                isInSellMenu = false;
                ItemStack stack = event.getInventory().getItem(13);
                player.getInventory().addItem(stack);
                player.closeInventory();
                canCloseInv = true;
            }
        } else if (event.getView().getTitle().equals("§6Sofort-/Auktionsverkauf")) { //Instant sell or Auction sell
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eSofort verkauf")) { //Instant verkaufen
                ItemStack isign = new ItemStack(Material.OAK_SIGN, 1);
                ItemMeta isignmeta = isign.getItemMeta();
                isignmeta.setDisplayName("§7Verkaufe für: §a" + currentValue);
                ArrayList<String> ilore = new ArrayList<String>();
                ilore.add("§7");
                ilore.add("§7§o*Klicke zum sofort verkauf!*");
                ilore.add("§7");
                isignmeta.setLore(ilore);
                isign.setItemMeta(isignmeta);

                Inventory invChoose = Bukkit.createInventory(null, 5*9, "§bWähle ein Item!");//§bWähle ein Item!
                for(int i = 0; i < 45; i++) {
                    invChoose.setItem(i, glas);
                }
                invChoose.setItem(31, new ItemStack(Material.AIR));
                invChoose.setItem(34, p1c);
                invChoose.setItem(25, p1d);
                invChoose.setItem(16, p10d);
                invChoose.setItem(7, p100d);
                invChoose.setItem(1, m100d);
                invChoose.setItem(10, m10d);
                invChoose.setItem(19, m1d);
                invChoose.setItem(28, m1c);
                invChoose.setItem(13, isign);

                player.closeInventory();
                player.openInventory(invChoose);
                isInChooseMen = true;
            }
        }
    }
    public void updateSign (Player p){
        ItemStack sign = new ItemStack(Material.OAK_SIGN, 1);
        ItemMeta signmeta = sign.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§7");
        if (p.getOpenInventory().getItem(13).getItemMeta().getLore().contains("§7§o*Klicke zum sofort verkauf!*")) {
            signmeta.setDisplayName("§7Verkaufe für: §a" + currentValue);
            lore.add("§7§o*Klicke zum sofort verkauf!*");
        } else if (p.getOpenInventory().getItem(13).getItemMeta().getLore().contains("§7§o*Klicke zum Auktionsverkauf!")) {
            signmeta.setDisplayName("§7§nMinimum: §r§a" + currentValue);
            lore.add("§7§o*Klicke zum Auktionsverkauf!*"); //§7§o*Klicke zum Auktionsverkauf!
        } else if (p.getOpenInventory().getTitle().equalsIgnoreCase("§6Dein Angebot:")) {
            signmeta.setDisplayName("§7Du bietest: §a" + currentValue + " Coins");
            lore.add("§7§o*Klicke um Angebot zu machen!*");
        }
        lore.add("§7");
        signmeta.setLore(lore);
        sign.setItemMeta(signmeta);

        p.getOpenInventory().setItem(13, sign);
    }

    public double round ( double value, int places){
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
