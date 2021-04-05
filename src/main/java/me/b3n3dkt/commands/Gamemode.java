package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        if (s instanceof Player) {
            Player p = (Player)s;
            if (p.hasPermission("jailedmc.command.gamemode")) {
                if (args.length == 0) {
                    p.sendMessage(Citybuild.getPrefix() + "§cNutze /gamemode <0|1|2|3> <Spieler>!");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        setGameMode(null, p, GameMode.SURVIVAL);
                    } else if (args[0].equalsIgnoreCase("1")) {
                        setGameMode(null, p, GameMode.CREATIVE);
                    } else if (args[0].equalsIgnoreCase("2")) {
                        setGameMode(null, p, GameMode.ADVENTURE);
                    } else if (args[0].equalsIgnoreCase("3")) {
                        setGameMode(null, p, GameMode.SPECTATOR);
                    } else {
                        p.sendMessage(Citybuild.getPrefix() + "§cNutze /gamemode <0|1|2|3> <Spieler>!");
                    }
                } else if (args.length == 2) {
                    if (!p.hasPermission("jailedmc.command.gamemode.other")) {
                        p.sendMessage(Citybuild.getNoperm());
                        return true;
                    }

                    Player t = Bukkit.getPlayer(args[1]);
                    if (t != null) {
                        if (args[0].equalsIgnoreCase("0")) {
                            setGameMode(t, p, GameMode.SURVIVAL);
                        } else if (args[0].equalsIgnoreCase("1")) {
                            setGameMode(t, p, GameMode.CREATIVE);
                        } else if (args[0].equalsIgnoreCase("2")) {
                            setGameMode(t, p, GameMode.ADVENTURE);
                        } else if (args[0].equalsIgnoreCase("3")) {
                            setGameMode(t, p, GameMode.SPECTATOR);
                        } else {
                            p.sendMessage(Citybuild.getPrefix() + "§cNutze /gamemode <0|1|2|3> <Spieler>!");
                        }
                    } else {
                        p.sendMessage(Citybuild.getPrefix() + "§7Der angegebene Spieler ist nicht Online!");
                    }
                } else {
                    p.sendMessage(Citybuild.getPrefix() + "§cNutze /gamemode <0|1|2|3> <Spieler>!");
                }
            } else {
                p.sendMessage(Citybuild.getNoperm());
            }
        }

        return false;
    }

    public void setGameMode(Player target, Player sender, GameMode gameMode) {
        if (target != null) {
            sender.sendMessage(Citybuild.getPrefix() + "§7Du hast §8'§3" + target.getName() + "§8' §7Gamemode §8'§3" + gameMode.toString() + "§8' §7gegeben!");
            target.sendMessage(Citybuild.getPrefix() + "§7Du bist nun im Gamemode §8'§3" + gameMode.toString() + "§8'§7!");
            target.setGameMode(gameMode);
        }else if(target == null){
            sender.sendMessage(Citybuild.getPrefix() + "§7Du bist nun im Gamemode §8'§3" + gameMode.toString() + "§8'§7!");
            sender.setGameMode(gameMode);
        }
    }
}
