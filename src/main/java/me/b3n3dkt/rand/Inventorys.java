package me.b3n3dkt.rand;

import me.b3n3dkt.utils.EnderChest;
import me.b3n3dkt.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Inventorys {

    public static void openRandMenü(Player player){
        Inventory inv = Bukkit.createInventory(null, 3*9, "§6Randmenü");

        ItemStack glas = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
        ItemMeta glasmeta = glas.getItemMeta();
        glasmeta.setDisplayName("§e§l");
        glas.setItemMeta(glasmeta);

        ItemStack stufen = (new ItemBuilder(Material.STONE_SLAB).setAmount(1).setDisplayName("§aStufen")).build();
        ItemStack besonderes = (new ItemBuilder(Material.BEACON).setAmount(1).setDisplayName("§cBesondere")).build();
        ItemStack teppiche = (new ItemBuilder(Material.GREEN_CARPET, (short) 5).setAmount(1).setDisplayName("§aTeppiche")).build();
        ItemStack barrier = (new ItemBuilder(Material.BARRIER).setAmount(1).setDisplayName("§cKeinen Rand")).build();

        player.openInventory(inv);

        for(int i = 0;i<3*9;i++){
            inv.setItem(i, glas);
        }
        inv.setItem(11, stufen);
        inv.setItem(13, besonderes);
        inv.setItem(15, teppiche);
        inv.setItem(22, barrier);

        player.updateInventory();
    }

    public static void openStufenMenü(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, "§aStufen");

        ItemStack glas = (new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§7")).build();
        ItemStack zurück = (new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§cZurück")).build();
        ItemStack birch = (new ItemBuilder(Material.BIRCH_SLAB, (short) 2).setAmount(1).setDisplayName("§aBirkenholzstufe")).build();
        ItemStack acacia = (new ItemBuilder(Material.ACACIA_SLAB, (short) 4).setAmount(1).setDisplayName("§aAkazienholzstufe")).build();
        ItemStack jungle = (new ItemBuilder(Material.JUNGLE_SLAB, (short) 3).setAmount(1).setDisplayName("§aTropenholzstufe")).build();
        ItemStack darkoak = (new ItemBuilder(Material.DARK_OAK_SLAB, (short) 5).setAmount(1).setDisplayName("§aSchwarzeichenholzstufe")).build();
        ItemStack oak = (new ItemBuilder(Material.OAK_SLAB, (short) 0).setAmount(1).setDisplayName("§aEichenholzstufe")).build();
        ItemStack spruce = (new ItemBuilder(Material.SPRUCE_SLAB, (short) 1).setAmount(1).setDisplayName("§aFichtenholzstufe")).build();

        ItemStack stone = (new ItemBuilder(Material.STONE_SLAB).setAmount(1).setDisplayName("§aSteinstufe")).build();
        ItemStack brickstone = (new ItemBuilder(Material.BRICK_SLAB, (short) 5).setAmount(1).setDisplayName("§aZiegelsteinstufe")).build();
        ItemStack sand = (new ItemBuilder(Material.SANDSTONE_SLAB, (short) 1).setAmount(1).setDisplayName("§aSandsteinstufe")).build();
        ItemStack quartz = (new ItemBuilder(Material.QUARTZ_SLAB, (short) 7).setAmount(1).setDisplayName("§aQuartzstufe")).build();
        ItemStack brick = (new ItemBuilder(Material.STONE_BRICK_SLAB, (short) 4).setAmount(1).setDisplayName("§aZiegelstufe")).build();
        ItemStack nether = (new ItemBuilder(Material.NETHER_BRICK_SLAB, (short) 6).setAmount(1).setDisplayName("§aNetherziegelstufe")).build();

        player.openInventory(inv);

        for(int i = 0;i<54;i++){
            inv.setItem(i, glas);
        }
        inv.setItem(1, oak);
        inv.setItem(11, spruce);
        inv.setItem(19, jungle);
        inv.setItem(29, acacia);
        inv.setItem(37, darkoak);
        inv.setItem(47, birch);

        inv.setItem(7, stone);
        inv.setItem(15, sand);
        inv.setItem(25, brick);
        inv.setItem(33, brickstone);
        inv.setItem(43, nether);
        inv.setItem(51, quartz);
        inv.setItem(53, zurück);

        player.updateInventory();
    }

    public static void openBesondere(Player player){
        Inventory inv = Bukkit.createInventory(null, 3*9, "§6Besondere");
        ItemStack glas = (new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§7")).build();
        ItemStack zurück = (new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§cZurück")).build();

        ItemStack anvil = (new ItemBuilder(Material.ANVIL).setAmount(1).setDisplayName("§aAmboss")).build();
        ItemStack rail = (new ItemBuilder(Material.RAIL).setAmount(1).setDisplayName("§aSchienen")).build();
        ItemStack beacon = (new ItemBuilder(Material.BEACON).setAmount(1).setDisplayName("§aBeacon")).build();
        ItemStack snow = (new ItemBuilder(Material.SNOW).setAmount(1).setDisplayName("§aSchnee")).build();
        ItemStack ironfence = (new ItemBuilder(Material.IRON_BARS).setAmount(1).setDisplayName("§aEisengitter")).build();
        ItemStack spawner = (new ItemBuilder(Material.END_PORTAL_FRAME).setAmount(1).setDisplayName("§aEnderportal-Rahmen")).build();

        player.openInventory(inv);
        for(int i = 0;i<3*9;i++){
            inv.setItem(i, glas);
        }
        inv.setItem(10, spawner);
        inv.setItem(11, ironfence);
        inv.setItem(12, snow);
        inv.setItem( 14, anvil);
        inv.setItem(15, rail);
        inv.setItem(16, beacon);
        inv.setItem(26, zurück);

        player.updateInventory();
    }

    public static void openTeppiche(Player player){
        Inventory inv = Bukkit.createInventory(null, 54, "§aTeppiche");
        ItemStack glas = (new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§7")).build();
        ItemStack zurück = (new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setAmount(1).setDisplayName("§cZurück")).build();

        ItemStack white = (new ItemBuilder(Material.WHITE_CARPET).setAmount(1).setDisplayName("§aWeißer Teppich")).build();
        ItemStack orange = (new ItemBuilder(Material.ORANGE_CARPET).setAmount(1).setDisplayName("§aOranger Teppich")).build();
        ItemStack magenta = (new ItemBuilder(Material.MAGENTA_CARPET).setAmount(1).setDisplayName("§aMagenta Teppich")).build();
        ItemStack light_blue = (new ItemBuilder(Material.LIGHT_BLUE_CARPET).setAmount(1).setDisplayName("§aHellblauer Teppich")).build();
        ItemStack yellow = (new ItemBuilder(Material.YELLOW_CARPET).setAmount(1).setDisplayName("§aGelber Teppich")).build();
        ItemStack lime = (new ItemBuilder(Material.LIME_CARPET).setAmount(1).setDisplayName("§aHellgrüner Teppich")).build();
        ItemStack pink = (new ItemBuilder(Material.PINK_CARPET).setAmount(1).setDisplayName("§aPinker Teppich")).build();
        ItemStack gray = (new ItemBuilder(Material.GRAY_CARPET).setAmount(1).setDisplayName("§aGrauer Teppich")).build();
        ItemStack light_gray = (new ItemBuilder(Material.LIGHT_GRAY_CARPET).setAmount(1).setDisplayName("§aHellgrauer Teppich")).build();
        ItemStack cyan = (new ItemBuilder(Material.CYAN_CARPET).setAmount(1).setDisplayName("§aCyan Teppich")).build();
        ItemStack purple = (new ItemBuilder(Material.PURPLE_CARPET).setAmount(1).setDisplayName("§aLila Teppich")).build();
        ItemStack blue = (new ItemBuilder(Material.BLUE_CARPET).setAmount(1).setDisplayName("§aBlauer Teppich")).build();
        ItemStack brown = (new ItemBuilder(Material.BROWN_CARPET).setAmount(1).setDisplayName("§aBrauner Teppich")).build();
        ItemStack green = (new ItemBuilder(Material.GREEN_CARPET).setAmount(1).setDisplayName("§aGrüner Teppich")).build();
        ItemStack red = (new ItemBuilder(Material.RED_CARPET).setAmount(1).setDisplayName("§aRoter Teppich")).build();
        ItemStack black = (new ItemBuilder(Material.BLACK_CARPET).setAmount(1).setDisplayName("§aSchwarzer Teppich")).build();

        player.openInventory(inv);

        for(int i = 0;i<54;i++){
            inv.setItem(i, glas);
        }

        inv.setItem(1, white);
        inv.setItem(11, orange);
        inv.setItem(13, brown);
        inv.setItem(19, magenta);
        inv.setItem(22, green);
        inv.setItem(29, light_blue);
        inv.setItem(31, red);
        inv.setItem(37, yellow);
        inv.setItem(40, black);
        inv.setItem(47, lime);
        inv.setItem(7, pink);
        inv.setItem(15, gray);
        inv.setItem(25, light_gray);
        inv.setItem(33, cyan);
        inv.setItem(43, purple);
        inv.setItem(51, blue);
        inv.setItem(53, zurück);
        player.updateInventory();

    }

    public static void openOtherEnderChest(Player player, Player target) {
        EnderChest ec = new EnderChest(target);
        HashMap<Integer, ItemStack> temp = ec.getEnderChest(target);
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§5EnderChest");
        player.openInventory(inv);

        for(int i = 0; i < 54; ++i) {
            inv.setItem(i, (ItemStack)temp.get(i));
        }

        player.updateInventory();
    }

    public static void openEnderChest(Player player) {
        EnderChest ec = new EnderChest(player);
        HashMap<Integer, ItemStack> temp = ec.getEnderChest(player);
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§5EnderChest");
        player.openInventory(inv);

        for(int i = 0; i < 54; ++i) {
            inv.setItem(i, (ItemStack)temp.get(i));
        }

        player.updateInventory();
    }

}
