package me.b3n3dkt.utils;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class Combat {
   public static HashMap<Player, BukkitTask> task = new HashMap();
   public static HashMap<Player, Player> lastHit = new HashMap<>();

   public static void tagPlayer(final Player player, Integer duration) {
      if (!isTagged(player)) {
         BukkitTask id = Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
            public void run() {
               Combat.task.remove(player);
            }
         }, (long)duration);
         task.put(player, id);
      } else if (isTagged(player)) {
         untagPlayer(player);
         tagPlayer(player, duration);
      }

   }

   public static void untagPlayer(Player player) {
      if (isTagged(player)) {
         int id = ((BukkitTask)task.get(player)).getTaskId();
         Bukkit.getScheduler().cancelTask(id);
         task.remove(player);
      }

   }

   public static boolean isTagged(Player player) {
      return task.containsKey(player);
   }
}
