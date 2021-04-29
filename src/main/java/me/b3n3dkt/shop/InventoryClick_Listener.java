package me.b3n3dkt.shop;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.ItemBuilder;
import me.b3n3dkt.utils.Score;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class InventoryClick_Listener implements Listener {

    private static DecimalFormat df = new DecimalFormat("0.00");

    @EventHandler
    public void onHandle(InventoryClickEvent event){
        try{
        if(event.getInventory() == null){return;}
        if(event.getCurrentItem() == null){return;}
        if(event.getInventory() == event.getWhoClicked().getInventory()){return;}
        Player player = (Player)event.getWhoClicked();
        if(event.getView().getTitle().equalsIgnoreCase("§6§lShop")){
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().hasLore() == true){
                if(event.getCurrentItem().getItemMeta().getLore().get(0).contains("§7ID: §a")){
                    ItemStack ggrlass = (new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setAmount(1).setDisplayName("4")).build();
                    if(event.getInventory().getItem(1).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "holz");
                        }else if(event.getClick().equals(ClickType.LEFT))
                            openBuyInventory(event.getCurrentItem(), player, "holz");
                    }else if(event.getInventory().getItem(7).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "baumaterialien");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "baumaterialien");
                    }else if(event.getInventory().getItem(10).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "besonderes");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "besonderes");
                    }else if(event.getInventory().getItem(16).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)) {
                            openSellInventory(event.getCurrentItem(), player, "werkzeuge");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "werkzeuge");
                    }else if(event.getInventory().getItem(19).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "waffen");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "waffen");
                    }else if(event.getInventory().getItem(25).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "mobloot");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "mobloot");
                    }else if(event.getInventory().getItem(28).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "spawneggs");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "spawneggs");
                    }else if(event.getInventory().getItem(34).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "redstone");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "redstone");
                    }else if(event.getInventory().getItem(37).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "rüstung");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "rüstung");
                    }else if(event.getInventory().getItem(43).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "verzauberungen");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "verzauberungen");
                    }else if(event.getInventory().getItem(46).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "deko");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "deko");
                    }else if(event.getInventory().getItem(52).equals(ggrlass)){
                        if(event.getClick().equals(ClickType.RIGHT)){
                            openSellInventory(event.getCurrentItem(), player, "essen");
                        }else if(event.getClick().equals(ClickType.LEFT))
                        openBuyInventory(event.getCurrentItem(), player, "essen");
                    }
                }
            }
            if(event.getCurrentItem().getItemMeta() == null){return;}
            if(event.getCurrentItem().getItemMeta().getDisplayName() == null){return;}
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Holz")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "holz");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Baumaterialien")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "baumaterialien");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Besonderes")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "besonderes");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Werkzeuge")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "werkzeuge");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Waffen")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "waffen");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Mob-Loot")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "mobloot");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Spawn Eggs")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "spawneggs");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Redstone")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "redstone");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Rüstung")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "rüstung");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Verzauberungen")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "verzauberungen");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Deko")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "deko");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Essen")){
                Shop_CMD.openShopInventory(player);
                Shop_CMD.openCategoryInv(player, "essen");
            }
        }else if(event.getView().getTitle().equalsIgnoreCase("§6Item(s) §3§lkaufen?")){
            event.setCancelled(true);
            Score sb = new Score(player);
            ItemStack stack = event.getInventory().getItem(13);
            String tempLore = stack.getItemMeta().getLore().get(0);
            tempLore = tempLore.replace("§7ID: §a", "");
            int id = Integer.parseInt(tempLore);
            String category = stack.getItemMeta().getLore().get(3);
            category = category.replace("§7Kategorie: §a", "");
            Shop shop = new Shop(category);
            ItemStack item = shop.getItemStackWithoutLore(id);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cKlicke zum Abbruch!")){
                player.closeInventory();
                Shop_CMD.openCategoryInv(player, category);
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aKlicke zum Kaufen!")){
                        if(hasAvaliableSlot(player) == true){
                            if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                                if (shop.getAvailable(id) >= 64) {
                                    double price = (shop.getPrice(id)*64);
                                    if(MySQL.getcoins(player.getUniqueId().toString()) >= (shop.getPrice(id)*64)){
                                        MySQL.setcoins(player.getUniqueId().toString(), MySQL.getcoins(player.getUniqueId().toString())-(shop.getPrice(id)*64));
                                        player.sendMessage(Citybuild.getPrefix() + "§aDu hast dir 64 Items für §8'§3" + df.format(price) + "§8' §aCoins gekauft!");
                                        ItemStack temp = item;
                                        temp = (new ItemBuilder(temp.getType(), (short) temp.getDurability()).setAmount(64)).build();
                                        player.getInventory().addItem(temp);
                                        shop.setAvailable(id, shop.getAvailable(id)-64);
                                        shop.setSold(id, shop.getSold(id)+64);
                                        for(int i =0;i<64;i++){
                                            shop.addItemPrice(id);
                                        }
                                        openBuyInventory(stack, player, category);
                                    }else{
                                        player.sendMessage(Citybuild.getPrefix() + "§cDu hast nicht genug Geld!");
                                    }
                                }else{
                                    player.sendMessage(Citybuild.getPrefix() + "§cEs sind nicht genug Items zur verfügung!");
                                }
                            }else if(event.getClick().equals(ClickType.LEFT)) {
                                if(shop.getAvailable(id) >= 1){
                                    if(MySQL.getcoins(player.getUniqueId().toString()) >= shop.getPrice(id)){
                                        MySQL.setcoins(player.getUniqueId().toString(), MySQL.getcoins(player.getUniqueId().toString())-shop.getPrice(id));
                                        player.sendMessage(Citybuild.getPrefix() + "§aDu hast dir ein Item für §8'§3" + df.format(shop.getPrice(id)) + "§8' §aCoins gekauft!");
                                        stack.setAmount(1);
                                        player.getInventory().addItem(item);
                                        shop.setAvailable(id, shop.getAvailable(id)-1);
                                        shop.setSold(id, shop.getSold(id)+1);
                                        shop.addItemPrice(id);
                                        openBuyInventory(stack, player, category);
                                    }else{
                                        player.sendMessage(Citybuild.getPrefix() + "§cDu hast nicht genug Geld!");
                                    }
                                }else{
                                    player.sendMessage(Citybuild.getPrefix() + "§cEs sind nicht genug Items zur verfügung!");
                                }
                            }
                        }else{
                            player.sendMessage(Citybuild.getPrefix() + "§cDein Inventar zu voll!");
                        }
                    }
            sb.update();
            }else if(event.getView().getTitle().equalsIgnoreCase("§6Item(s) §c§lverkaufen?")){
            event.setCancelled(true);
            Score sb = new Score(player);
            ItemStack stack = event.getInventory().getItem(13);
            String tempLore = stack.getItemMeta().getLore().get(0);
            tempLore = tempLore.replace("§7ID: §a", "");
            int id = Integer.parseInt(tempLore);
            String category = stack.getItemMeta().getLore().get(3);
            category = category.replace("§7Kategorie: §a", "");
            Shop shop = new Shop(category);
            ItemStack item = shop.getItemStackWithoutLore(id);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aKlicke zum Verkaufen!")){
                if(event.getClick().equals(ClickType.LEFT)){
                    int amount = 0;
                    for(ItemStack itemStack : player.getInventory().getContents()){
                        if(itemStack != null) {
                            if (itemStack.getData().equals(stack.getData())) {
                                ItemStack temp = itemStack;
                                    amount = amount + itemStack.getAmount();
                            }
                        }
                    }
                    if(amount == 0){player.sendMessage(Citybuild.getPrefix() + "§cDu hast keine Items von dem Item Typ im Inventar!");return;}
                    double sellMulti = shop.getSellMulti(id);
                    double price = shop.getPrice(id)*sellMulti;
                    player.sendMessage(Citybuild.getPrefix() + "§aDu hast §8'§31§8' §aItem für §8'§3" + df.format(price) + "§8' §averkauft!");
                    MySQL.setcoins(player.getUniqueId().toString(), MySQL.getcoins(player.getUniqueId().toString())+price);
                    shop.removeItemPrice(id);
                    shop.setAvailable(id, shop.getAvailable(id)+1);
                    for(ItemStack itemStack : player.getInventory().getContents()){
                        if(itemStack != null) {
                            if (itemStack.getData().equals(stack.getData())) {
                                if (itemStack.getAmount() != 1) {
                                    itemStack.setAmount(itemStack.getAmount() - 1);
                                    break;
                                } else {
                                    player.getInventory().remove(itemStack);
                                }
                            }
                        }
                    }
                    double estimatedPrice = shop.getPrice(id);
                    double temp = shop.getPrice(id);
                    for(int i = 0;i<64;i++){
                        temp = temp*sellMulti;
                        estimatedPrice = estimatedPrice+temp;
                    }
                    ItemMeta stackmeta = stack.getItemMeta();
                    stackmeta.setLore(Arrays.asList("§7ID: §a" + id, "§7Preis: §a" + df.format(shop.getPrice(id)), "§7Verfügbar: §a" + shop.getAvailable(id), "§7Kategorie: §a" + category));
                    ItemStack sign = (new ItemBuilder(Material.OAK_SIGN).setAmount(1).setDisplayName("§6Bist du die sicher?").setLore(Arrays.asList("§71 Stück: §a" + df.format((price/100)*80),"§764 Stück: §a" + df.format(estimatedPrice)))).build();
                    stack.setItemMeta(stackmeta);
                    player.getOpenInventory().setItem(13, stack);
                    player.getOpenInventory().setItem(4, sign);

                }else if(event.getClick().equals(ClickType.SHIFT_LEFT)){
                    int amount = 0;
                    for(ItemStack itemStack : player.getInventory().getContents()){
                        if(itemStack != null) {
                            if (itemStack.getData().equals(stack.getData())) {
                                amount = amount + itemStack.getAmount();
                            }
                        }
                    }
                    if(amount == 0){player.sendMessage(Citybuild.getPrefix() + "§cDu hast keine Items von dem Item Typ im Inventar!");return;}
                    double sellMulti = shop.getSellMulti(id); //0.95
                    double price = shop.getPrice(id); //10
                    double estimatedPrice = price; //10
                    for(int i = 0;i<amount;i++){
                        estimatedPrice = estimatedPrice+(price*sellMulti); //10+9.5=19.5
                    }
                    player.sendMessage(Citybuild.getPrefix() + "§aDu hast §8'§3" + amount + "§8' §aItem für §8'§3" + df.format(estimatedPrice) + "§8' §averkauft!");
                    shop.setAvailable(id, shop.getAvailable(id)+amount);
                    MySQL.setcoins(player.getUniqueId().toString(), MySQL.getcoins(player.getUniqueId().toString())+estimatedPrice);
                    for(int i = 0;i<amount;i++){
                        shop.removeItemPrice(id);
                    }

                    for(ItemStack itemStack : player.getInventory().getContents()){
                        if(itemStack != null) {
                            if (itemStack.getData().equals(stack.getData())) {
                                    player.getInventory().remove(itemStack);
                            }
                        }
                    }
                    price = shop.getPrice(id);
                    for(int i = 0;i<64;i++){
                        estimatedPrice = estimatedPrice+(price*sellMulti);
                    }
                    ItemMeta stackmeta = stack.getItemMeta();
                    stackmeta.setLore(Arrays.asList("§7ID: §a" + id, "§7Preis: §a" + df.format(shop.getPrice(id)), "§7Verfügbar: §a" + shop.getAvailable(id), "§7Kategorie: §a" + category));
                    ItemStack sign = (new ItemBuilder(Material.OAK_SIGN).setAmount(1).setDisplayName("§6Bist du die sicher?").setLore(Arrays.asList("§71 Stück: §a" + df.format((price/100)*80),"§764 Stück: §a" + df.format(estimatedPrice)))).build();
                    stack.setItemMeta(stackmeta);
                    player.getOpenInventory().setItem(13, stack);
                    player.getOpenInventory().setItem(4, sign);

                }
                sb.update();
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cKlicke zum Abbruch!")){
                player.closeInventory();
              }
            }
        }catch (Exception e1){
            System.out.println("[ERROR] InventoryClick_Listener hat einen Fehler!");
        }
    }

    public static void openSellInventory(ItemStack stack, Player player, String category){
        Shop shop = new Shop(category);
        String tempLore = stack.getItemMeta().getLore().get(0);
        tempLore = tempLore.replace("§7ID: §a", "");
        int id = Integer.parseInt(tempLore);
        double sellMulti = shop.getSellMulti(id);
        double price = shop.getPrice(id);
        double estimatedPrice = price;

        for(int i = 0;i<64;i++){
            estimatedPrice = estimatedPrice+(price*sellMulti);
        }

        ItemStack glas = (new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§a")).build();
        Inventory buy = Bukkit.createInventory(null, 3*9, "§6Item(s) §c§lverkaufen?");
        ItemStack green = (new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§aKlicke zum Verkaufen!").setLore(Arrays.asList("§7Mit Shift kannst du alle Items in deinem Inventar gleichzeitig Verkaufen!"))).build();
        ItemStack red = (new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§cKlicke zum Abbruch!")).build();
        ItemStack sign = (new ItemBuilder(Material.OAK_SIGN).setAmount(1).setDisplayName("§6Bist du die sicher?").setLore(Arrays.asList("§71 Stück: §a" + df.format((price*sellMulti)),"§764 Stück: §a" + df.format(estimatedPrice)))).build();

        player.openInventory(buy);
        for(int i = 0;i<27;i++){
            buy.setItem(i, glas);
        }
        ItemMeta stackmeta = stack.getItemMeta();
        stackmeta.setLore(Arrays.asList("§7ID: §a" + id, "§7Preis: §a" + df.format(shop.getPrice(id)), "§7Verfügbar: §a" + shop.getAvailable(id), "§7Kategorie: §a" + category));
        stack.setItemMeta(stackmeta);
        buy.setItem(13, stack);
        buy.setItem(4, sign);
        buy.setItem(10, green);
        buy.setItem(16, red);
        player.updateInventory();

    }

    public static void openBuyInventory(ItemStack stack, Player player, String category){
        Shop shop = new Shop(category);
        String tempLore = stack.getItemMeta().getLore().get(0);
        tempLore = tempLore.replace("§7ID: §a", "");
        int id = Integer.parseInt(tempLore);
        double price = shop.getPrice(id);
        double estimatedPrice = price;

        for(int i = 0;i<64;i++){
            estimatedPrice = estimatedPrice+(price*shop.getPercentUp(id));
        }

        ItemStack glas = (new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§a")).build();
        Inventory buy = Bukkit.createInventory(null, 3*9, "§6Item(s) §3§lkaufen?");
        ItemStack green = (new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§aKlicke zum Kaufen!").setLore(Arrays.asList("§7Mit Shift kannst du 64 Items gleichzeitig kaufen!"))).build();
        ItemStack red = (new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§cKlicke zum Abbruch!")).build();
        ItemStack sign = (new ItemBuilder(Material.OAK_SIGN).setAmount(1).setDisplayName("§6Bist du die sicher?").setLore(Arrays.asList("§71 Stück: §a" + df.format(price),"§764 Stück: §a" + df.format(estimatedPrice)))).build();

        player.openInventory(buy);
        for(int i = 0;i<27;i++){
            buy.setItem(i, glas);
        }
        ItemMeta stackmeta = stack.getItemMeta();
        stackmeta.setLore(Arrays.asList("§7ID: §a" + id, "§7Preis: §a" + df.format(shop.getPrice(id)), "§7Verfügbar: §a" + shop.getAvailable(id), "§7Kategorie: §a" + category));
        stack.setItemMeta(stackmeta);
        buy.setItem(13, stack);
        buy.setItem(4, sign);
        buy.setItem(10, green);
        buy.setItem(16, red);
        player.updateInventory();

    }

    //, "", "§7Kaufen(Linksklick)|Verkaufen(Rechtsklick)", "§7Um einen Stack Kaufen/alles Verkaufen, Shift mit drücken!"

    public boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }

}
