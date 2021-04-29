package me.b3n3dkt.utils;

import me.b3n3dkt.Citybuild;;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.UUID;

public class ClearLag {
   private int seconds = 360*3;

   public static void setActionBar(Player player, String message) {
      player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
   }

   public void startClearlag() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(Citybuild.getMain(), new Runnable() {
         public void run() {
            Player all;
            Iterator var2;
            label106:
            switch(ClearLag.this.seconds) {
            case 0:
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clearlag");
               ClearLag.this.seconds = 360;
               break;
            case 1:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(true) {
                  if (!var2.hasNext()) {
                     break label106;
                  }

                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §beiner §7Sekunde gelöscht§8.");
               }
            case 2:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(true) {
                  if (!var2.hasNext()) {
                     break label106;
                  }

                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §b2 §7Sekunden gelöscht§8.");
               }
            case 3:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(true) {
                  if (!var2.hasNext()) {
                     break label106;
                  }

                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §b3 §7Sekunden gelöscht§8.");
               }
            case 4:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(true) {
                  if (!var2.hasNext()) {
                     break label106;
                  }

                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §b4 §7Sekunden gelöscht§8.");
               }
            case 5:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(true) {
                  if (!var2.hasNext()) {
                     break label106;
                  }

                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §b5 §7Sekunden gelöscht§8.");
               }
            case 10:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(true) {
                  if (!var2.hasNext()) {
                     break label106;
                  }

                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §b10 §7Sekunden gelöscht§8.");
               }
            case 60:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(true) {
                  if (!var2.hasNext()) {
                     break label106;
                  }

                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §beiner §7Minute gelöscht§8.");
               }
            case 300:
               var2 = Bukkit.getOnlinePlayers().iterator();

               while(var2.hasNext()) {
                  all = (Player)var2.next();
                  ClearLag.setActionBar(all, "§7Alle bodenliegenden Items werden in §b5 §7Minuten gelöscht§8.");
               }
            }

            ClearLag var10000 = ClearLag.this;
            var10000.seconds = var10000.seconds - 1;
         }
      }, 0L, 20L);
   }
}
