package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.Score;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Rang implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("jailedmc.command.rang")){
            if(args.length == 3){
                OfflinePlayer offlinetarget = Bukkit.getOfflinePlayer(args[1]);
                Player t = Bukkit.getPlayer(args[1]);
                Score sb = new Score(t);
                String rang = args[2];
                String uuid = offlinetarget.getUniqueId().toString();
                if(args[0].equalsIgnoreCase("set")){
                    if(MySQL.isRegistered(offlinetarget.getUniqueId().toString())){
                        if(rang.equalsIgnoreCase("spieler")){
                            setRank((Player) offlinetarget, "spieler");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§7Spieler§8'§7.");
                            MySQL.setRang(uuid, "spieler");
                        }else if(rang.equalsIgnoreCase("mitglied")){
                            setRank((Player) offlinetarget, "mitglied");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§bMitglied§8'§7.");
                            MySQL.setRang(uuid, "mitglied");
                        }else if(rang.equalsIgnoreCase("premium")){
                            setRank((Player) offlinetarget, "premium");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§6Premium§8'§7.");
                            MySQL.setRang(uuid, "premium");
                        }else if(rang.equalsIgnoreCase("obsidian")){
                            setRank((Player) offlinetarget, "obsidian");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§dObsidian§8'§7.");
                            MySQL.setRang(uuid, "obsidian");
                        }else if(rang.equalsIgnoreCase("titan")){
                            setRank((Player) offlinetarget, "titan");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§9Titan§8'§7.");
                            MySQL.setRang(uuid, "titan");
                        }else if(rang.equalsIgnoreCase("bedrock")){
                            setRank((Player) offlinetarget, "bedrock");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§5Bedrock§8'§7.");
                            MySQL.setRang(uuid, "bedrock");
                        }else if(rang.equalsIgnoreCase("legende")){
                            setRank((Player) offlinetarget, "legende");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§c§lLegende§8'§7.");
                            MySQL.setRang(uuid, "legende");
                        }else if(rang.equalsIgnoreCase("phoenix")){
                            setRank((Player) offlinetarget, "phoenix");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§e§lPhoenix§8'§7.");
                            MySQL.setRang(uuid, "phoenix");
                        }else if(rang.equalsIgnoreCase("vip")){
                            setRank((Player) offlinetarget, "vip");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§3VIP§8'§7.");
                            MySQL.setRang(uuid, "vip");
                        }else if(rang.equalsIgnoreCase("vip+")){
                            setRank((Player) offlinetarget, "vip+");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§3§lVIP+§8'§7.");
                            MySQL.setRang(uuid, "vip+");
                        }else if(rang.equalsIgnoreCase("elite")){
                            setRank((Player) offlinetarget, "elite");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§b§lElite§8'§7.");
                            MySQL.setRang(uuid, "elite");
                        }else if(rang.equalsIgnoreCase("donator")){
                            setRank((Player) offlinetarget, "donator");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§eDonator§8'§7.");
                            MySQL.setRang(uuid, "donator");
                        }else if(rang.equalsIgnoreCase("freund")){
                            setRank((Player) offlinetarget, "freund");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§9Freund/in§8'§7.");
                            MySQL.setRang(uuid, "freund");
                        }else if(rang.equalsIgnoreCase("youtuber")){
                            setRank((Player) offlinetarget, "youtuber");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§4§lYou§f§lTuber§8'§7.");
                            MySQL.setRang(uuid, "youtuber");
                        }else if(rang.equalsIgnoreCase("streamer")){
                            setRank((Player) offlinetarget, "streamer");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§d§lStreamer§8'§7.");
                            MySQL.setRang(uuid, "streamer");
                        }else if(rang.equalsIgnoreCase("partner")){
                            setRank((Player) offlinetarget, "partner");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§ePartner§8'§7.");
                            MySQL.setRang(uuid, "partner");
                        }else if(rang.equalsIgnoreCase("azubi")){
                            setRank((Player) offlinetarget, "azubi");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§1Azubi§8'§7.");
                            MySQL.setRang(uuid, "azubi");
                        }else if(rang.equalsIgnoreCase("supporter")){
                            setRank((Player) offlinetarget, "supporter");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§1Supporter§8'§7.");
                            MySQL.setRang(uuid, "supporter");
                        }else if(rang.equalsIgnoreCase("builder")){
                            setRank((Player) offlinetarget, "builder");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§2Builder§8'§7.");
                            MySQL.setRang(uuid, "builder");
                        }else if(rang.equalsIgnoreCase("testmoderator")){
                            setRank((Player) offlinetarget, "testmoderator");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§cTestModerator§8'§7.");
                            MySQL.setRang(uuid, "testmoderator");
                        }else if(rang.equalsIgnoreCase("moderator")){
                            setRank((Player) offlinetarget, "moderator");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§cModerator§8'§7.");
                            MySQL.setRang(uuid, "moderator");
                        }else if(rang.equalsIgnoreCase("testdeveloper")){
                            setRank((Player) offlinetarget, "testdeveloper");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§bTestDeveloper§8'§7.");
                            MySQL.setRang(uuid, "testdeveloper");
                        }else if(rang.equalsIgnoreCase("developer")){
                            setRank((Player) offlinetarget, "developer");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§bDeveloper§8'§7.");
                            MySQL.setRang(uuid, "developer");
                        }else if(rang.equalsIgnoreCase("testadministrator")){
                            setRank((Player) offlinetarget, "testadministrator");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§cTestAdministrator§8'§7.");
                            MySQL.setRang(uuid, "testadministrator");
                        }else if(rang.equalsIgnoreCase("administrator")){
                            setRank((Player) offlinetarget, "administrator");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§cAdministrator§8'§7.");
                            MySQL.setRang(uuid, "administrator");
                        }else if(rang.equalsIgnoreCase("owner")){
                            setRank((Player) offlinetarget, "owner");
                            sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat nun den Rang §8'§4Owner§8'§7.");
                            MySQL.setRang(uuid, "owner");
                        }else{
                            sender.sendMessage(Citybuild.getPrefix() + "§cDer Rang existiert nicht!");
                        }
                        if(t != null) {
                            sb.update();
                            sb.updatetab();
                        }
                    }else{
                        sender.sendMessage(Citybuild.getPrefix() + "§cDer Spieler existiert nicht!");
                    }
                }else{
                    sender.sendMessage(Citybuild.getPrefix() + "§cNutze: /rang <set/info> <Spieler> <Rang>");
                }
            }else if(args.length == 2){
                OfflinePlayer offlinetarget = Bukkit.getOfflinePlayer(args[1]);
                if(args[0].equalsIgnoreCase("info")){
                    if(MySQL.isRegistered(offlinetarget.getUniqueId().toString())){
                        sender.sendMessage(Citybuild.getPrefix() + "§7Der Spieler §8'§e" + offlinetarget.getName() + "§8' §7hat den Rang §8'§e" + MySQL.getRang(offlinetarget.getUniqueId().toString()) + "§8'§7.");
                    }else{
                        sender.sendMessage(Citybuild.getPrefix() + "§cDer Spieler existiert nicht!");
                    }
                }else{
                    sender.sendMessage(Citybuild.getPrefix() + "§cNutze: /rang <set/info> <Spieler> <Rang>");
                }
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("info")){
                    sender.sendMessage("§8<-----[§eRänge§8]----->");
                    sender.sendMessage("§7-Spieler");
                    sender.sendMessage("§7-§bMitglied");
                    sender.sendMessage("§7-§6Premium");
                    sender.sendMessage("§7-§dObsidian");
                    sender.sendMessage("§7-§9Titan");
                    sender.sendMessage("§7-§5Bedrock");
                    sender.sendMessage("§7-§c§lLegende");
                    sender.sendMessage("§7-§e§lPhoenix");
                    sender.sendMessage("§7-§3VIP");
                    sender.sendMessage("§7-§e§lVIP+");
                    sender.sendMessage("§7-§b§lElite");
                    sender.sendMessage("§7-§eDonator");
                    sender.sendMessage("§7-§9Freund");
                    sender.sendMessage("§7-§4§lYou§f§lTuber");
                    sender.sendMessage("§7-§d§lStreamer");
                    sender.sendMessage("§7-§ePartner");
                    sender.sendMessage("§7-§1Azubi");
                    sender.sendMessage("§7-§1Supporter");
                    sender.sendMessage("§7-§2Builder");
                    sender.sendMessage("§7-§cTestModerator");
                    sender.sendMessage("§7-§cModerator");
                    sender.sendMessage("§7-§bTestDeveloper");
                    sender.sendMessage("§7-§bDeveloper");
                    sender.sendMessage("§7-§cTestAdministrator");
                    sender.sendMessage("§7-§cAdministrator");
                    sender.sendMessage("§7-§4Owner");
                }
            }else{
                sender.sendMessage(Citybuild.getPrefix() + "§cNutze: /rang <set/info> <Spieler> <Rang>");
            }
        }else{
            sender.sendMessage(Citybuild.getNoperm());
        }
        return false;
    }

    public void setRank(Player p, String group){
        String uuid = p.getUniqueId().toString();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " group remove " + MySQL.getRang(uuid));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " group add " + group);
    }
}