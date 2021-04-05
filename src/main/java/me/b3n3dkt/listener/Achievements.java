package me.b3n3dkt.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class Achievements implements Listener {
   @EventHandler
   public void onAchieve(PlayerAchievementAwardedEvent e) {
      e.setCancelled(true);
   }
}
