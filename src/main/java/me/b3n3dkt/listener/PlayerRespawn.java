package me.b3n3dkt.listener;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn
        implements Listener
{
    @EventHandler
    public void onHandle(PlayerRespawnEvent e) {
        File file = new File("plugins//Citybuild//spawns.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        String w = cfg.getString("Spawn.World");
        double x = cfg.getDouble("Spawn.X");
        double y = cfg.getDouble("Spawn.Y");
        double z = cfg.getDouble("Spawn.Z");
        double yaw = cfg.getDouble("Spawn.Yaw");
        double pitch = cfg.getDouble("Spawn.Pitch");
        Location loc = new Location(Bukkit.getWorld(w), x, y, z);
        loc.setYaw((float) yaw);
        loc.setPitch((float) pitch);
        e.setRespawnLocation(loc);
    }
}
