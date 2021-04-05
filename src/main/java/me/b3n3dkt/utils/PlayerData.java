package me.b3n3dkt.utils;

import java.util.UUID;

public class PlayerData {
   private UUID uuid = null;
   private FileBuilder fb = null;

   public PlayerData(UUID uuid) {
      this.uuid = uuid;
      this.fb = new FileBuilder("plugins//SkyPvP//PlayerData//", uuid.toString() + ".yml");
   }

   public boolean exist() {
      return this.fb.exist();
   }

   public void newData(UUID uuid, String name, String ip) {
      this.fb.setValue("player.data.vanish", "false");
      this.fb.save();
   }

   public void setVanished() {
      this.fb.setValue("player.data.vanish", true);
      this.fb.save();
   }

   public void setUnVanished() {
      this.fb.setValue("player.data.vanish", false);
      this.fb.save();
   }

   public boolean getVanish() {
      return this.fb.getBoolean("player.data.vanish");
   }

}
