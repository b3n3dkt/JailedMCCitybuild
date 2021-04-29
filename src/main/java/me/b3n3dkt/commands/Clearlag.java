package me.b3n3dkt.commands;

import java.util.Iterator;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.ClearLag;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class Clearlag implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jailedmc.command.clearlag")) {
            if (args.length != 0) {
                sender.sendMessage(Citybuild.getPrefix() + "§cNutze bitte /clearlag!");
            } else if (args.length == 0) {
                Iterator worlds = Bukkit.getWorlds().iterator();

                label46:
                while(worlds.hasNext()) {
                    World world = (World)worlds.next();
                    Iterator entities = world.getEntities().iterator();

                    while(true) {
                        Entity entity;
                        do {
                            if (!entities.hasNext()) {
                                continue label46;
                            }

                            entity = (Entity)entities.next();
                        } while(!(entity instanceof Item) && !(entity instanceof Animals) && !(entity instanceof Monster));

                        entity.remove();
                    }
                }
                for(Player all : Bukkit.getOnlinePlayers()){
                    me.b3n3dkt.utils.ClearLag.setActionBar(all, " §c§l➤ §bAlle bodenliegenden Items und alle Entities wurden gelöscht!");
                    all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
                }
            }
        } else {
            sender.sendMessage(Citybuild.getNoperm());
        }

        return false;
    }
}
