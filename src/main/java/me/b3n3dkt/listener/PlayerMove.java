package me.b3n3dkt.listener;

import me.b3n3dkt.Citybuild;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class PlayerMove implements Listener {
   public static ArrayList<Player> spawn = new ArrayList();

   @EventHandler
   public void onHandle(PlayerMoveEvent e) {
      Player p = e.getPlayer();
      if (spawn.contains(p)) {
         spawn.remove(p);
         p.sendMessage(Citybuild.getPrefix() + "§cDer teleportier vorgang wurde abgebrochen!");
      }

   }
}
