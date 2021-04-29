package me.b3n3dkt.shop;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

import me.b3n3dkt.utils.FileBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shop {
    private FileBuilder f;
    private String name;
    private static DecimalFormat df = new DecimalFormat("0.00");

    public Shop(String name) {
        this.f = new FileBuilder("plugins//Citybuild//Shop",  name + ".yml");
        this.name = name;
    }

    public boolean categoryExist() {
        return this.f.exist();
    }

    public void createCategory(){
        this.f.setValue("itemCount", 0);
        this.f.save();
    }

    public void setItemCount(int amount){
        this.f.setValue("itemCount", amount);
        this.f.save();
    }

    public int getItemCount(){ return this.f.getInt("itemCount"); }

    public boolean ifItemStackExists(int itemCount){
        if(getItemStack(itemCount) == null){
            return false;
        }else if(getItemStack(itemCount) != null){
            return true;
        }
        return false;
    }
    public ItemStack getItemStack(int itemCount){
        ItemStack stack =this.f.getItemStack("items." + itemCount + ".stack");
        ItemMeta stackmeta =  stack.getItemMeta();
        stackmeta.setLore(Arrays.asList("§7ID: §a" + getID(itemCount), "§7Preis: §a" + df.format(getPrice(itemCount)), "§7Verfügbar: §a" + getAvailable(itemCount)));
        stack.setItemMeta(stackmeta);
        return stack;
    }
    public ItemStack getItemStackWithoutLore(int itemCount){return this.f.getItemStack("items." + itemCount + ".stack");}
    public double getPrice(int itemCount){ return this.f.getDouble("items." + itemCount + ".price");}
    public int getAvailable(int itemCount){return this.f.getInt("items." + itemCount + ".available");}
    public int getSold(int itemCount){return this.f. getInt("items." + itemCount + ".sold");}
    public int getID(int itemCount){return this.f.getInt("items." + itemCount + ".id");}

    public void addItemPrice(int itemCount){
        double percent = getPercentUp(itemCount);
        double newprice = percent*getPrice(itemCount);
        setPrice(itemCount, newprice);
    }
    public void removeItemPrice(int itemCount){
        double percent = getPercentDown(itemCount);
        double newprice = percent*getPrice(itemCount);
        setPrice(itemCount, newprice);
    }

    public void setPrice(int itemCount, double newPrice){
        this.f.setValue("items." + itemCount + ".price", newPrice);
        this.f.save();
    }
    public void setAvailable(int itemCount, int amount){
        this.f.setValue("items." + itemCount + ".available", amount);
        this.f.save();
    }
    public void setSold(int itemCount, int amount){
        this.f.setValue("items." + itemCount + ".sold", amount);
        this.f.save();
    }

    public void addItem(ItemStack stack, double price, int available, int id, double percentUp, double percentDown, double sellMulti){
        this.f.setValue("items." + getItemCount() + ".stack", stack);
        this.f.setValue("items." + getItemCount() + ".price", price );
        this.f.setValue("items." + getItemCount() + ".available", available);
        this.f.setValue("items." + getItemCount() + ".sold", 0);
        this.f.setValue("items." + getItemCount() + ".id", id);
        this.f.setValue("items." + getItemCount() + ".percentUp", percentUp);
        this.f.setValue("items." + getItemCount() + ".percentDown", percentDown);
        this.f.setValue("items." + getItemCount() + ".sellMulti", sellMulti);
        this.f.save();
        setItemCount(getItemCount()+1);
    }

    public double getPercentDown(int itemCount){
        double perccent = Double.valueOf(this.f.getString("items." + itemCount + ".percentDown"));
        return perccent;
    }

    public double getSellMulti(int itemCount){
        return this.f.getDouble("items." + itemCount + ".sellMulti");
    }

    public double getPercentUp(int itemCount){
        double perccent = Double.valueOf(this.f.getString("items." + itemCount + ".percentUp"));
        return perccent;
    }

    public void removeItem(int itemCount){
        this.f.setValue("items." + itemCount, null);
    }

    public HashMap<Integer, ItemStack> getItems(){
        HashMap<Integer, ItemStack> temp = new HashMap<Integer, ItemStack>();
        for(int i = 0;i<getItemCount();i++){
            int s = 1;
            if (getItemStack(i) != null) {
                ItemStack stack = getItemStack(i);
                ItemMeta stackmeta =  stack.getItemMeta();
                stackmeta.setLore(Arrays.asList("§7ID: §a" + i, "§7Price: §a" + getPrice(i), "§7Available: §a" + getAvailable(i)));
                stack.setItemMeta(stackmeta);
                temp.put(s, stack);
                s++;
            }
        }
        return temp;
    }

    public static double roundToDecimals(double d, int c) {
        int temp = (int)(d * Math.pow(10 , c));
        return ((double)temp)/Math.pow(10 , c);
    }

}
