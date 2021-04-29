package me.b3n3dkt.auktionshaus;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryCloseListener implements Listener{
	
	@EventHandler
	public void onHandle(InventoryCloseEvent e) {
		try {
		
		if(e.getView().getTitle().equalsIgnoreCase("§bWähle ein Item!")) {
			InventoryClick_Auktionshaus.currentValue = 0;

			Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
				
				@Override
				public void run() {
		              if (InventoryClick_Auktionshaus.isInChooseMen &&
		                      e.getPlayer().getInventory().getItem(31) != new ItemStack(Material.AIR)) {
		                      ItemStack stack = e.getInventory().getItem(31);
		                      if(stack != null) {
		                      e.getPlayer().getInventory().addItem(new ItemStack[] { stack });
		                    } 
		              }
				}
			}, 1);
		}else if(e.getView().getTitle().equalsIgnoreCase("§eSofort Verkaufsangebot")) {
			Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
				
				@Override
				public void run() {
					if(InventoryClick_Auktionshaus.isInSellMenu) {
						e.getPlayer().openInventory(e.getInventory());
					}
					
				}
			}, 1);
		}else if(e.getView().getTitle().equalsIgnoreCase("§6Bist du sicher?")) {
			Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
				
				@Override
				public void run() {
					if(InventoryClick_Auktionshaus.canCloseInv == false) {
						e.getPlayer().openInventory(e.getInventory());
					}
					
				}
			}, 1);
		}else if(e.getView().getTitle().equalsIgnoreCase("§6Auktionshaus")) {
			CMD_Auktionshaus.inAh.remove(e.getPlayer());
		}else if(e.getView().getTitle().equalsIgnoreCase("§eDeine Items")) {

		}else if(e.getView().getTitle().equalsIgnoreCase("§6Item Einstellungen")) {
			
		}else if(e.getView().getTitle().equalsIgnoreCase("§6Bestätigen")) {
			Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
				
				@Override
				public void run() {
					if(InventoryClick_YourItems.isInDelMen == true) {
						e.getPlayer().openInventory(e.getInventory());
					}
					
				}
			}, 1);
		}else if(e.getView().getTitle().equalsIgnoreCase("§6Preis ändern")) {
			Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
				
				@Override
				public void run() {
					if(InventoryClick_YourItems.isInPrChMen == true) {
						e.getPlayer().openInventory(e.getInventory());
					}
					
				}
			}, 1);
		}
		
		}catch(Exception e1) {
			
		}
		
	}

}
