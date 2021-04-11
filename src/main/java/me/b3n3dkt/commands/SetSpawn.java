package me.b3n3dkt.commands;

import java.io.File;
import java.io.IOException;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("jailedmc.command.setspawn")) {
                if (args.length != 0) {
                    player.sendMessage(Citybuild.getPrefix() + "§cNutze /setspawn!");
                } else if (args.length == 0) {
                    //setLocation("spawn", player.getLocation());
                    File file = new File("plugins//Citybuild//spawns.yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    cfg.set("Spawn.X", player.getLocation().getX());
                    cfg.set("Spawn.Y", player.getLocation().getY());
                    cfg.set("Spawn.Z", player.getLocation().getZ());
                    cfg.set("Spawn.Yaw", (double)player.getLocation().getYaw());
                    cfg.set("Spawn.Pitch", (double)player.getLocation().getPitch());
                    cfg.set("Spawn.World", player.getLocation().getWorld().getName().toString());
                    try {
                        cfg.save(file);
                    } catch (IOException var9) {
                        var9.printStackTrace();
                    }

                    player.sendMessage(Citybuild.getPrefix() + "§7Du hast den Spawn erfolgreich gesetzt!");
                }
            } else {
                player.sendMessage(Citybuild.getNoperm());
            }
        }

        return false;
    }

    public static String state2 = "";
    public static File file = new File("plugins//Citybuild//spawns.yml");
    public static YamlConfiguration cfg;

    public static void savecfg() {
        try {
            cfg.save(file);
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }

    public static void setLocation(String name, Location loc) {
        cfg.set(name + ".world", loc.getWorld().getName());
        cfg.set(name + ".x", loc.getX());
        cfg.set(name + ".y", loc.getY());
        cfg.set(name + ".z", loc.getZ());
        cfg.set(name + ".pitch", loc.getPitch());
        cfg.set(name + ".yaw", loc.getYaw());
        savecfg();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception var3) {
            }
        }

    }

    public static Location getLocation(String name) {
        World world = Bukkit.getWorld(cfg.getString(name + ".world"));
        double x = cfg.getDouble(name + ".x");
        double y = cfg.getDouble(name + ".y");
        double z = cfg.getDouble(name + ".z");
        Location loc = new Location(world, x, y, z);
        loc.setPitch((float)cfg.getInt(name + ".pitch"));
        loc.setYaw((float)cfg.getInt(name + ".yaw"));
        return loc;
    }

    public static String isspawnxist() {
        if (!file.exists()) {
            state2 = null;
        } else {
            state2 = "exestiert";
        }

        return state2;
    }

    static {
        new YamlConfiguration();
        cfg = YamlConfiguration.loadConfiguration(file);
    }

}
