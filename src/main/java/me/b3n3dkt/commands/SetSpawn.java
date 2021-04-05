package me.b3n3dkt.commands;

import java.io.File;
import java.io.IOException;

import me.b3n3dkt.Citybuild;
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
                    File file = new File("plugins//Citybuild//spawns.yml");
                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    cfg.set("Spawn.X", player.getLocation().getX());
                    cfg.set("Spawn.Y", player.getLocation().getY());
                    cfg.set("Spawn.Z", player.getLocation().getZ());
                    cfg.set("Spawn.Yaw", (double)player.getLocation().getYaw());
                    cfg.set("Spawn.Pitch", (double)player.getLocation().getPitch());
                    cfg.set("Spawn.World", player.getLocation().getWorld().getName());
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
}
