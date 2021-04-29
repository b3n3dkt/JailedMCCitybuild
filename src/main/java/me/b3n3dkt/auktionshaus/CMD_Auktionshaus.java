package me.b3n3dkt.auktionshaus;

import me.b3n3dkt.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class CMD_Auktionshaus implements CommandExecutor{

	public Items item = new Items();

	public Inventory inv = Bukkit.createInventory(null, 9*6, "§6Auktionshaus"); //§6Auction house

	public static ArrayList<Player> inAh = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length != 0) {
				p.sendMessage(me.b3n3dkt.Citybuild.getPrefix() + "§cNutze /auktionshaus|/ah !");
			}else if(args.length == 0) {
				openAuktionshaus(p);
			}
		}
		return false;
	}

	public static ItemStack getPlayerHead(String player) {
		boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
		Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
		ItemStack item = new ItemStack(type, 1);
		if(!isNewVersion) {
			item.setDurability((short) 3);
		}
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(player);
		item.setItemMeta(meta);

		return item;
	}

	public static void openAuktionshaus(Player p) {
		Items items = new Items();
		CMD_Auktionshaus ah = new CMD_Auktionshaus();
		Inventory inv = Bukkit.createInventory(null, 9*6, "§6Auktionshaus");
		inAh.add(p);
		ItemStack skull = getPlayerHead(p.toString());
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName("§eDeine Items"); //§eYour Items
		skull.setItemMeta(meta);
		
		ItemStack anvil = new ItemStack(Material.ANVIL);
		ItemMeta anvilmeta = anvil.getItemMeta();
		anvilmeta.setDisplayName("§bItems verkaufen"); //§bSell Item
		anvil.setItemMeta(anvilmeta);
		
		ItemStack paper = new ItemStack(Material.PAPER);
		ItemMeta papermeta = paper.getItemMeta();
		papermeta.setDisplayName("§aVerlauf"); //§aHistory
		paper.setItemMeta(papermeta);
		
		ItemStack glas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
		ItemMeta glasmeta = glas.getItemMeta();
		glasmeta.setDisplayName("§6");
		glas.setItemMeta(glasmeta);
		
		ItemStack barrier = new ItemStack(Material.BARRIER);
		ItemMeta barriermeta = barrier.getItemMeta();
		barriermeta.setDisplayName("§6");
		barrier.setItemMeta(barriermeta);
		
		ItemStack greenglas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		ItemMeta greenglasmeta = greenglas.getItemMeta();
		greenglasmeta.setDisplayName("§aNächste Seite"); //§aNext Page
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7");
		lore.add("§7Momentane Seite: 1"); //§7Currently Page: 1
		lore.add("§7");
		greenglasmeta.setLore(lore);
		greenglas.setItemMeta(greenglasmeta);
		
		ItemStack redglas= new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta redglasmeta = redglas.getItemMeta();
		redglasmeta.setDisplayName("§cLetzte Seite"); //§cLast Page
		redglas.setItemMeta(redglasmeta);
		
		Integer index = ah.item.getIndex();
		int slot = 0;
		
		for(int i = 0;i<index;i++) {
			if(ah.item.getItem(i) != null) {
				if(slot > 44) {
				}else {
				ItemStack stack = ah.item.getItem(i);
				ItemMeta stackmeta = stack.getItemMeta();
				ArrayList<String> stacklore = new ArrayList<String>();
				stacklore.add("§7");
				stacklore.add("§7-Verkäufer: §a" + ah.item.getPlayerName(i)); //§7-Seller: §a
				stacklore.add("§7-ID: §a" + i);
				if(ah.item.getInstantSell(i) == true) {
					stacklore.add("§7-Sofort Kauf"); //§7-Instant Buy
					stacklore.add("§7-Preis: §a" + ah.item.getPrice(i) + "$"); //§7-Price: §a
				}else if(ah.item.getInstantSell(i) == false){
					long current = System.currentTimeMillis();
					long end = ah.item.getEnd(i).longValue();
					long millis = end - current;
				    int second = 0;
				    int minutes = 0;
				    int hours = 0;
				    while (millis > 1000L) {
				      millis -= 1000L;
				      second++;
				    } 
				    while (second > 60) {
				      second -= 60;
				      minutes++;
				    } 
				    while (minutes > 60) {
				      minutes -= 60;
				      hours++;
				    }
					stacklore.add("§7-Auction Ends in "+ hours + " Hour(s) and " + minutes + " Minute(s)");
					stacklore.add("§7-Minimum Price: §a" + ah.item.getPrice(i) + "$");
					
				}
				stacklore.add("§7");
				stackmeta.setLore(stacklore);
				stack.setItemMeta(stackmeta);
				
				inv.setItem(slot, stack);
				slot++;
				}
			}
		}
		
		inv.setItem(47, skull);
		inv.setItem(49, anvil);
		inv.setItem(51, paper);
		inv.setItem(46, glas);
		inv.setItem(48, glas);
		inv.setItem(50, glas);
		inv.setItem(52, glas);
		inv.setItem(45, barrier);
		inv.setItem(53, greenglas);
		
		p.openInventory(inv);
		
		p.updateInventory();
	}
	
}
