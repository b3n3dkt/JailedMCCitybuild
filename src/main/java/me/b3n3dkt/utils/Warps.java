package me.b3n3dkt.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Warps {
    private FileBuilder fb;
    private String name;

    public Warps(String name){
        this.name = name;
        this.fb = new FileBuilder("plugins//Citybuild//Warps", name +".yml");
    }

    public boolean warpExists(){ return this.fb.exist(); }

    public static List getWarps(){
        List<String> temp = new ArrayList<>();
        File dir = new File("plugins//Citybuild/Warps");
        File[] file = dir.listFiles();
        int length = file.length;

        for(int i = 0;i<length;i++){
            File f = file[i];
            if(f.isFile()){
                temp.add(f.getName().replace(".yml", ""));
            }
        }
        return temp;
    }

    public void removeWarp(){this.fb.delete();}

    public Location getWarp(){
        String w = this.fb.getString("Warp.world");
        double x = this.fb.getDouble("Warp.x");
        double y = this.fb.getDouble("Warp.y");
        double z = this.fb.getDouble("Warp.z");
        double yaw = this.fb.getDouble("Warp.yaw");
        double pitch = this.fb.getDouble("Warp.pitch");
        Location loc = new Location(Bukkit.getWorld(w), x, y, z);
        loc.setYaw((float) yaw);
        loc.setPitch((float) pitch);
        return loc;
    }

    public void setWarp(String world, double x, double y, double z, double yaw, double pitch){
        this.fb.setValue("Warp.world", world);
        this.fb.setValue("Warp.x", x);
        this.fb.setValue("Warp.y", y);
        this.fb.setValue("Warp.z", z);
        this.fb.setValue("Warp.yaw", yaw);
        this.fb.setValue("Warp.pitch", pitch);
        this.fb.save();
    }
}
