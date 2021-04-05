package me.b3n3dkt.listener;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class UnknownCommand implements Listener {
   @EventHandler(
      priority = EventPriority.NORMAL
   )
   public void onUnbekannt(PlayerCommandPreprocessEvent e) {
      if (!e.isCancelled()) {
         Player p = e.getPlayer();
         String msg = e.getMessage().split(" ")[0];
         HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
         if (topic == null) {
            p.sendMessage(Citybuild.getPrefix() + "§cDer Befehl §8'§e" + e.getMessage() + "§8' §cexistiert nicht.");
            e.setCancelled(true);
         }
      }

   }
}
