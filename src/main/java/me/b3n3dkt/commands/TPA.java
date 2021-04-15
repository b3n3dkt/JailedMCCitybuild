package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TPA implements CommandExecutor {

    private int seconds = 5;
    private int taskid;
    HashMap<Player, Player> tpa = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("accept")){
                    if(tpa.containsKey(player)){
                        Player target = tpa.get(player);
                        target.sendMessage(Citybuild.getPrefix() + "§7Du wirst in §8'§35 Sekunden§8' §7zu §8'§3" + player.getName() + "§8' §7teleportiert!");
                        player.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§3" + target.getName() + "§8' §7wird in §8'§35 Sekunden§8' §7zu dir teleportiert!");
                        taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Citybuild.getMain(), new Runnable() {
                            @Override
                            public void run() {
                                switch (TPA.this.seconds){
                                    case 0:
                                        target.sendMessage(Citybuild.getPrefix() + "§7Du wurdest zu §8'§3" + player.getName() + "§8' §7teleportiert!");
                                        player.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§3" + target.getName() + "§8' §7wurde zu dir teleportiert!");
                                        TPA.this.seconds = 5;
                                        target.teleport(player.getLocation());
                                        tpa.remove(player);
                                        Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
                                            @Override
                                            public void run() {
                                                target.teleport(player.getLocation());
                                            }
                                        }, 10);
                                        Bukkit.getScheduler().cancelTask(taskid);
                                    case 1:
                                        target.playSound(target.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
                                    case 2:
                                        target.playSound(target.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
                                    case 3:
                                        target.playSound(target.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
                                    case 4:
                                        target.playSound(target.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
                                    case 5:
                                        target.playSound(target.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
                                }
                                seconds--;
                            }
                        }, 0, 20L);
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cNiemand hat dir eine TPA Anfrage geschickt!");
                    }
                }else if(args[0].equalsIgnoreCase("deny")){
                    if(tpa.containsKey(player)){
                        Player target = tpa.get(player);
                        player.sendMessage(Citybuild.getPrefix() + "§7Du hast die Anfrage abgelehnt!");
                        target.sendMessage(Citybuild.getPrefix() + "§7Die TPA Anfrage wurde abgelehnt!");
                        tpa.remove(player);
                    }else{
                        player.sendMessage(Citybuild.getPrefix() + "§cNiemand hat die eine TPA Anfrage geschickt!");
                    }
                }else{
                    Player target = Bukkit.getPlayer(args[0]);
                    if(player != null){
                        Player temp = tpa.get(target);
                        if(!tpa.containsKey(target) && temp != player){
                            if(!tpa.containsKey(player)){
                                if(target == player){player.sendMessage(Citybuild.getPrefix() + "§cDu kannst dir selber keine TPA schicken!"); return true;}
                                tpa.put(target,player);
                                player.sendMessage(Citybuild.getPrefix() + "§7Du hast dem Spieler §8'§e" + target.getName() + "§8' §7eine TPA Anfrage geschickt! Die Anfrage verfällt nach 30 Sekunden!");
                                target.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§3" + player.getName() + "§8' §7möchte sich zu dir Teleportieren! Nehme die Anfrage mit §a/tpa accept §7an oder lehne sie mit §c/tpa deny §7ab! Die Anfrage verfällt nach 30 Sekunden!");
                                Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
                                    @Override
                                    public void run() {
                                        if(tpa.containsKey(target)){
                                            tpa.remove(target);
                                        }
                                    }
                                }, 20*30);
                            }else{
                                if(tpa.containsKey(player)){
                                    player.sendMessage(Citybuild.getPrefix() + "§7Bitte warte einen Moment, du hast bereits eine TPA Anfrage bekommen!");
                                }
                            }
                        }else if(tpa.containsKey(target) && temp == player){
                            player.sendMessage(Citybuild.getPrefix() + "§cDu hast dem Spieler bereits eine TPA Anfrage geschickt!");
                        }else if(tpa.containsKey(target)){
                            player.sendMessage(Citybuild.getPrefix() + "§7Bitte warte einen Moment, der Spieler hat bereits eine TPA Anfrage bekommen!");
                        }
                    }else{
                        sender.sendMessage(Citybuild.getPrefix() + "§cDer Spieler ist nicht online!");
                    }
                }
            }else{
                player.sendMessage(Citybuild.getPrefix() + "§cNutze /tpa <Spieler>!");
            }
        }else{
            sender.sendMessage("Du bist die Konsole!");
        }
        return false;
    }
}
