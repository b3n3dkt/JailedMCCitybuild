package me.b3n3dkt.utils;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Items {
	
	private static FileBuilder fb;
	
	public static HashMap<Integer, String> indexPlayer = new HashMap<Integer, String>();
	public static HashMap<Integer, ItemStack> indexItem = new HashMap<Integer, ItemStack>();
	
	public Items() {
		fb = new FileBuilder("plugins//Citybuild//Auktionshaus//", "items.yml");
	}
	
	public boolean exist() {
		return fb.exist();
	}
	
	public void createConfig() {
		fb.setValue("index", 0);
		fb.setValue("pages", 1);
		fb.setValue("currentItems", 0);
		fb.save();
	}
	
	public void addItem(Player player, ItemStack stack, String uuid, double price, String dn, String ench, String lore, Integer id, boolean instantsell) {
		PlayerData data = new PlayerData(player);
		data.addItem(player.getName(), stack, uuid, price, dn, ench, lore, getIndex(), instantsell);
		String path = "listings." + getIndex();
		fb.setValue(path + ".item", stack);
		fb.setValue(path + ".seller", ""+player.getName()+"");
		fb.setValue(path + ".price", ""+price+"");
		fb.setValue(path + ".uuid", ""+uuid+"");
		fb.setValue(path + ".time", ""+Long.valueOf(System.currentTimeMillis())+"");
		fb.setValue(path + ".id", id);
		fb.setValue(path + ".instantsell", instantsell);
		if(instantsell == false) {
			fb.setValue(path + ".ending", ""+Long.valueOf(System.currentTimeMillis()+86400000));
			fb.setValue(path + ".ended", "false");
			fb.setValue(path + ".sold",  "false");
			fb.setValue(path + ".offers", " ");
			fb.setValue(path + ".offers.index", ""+0+"");
		}
		fb.setValue("index", getIndex()+1);
		fb.setValue("currentItems", getCurrentItems() +1);
		fb.save();
		update();
	}
	
	public void addOffer(Player player, double price, String uuid, Integer index) {
		PlayerData data = new PlayerData(player);
		data.addOffer(player.getName(), price, uuid, index);
		String path = "listings." + index;
		fb.setValue(path + ".offers.index", getAucIndex(index) +1);
		if(doesAucExist(index, player.getName())) {
			fb.setValue(path + ".offers." + getAucIndex(index) +".price", ""+price+"");
		}else {
		fb.setValue(path + ".offers." + getAucIndex(index) +".player", ""+player+"");
		fb.setValue(path + ".offers." + getAucIndex(index) +".price", ""+price+"");
		fb.setValue(path + ".offers." + getAucIndex(index) +".uuid", ""+uuid+"");
		fb.setValue(path + ".offers." + getAucIndex(index) + ".id", getAucIndex(index));
		}
		fb.save();
	}
	
	public void checkIfTimeIsOver() {
		Bukkit.getScheduler().runTaskAsynchronously(Citybuild.getMain(), new Runnable() {
			
			@Override
			public void run() {
				Bukkit.getConsoleSender().sendMessage("Checking...");
				for(int i1 = 0;i1<getIndex();i1++) {
					Bukkit.getConsoleSender().sendMessage("Test1");
					if(getItem(i1) != null) {
						Bukkit.getConsoleSender().sendMessage("Test2");
						//if(getEnd(i1) <= System.currentTimeMillis() || getEnd(i1) == System.currentTimeMillis()){
						Bukkit.getConsoleSender().sendMessage("Test3");
							Bukkit.getConsoleSender().sendMessage("Test4");
							if(getAucIndex(i1) != 0) {
								Bukkit.getConsoleSender().sendMessage("Test5");
										Bukkit.getConsoleSender().sendMessage("New winner found");
										Double bid = getHighestBid(i1);
										Bukkit.getConsoleSender().sendMessage("Test6");
										String player = getPlayerNameFromHighestBid(i1);
										Bukkit.getConsoleSender().sendMessage("Test7");
										PlayerData data = new PlayerData(Bukkit.getPlayer(player));
										Bukkit.getConsoleSender().sendMessage("Test8");
											data.addCollectableItems(getItem(i1), getPlayerName(i1), bid, getUUID(i1), getID(i1).toString());
											//deleteItem(i1);
											Bukkit.getConsoleSender().sendMessage("The Winner is:" + player + " with " + bid +"$");
								
							}else {
								Bukkit.getConsoleSender().sendMessage("No winner found...");
							}
					//}
					}
				}
				Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
					@Override
					public void run() {
						checkIfTimeIsOver();
						Bukkit.getConsoleSender().sendMessage("Repeat in 30 Seconds...");
					}
				}, 20*10);
				
			}
			
		});

	}
	
	public void loadDefaults() {
		Integer current = 0;
		for(int i=0;i<getIndex();i++) {
			if(getItem(i) != null) {
				String name = getPlayerName(i);
				current++;
				indexPlayer.put(i, name);
				indexItem.put(i, getItem(i));
			}
		}
		setCurrentItems(current);
		update();
	}
	
	public void deleteItem(Integer index) {
		PlayerData data = new PlayerData(Bukkit.getPlayer(getPlayerName(index)));
		data.deleteItem(getID(index));
		String path = "listings." + index;
		fb.setValue(path + ".item", null);
		fb.setValue(path + ".seller", null);
		fb.setValue(path + ".price", null);
		fb.setValue(path + ".uuid", null);
		fb.setValue(path + ".time", null);
		fb.setValue(path + ".id", null);
		fb.setValue(path + ".instantsell",  null);
		for(int i = 0;i<getAucIndex(index);i++) {
			fb.setValue(path + ".offers." + i + ".player", null);
			fb.setValue(path + ".offers." + i + ".price", null);
			fb.setValue(path + ".offers." + i + ".uuid", null);
			fb.setValue(path + ".offers." + i + ".id", null);
			fb.setValue(path + ".offers.index" , null);
		}
		fb.save();
		update();
	}
	
    public List<ItemStack> getItems() {
        final List<ItemStack> temp = new ArrayList<ItemStack>();
    	for(int i = 1;i<getIndex();i++) {
    		if(getItem(i) != null) {
            ItemStack stack = getItem(i);
            temp.add(stack);
    		}
    	}
    	
        return temp;
    }
    
    public void update() {
    	Integer currentItems = getCurrentItems();
    	Integer pages = getPages();
        	if(currentItems >= pages*46) { 
        		setPages(getPages()+1);
        	}else if(currentItems+46 <= pages){
        		if(pages != 1) {
        		setPages(pages-1);
        		}
        	}
    
    }
    
    public String getPlayerNameFromHighestBid(Integer index) {
    	String player = null;
    	Double bid = 0.0;
    	for(int i = 0;i<getAucIndex(index);i++) {
    		Double tempBid = Double.parseDouble(fb.getString("listings." + index + ".offers." + i +".player"));
    		if(tempBid >= bid) {
    			bid = tempBid;
    			player = fb.getString("listings." + index + ".offers." + i + ".player");
    		}
    	}
    	
    	return player;
    }
    
    public Double getHighestBid(Integer index) {
    	Double bid = 0.0;
    	for(int i = 0;i<getAucIndex(index);i++) {
    		if(Double.parseDouble(fb.getString("listings." + index + ".offers." + i +".price")) >= bid) {
    			bid = Double.parseDouble(fb.getString("listings." + index + ".offers." + i +".price"));
    		}
    	}
    	
    	return bid;
    }
    
    
    public Integer getAucIDFromName(Integer index, String name) {
    	for(int i = 0;i<getAucIndex(index);i++) {
    		if(getAucPlayer(index, i) == name) {
    			return fb.getInt("listings." + index + ".offers." + i + "id");
    		}
    	}
    	return null;
    }
    
    public boolean doesAucExist(Integer index, String name) {
    	int aucIndex = getAucIndex(index);
    	for(int i = 0;i<aucIndex;i++) {
        	if(fb.getString("listings." + index + ".offers." + i + ".player") == name) {
        		return true;
        	}
    	}
    	return false;
    }
    
    public Double getAucBid(Integer index, Integer aucIndex) {
    	return Double.parseDouble(fb.getString("listings." + index + ".offers." + aucIndex +".price"));
    }
    
    public String getAucPlayer(Integer index, Integer aucIndex) {
    	return fb.getString("listings." + index + ".offers." + aucIndex + ".player"); 
    }
    
	public boolean isSold(Integer index) {
		return fb.getBoolean("listings." + index + ".sold");
	}
    
	public boolean isEnded(Integer index) {
		return fb.getBoolean("listings." + index + ".ended");
	}
    
    public Integer getAucIndex(Integer index) {
    	return fb.getInt("listings." + index + ".offers.index");
    }
    
    public Long getEnd(Integer index) {
	  	String stime = fb.getString("listings." + index + ".ending");
	  	Long time = Long.parseLong(stime);
	  	return time;
	  }
    
    public boolean getInstantSell(Integer index) {
		return fb.getBoolean("listings." + index + ".instantsell");
	}
    
    public Integer getID(Integer index) {
    	return fb.getInt("listings."+ index +".id");
    }
    
    public Integer getPages() {
    	return fb.getInt("pages");
    }
    
    public void setPages(Integer value) {
    	fb.setValue("pages", value);
    	fb.save();
    }
    
    public Double getPrice(Integer index) {
    	return Double.valueOf(fb.getString("listings." + index + ".price"));
    }
    
    public void setPrice(Double value, Integer index) {
    	fb.setValue("listings." + index + ".price", value);
    	fb.save();
    }
    
    public Long getTime(Integer index) {
      	String stime = fb.getString("listings." + index + ".time");
      	Long time = Long.parseLong(stime);
      	return time;
      }
    
    public Integer getCurrentItems() {
    	return fb.getInt("currentItems");
    }
    
    public void setCurrentItems(Integer value) {
    	fb.setValue("currentItems", value);
    	fb.save();
    }
    
	public ItemStack getItem(Integer index) {
		return fb.getItemStack("listings." + index + ".item");
	}
	
	public Integer getIndex() {
		return fb.getInt("index");
	}
	
	public String getUUID(Integer index) {
		String uuid = fb.getString("listings." + index + "uuid");
		return uuid;
	}
	
	public String getPlayerName(Integer index) {
		return fb.getString("listings." + index +".seller");
	}

	/*
	 * Wenn Mehr Items als Platz f�r eine Seite => Neue Seite
	 * Wenn Weniger Items als Platz f�r eine Seite => Seite l�schen
	 * 
	 * 47/46 >= 1 = Page++ index>46=++
	 * 46/46 = 1 = Page--  index=46 = --
	 * 45/46 <= 1 = Nothing index<46 = nothing 
	 * 91/46 >= 2 = Page++	index>46=++
	 * 90/46 = 2 = Page--	index=46=nothing
	 * 89/46 <= 2 = Nothing	index<46=--
	 * 
	 * 
	 *
	 * */
	
}
