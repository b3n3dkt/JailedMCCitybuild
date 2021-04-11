package me.b3n3dkt.commands;

import java.io.File;
import java.util.ArrayList;

import com.onarandombox.MultiverseCore.api.MultiversePlugin;
import com.onarandombox.MultiverseCore.commands.MultiverseCommand;
import me.b3n3dkt.Citybuild;
import me.b3n3dkt.listener.PlayerMove;
import me.b3n3dkt.utils.Combat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    ArrayList<Player> temp;
    private int seconds;
    int count;

    public Spawn() {
        this.temp = PlayerMove.spawn;
        this.seconds = 5;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length != 0) {
                p.sendMessage(Citybuild.getPrefix() + "§cNutze bitte /spawn!");
            } else {
                if (this.temp.contains(p)) {
                    p.sendMessage(Citybuild.getPrefix() + "§cDu wirst bereits zum Spawn teleportiert!");
                    return true;
                }

                if (Combat.isTagged(p)) {
                    p.sendMessage(Citybuild.getPrefix() + "§7Du kannst dich nicht während eines Kampfes zum Spawn teleportieren!");
                } else {
                    PlayerMove.spawn.add(p);
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

                    this.count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Citybuild.getMain(), new Runnable() {
                        public void run() {
                            if (PlayerMove.spawn.contains(p)) {
                                switch(Spawn.this.seconds) {
                                    case 0:
                                        MultiversePlugin mvp;
                                        p.sendMessage(Citybuild.getPrefix() + "§7Du wurdest zum Spawn teleportiert!");
                                        Spawn.this.seconds = 5;
                                        PlayerMove.spawn.remove(p);
                                        p.teleport(loc);
                                        break;
                                    case 1:
                                        p.sendMessage(Citybuild.getPrefix() + "§7Du wirst in einer Sekunde zum Spawn teleportiert. §cBewege dich nicht!");
                                        seconds--;
                                        break;
                                    case 2:
                                        p.sendMessage(Citybuild.getPrefix() + "§7Du wirst in 2 Sekunden zum Spawn teleportiert. §cBewege dich nicht!");
                                        seconds--;
                                        break;
                                    case 3:
                                        p.sendMessage(Citybuild.getPrefix() + "§7Du wirst in 3 Sekunden zum Spawn teleportiert. §cBewege dich nicht!");
                                        seconds--;
                                        break;
                                    case 4:
                                        p.sendMessage(Citybuild.getPrefix() + "§7Du wirst in 4 Sekunden zum Spawn teleportiert. §cBewege dich nicht!");
                                        seconds--;
                                        break;
                                    case 5:
                                        p.sendMessage(Citybuild.getPrefix() + "§7Du wirst in 5 Sekunden zum Spawn teleportiert. §cBewege dich nicht!");
                                        seconds--;
                                }
                            } else {
                                Bukkit.getScheduler().cancelTask(Spawn.this.count);
                                seconds = 5;
                            }

                        }
                    }, 0L, 20L);
                }
            }
        }

        return false;
    }
}
