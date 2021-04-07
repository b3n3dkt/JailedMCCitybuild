package me.b3n3dkt.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Homes {
    private FileBuilder fb;
    private String name;
    private String uuid;

    public Homes(String uuid, String name){
        this.name = name;
        this.uuid = uuid;
        this.fb = new FileBuilder("plugins//Citybuild//Homes//" + uuid, name + ".yml"  );
    }

    public boolean homeExists(){return this.fb.exist();}

    public static List getHomes(String uuid){
        List<String> temp = new ArrayList<>();
        File dir = new File("plugins//Citybuild//Homes//" + uuid);
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

    public void removeHome(){
        this.fb.delete();
    }

    public Location getHome(){
        String w = this.fb.getString("player.home." + name + ".world");
        double x = this.fb.getDouble("player.home." + name + ".x");
        double y = this.fb.getDouble("player.home." + name + ".y");
        double z = this.fb.getDouble("player.home." + name + ".z");
        double yaw = this.fb.getDouble("player.home." + name + ".yaw");
        double pitch = this.fb.getDouble("player.home." + name + ".pitch");
        Location loc = new Location(Bukkit.getWorld(w), x, y, z);
        loc.setYaw((float) yaw);
        loc.setPitch((float) pitch);
        return loc;
    }

    public void setHome(String world, double x, double y, double z, double yaw, double pitch){
        this.fb.setValue("player.home." + name + ".world", world);
        this.fb.setValue("player.home." + name + ".x", x);
        this.fb.setValue("player.home." + name + ".y", y);
        this.fb.setValue("player.home." + name + ".z", z);
        this.fb.setValue("player.home." + name + ".yaw", yaw);
        this.fb.setValue("player.home." + name + ".pitch", pitch);
        this.fb.save();
    }

}
