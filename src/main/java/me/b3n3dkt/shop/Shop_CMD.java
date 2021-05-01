package me.b3n3dkt.shop;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.print.DocFlavor;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;

public class Shop_CMD implements CommandExecutor {
    public static Inventory shopinv;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0) {
                openShopInventory(player);
            }else if(args.length == 2) { //shop <additem|createcategory> <Name> <Price> <Amount> <buyMulti> <sellMulti>
                if (player.hasPermission("jailedmc.command.shop")) {
                    if (args[0].equalsIgnoreCase("createcategory")) {
                        Shop shop = new Shop(args[1]);
                        if (shop.categoryExist() == false) {
                            shop.createCategory();
                            player.sendMessage(Citybuild.getPrefix() + "§7Du hast die Kategorie §8'§3" + args[1] + "§8' §7erstellt!");
                        } else {
                            player.sendMessage(Citybuild.getPrefix() + "§cDie Katogrie existiert bereits!");
                        }
                    } else {
                        player.sendMessage(Citybuild.getPrefix() + "§cNutze /shop <additem|createcategory|reload> <Price|Name> <Amount> <PercentUp> <PercentDown> <SellMulti>");
                    }
                } else {
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /shop!");
                }
            }else if(args.length == 6){
                if (player.hasPermission("jailedmc.command.shop")) {
                if (args[0].equalsIgnoreCase("additem")) {
                    Shop shop = new Shop(args[1]);
                    if (shop.categoryExist() == true) {
                        if(player.getItemInHand() != null && player.getItemInHand() != new ItemStack(Material.AIR)) {
                            shop.addItem(player.getItemInHand(), Double.valueOf(args[2]), Integer.valueOf(args[3]), shop.getItemCount(), Double.valueOf(args[4]), Double.valueOf(args[5]), Double.valueOf(args[6]));
                            player.sendMessage(Citybuild.getPrefix() + "§aDu hast ein Item zur Kategorie §8'§3" + args[1] + "§8' §7hinzugefügt!Preis: §a" + args[2] + "§7, Verfügbar: §a" + args[3] + "§7, ProzentUp: §a" + args[4] + "§7, ProzentDown: §a" + args[5] + "§7,SellMulti: §a" + args[6]);
                        }else{
                            player.sendMessage(Citybuild.getPrefix() + "§cDu musst ein Item in der Hand haben!");
                        }
                    } else {
                        player.sendMessage(Citybuild.getPrefix() + "§cDie Kategorie existiert nicht!");
                    }
                } else {
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /shop <additem|createcategory|reload> <Price|Name> <Amount> <PercentUp> <PercentDown> <SellMulti>");
                }
                } else {
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /shop!");
                }
            }/*else if(args.length == 1){
                if (player.hasPermission("jailedmc.command.shop")) {
                    if (args[0].equalsIgnoreCase("reload")) {

                    } else {
                        player.sendMessage(Citybuild.getPrefix() + "§cNutze /shop <additem|createcategory|reload> <Price|Name> <Amount> <PercentUp> <PercentDown> <SellMulti>");
                    }
                } else {
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /shop!");
                }
            }*/else{
                player.sendMessage(Citybuild.getPrefix() + "§cNutze /shop!");
            }

        }
        return false;
    }

    public static void openShopInventory(Player player){
        shopinv = Bukkit.createInventory(null, 54, "§6§lShop");
        ItemStack glas = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
        ItemMeta glasmeta = glas.getItemMeta();
        glasmeta.setDisplayName("§e§l");
        glas.setItemMeta(glasmeta);

        ItemStack holz = (new ItemBuilder(Material.OAK_LOG).setAmount(1).setDisplayName("§6Holz")).build();
        ItemStack baumaterialien = (new ItemBuilder(Material.BRICK).setAmount(1).setDisplayName("§6Baumaterialien")).build();
        ItemStack besonderes = (new ItemBuilder(Material.SPAWNER).setAmount(1).setDisplayName("§6Besonderes")).build();
        ItemStack werkzeuge = (new ItemBuilder(Material.IRON_PICKAXE).setAmount(1).setDisplayName("§6Werkzeuge")).build();
        ItemStack waffen = (new ItemBuilder(Material.DIAMOND_SWORD).setAmount(1).setDisplayName("§6Waffen")).build();
        ItemStack glass = (new ItemBuilder(Material.BLAZE_ROD).setAmount(1).setDisplayName("§6Mob-Loot")).build();
        ItemStack spawneggs = (new ItemBuilder(Material.CREEPER_SPAWN_EGG, (short) 120).setAmount(1).setDisplayName("§6Spawn Eggs")).build();
        ItemStack redstone = (new ItemBuilder(Material.REDSTONE).setAmount(1).setDisplayName("§6Redstone")).build();
        ItemStack rüstung = (new ItemBuilder(Material.IRON_CHESTPLATE).setAmount(1).setDisplayName("§6Rüstung")).build();
        ItemStack books = (new ItemBuilder(Material.ENCHANTED_BOOK).setAmount(1).setDisplayName("§6Verzauberungen")).build();
        ItemStack deko = (new ItemBuilder(Material.PEONY, (short) 5).setAmount(1).setDisplayName("§6Deko")).build();
        ItemStack essen = (new ItemBuilder(Material.COOKED_BEEF).setAmount(1).setDisplayName("§6Essen")).build();

        player.openInventory(shopinv);

        for(int i = 0;i<54;i++){
            shopinv.setItem(i, glas);
        }

        shopinv.setItem(0, holz);
        shopinv.setItem(8, baumaterialien);
        shopinv.setItem(9, besonderes);
        shopinv.setItem(17, werkzeuge);
        shopinv.setItem(18, waffen);
        shopinv.setItem(26, glass);
        shopinv.setItem(27, spawneggs);
        shopinv.setItem(35, redstone);
        shopinv.setItem(36, rüstung);
        shopinv.setItem(44, books);
        shopinv.setItem(45, deko);
        shopinv.setItem(53, essen);

        player.updateInventory();

    }

    public static void openCategoryInv(Player player, String category){
        Shop shop = new Shop(category);
        ItemStack ggrlass = (new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setAmount(1).setDisplayName("4")).build();
        int index = shop.getItemCount();
        int s = 0;
        for(int i = 0;i<index;i++){
            ItemStack stack = shop.getItemStack(i);
            if(stack != null){
                String tempLore = stack.getItemMeta().getLore().get(0);
                tempLore = tempLore.replace("§7ID: §a", "");
                int id = Integer.parseInt(tempLore);
                ItemMeta stackmeta = stack.getItemMeta();
                stackmeta.setLore(Arrays.asList("§7ID: §a" + shop.getID(id), "§7Preis: §a" + df.format(shop.getPrice(id)), "§7Verfügbar: §a" + shop.getAvailable(id),"§aKaufen§8(Linksklick)"));//|§cVerkaufen§8(Rechtsklick)
                stack.setItemMeta(stackmeta);
                if(i >= 0 && i <= 4){
                    if(s <= 3){
                        shopinv.setItem(11+s, stack);
                        s++;
                    }else if(s == 4){
                        shopinv.setItem(11+s, stack);
                        s = 0;
                    }
                }else if(i >= 4 && i <= 9){
                    if(s <= 3){
                        shopinv.setItem(20+s, stack);
                        s++;
                    }else if(s == 4){
                        shopinv.setItem(20+s, stack);
                        s = 0;
                    }
                }else if(i >= 9 && i <= 14){
                    if(s <= 3){
                        shopinv.setItem(29+s, stack);
                        s++;
                    }else if(s == 4){
                        shopinv.setItem(29+s, stack);
                        s = 0;
                    }
                }else if(i >= 14 && i <= 19){
                    if(s <= 3){
                        shopinv.setItem(38+s, stack);
                        s++;
                    }else if(s == 4){
                        shopinv.setItem(38+s, stack);
                        s = 0;
                    }
                }
            }
        }
        if(category.equalsIgnoreCase("holz")){
            shopinv.setItem(1, ggrlass);
        }else if(category.equalsIgnoreCase("baumaterialien")){
            shopinv.setItem(7, ggrlass);
        }else if(category.equalsIgnoreCase("besonderes")){
            shopinv.setItem(10, ggrlass);
        }else if(category.equalsIgnoreCase("werkzeuge")){
            shopinv.setItem(16, ggrlass);
        }else if(category.equalsIgnoreCase("waffen")){
            shopinv.setItem(19, ggrlass);
        }else if(category.equalsIgnoreCase("mobloot")){
            shopinv.setItem(25, ggrlass);
        }else if(category.equalsIgnoreCase("spawneggs")){
            shopinv.setItem(28, ggrlass);
        }else if(category.equalsIgnoreCase("redstone")){
            shopinv.setItem(34, ggrlass);
        }else if(category.equalsIgnoreCase("rüstung")){
            shopinv.setItem(37, ggrlass);
        }else if(category.equalsIgnoreCase("verzauberungen")){
            shopinv.setItem(43, ggrlass);
        }else if(category.equalsIgnoreCase("deko")){
            shopinv.setItem(46, ggrlass);
        }else if(category.equalsIgnoreCase("essen")){
            shopinv.setItem(52, ggrlass);
        }
        player.updateInventory();
    }

}
