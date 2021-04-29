package me.b3n3dkt.listener;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.commands.Event;
import me.b3n3dkt.commands.GlobalMute;
import me.b3n3dkt.utils.PlayerData;
import me.b3n3dkt.utils.Rang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Citybuild.activeVote != null) {
            if (p.hasPermission("jailedmc.command.votekick.bypass")) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
                p.sendMessage(Citybuild.getPrefix() + "§7Du kannst momentan nicht schreiben, weil ein VoteKick aktiv ist!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 1.0F, 1.0F);
            }
        } else if (GlobalMute.globalmute) {
            if (!p.hasPermission("jailedmc.command.globalmute.bypass")) {
                e.setCancelled(true);
                p.sendMessage(Citybuild.getPrefix() + "§7Du kannst momentan nicht schreiben, weil der Chat gemuted ist!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 1.0F, 1.0F);
            }
        } else if (Event.event && !p.hasPermission("jailedmc.command.globalmute.bypass")) {
            e.setCancelled(true);
            p.sendMessage(Citybuild.getPrefix() + "§cDu kannst momentan nicht schreiben, weil in Event aktiv ist!");
            p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 1.0F, 1.0F);
        }

        if (e.getMessage().equalsIgnoreCase("/pl") || e.getMessage().equalsIgnoreCase("/plugins") || e.getMessage().equalsIgnoreCase("/bukkit") || e.getMessage().equalsIgnoreCase("/?") || e.getMessage().equalsIgnoreCase("/help") || e.getMessage().equalsIgnoreCase("/bukkit:help") || e.getMessage().startsWith("//") || e.getMessage().equalsIgnoreCase("/hawk")) {
            if (!p.isOp()) {
                e.setCancelled(true);
                p.sendMessage(Citybuild.getNoperm());
            } else {
                e.setCancelled(false);
            }
        }
        if(e.getPlayer().hasPermission("jailedmc.chat.color.*")){
            e.setFormat(Rang.getSuffix(p.getUniqueId().toString()) + " §8× §7" + p.getName() + " §8→ §7" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }else{
            e.setFormat(Rang.getSuffix(p.getUniqueId().toString()) + " §8× §7" + p.getName() + " §8→ §7" + e.getMessage());
        }

    }
}
