package me.b3n3dkt.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.job.Quest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerData {
   private UUID uuid = null;
   private FileBuilder fb = null;
   private Player player;

   public PlayerData(Player player) {
      this.player = player;
      this.uuid = player.getUniqueId();
      this.fb = new FileBuilder("plugins//Citybuild//PlayerData//", uuid.toString() + ".yml");
   }

   public boolean exist() {
      return this.fb.exist();
   }

   public void newData() {
      this.fb.setValue("player.data.vanish", "false");
      this.fb.setValue("player.data.job.currentjob", "arbeitslos");
      this.fb.setValue("player.data.job.holzfäller.level", 0);//Holzfäller
      this.fb.setValue("player.data.job.holzfäller.xp", 0);
      this.fb.setValue("player.data.job.holzfäller.xpToNextLevel", 200);
      this.fb.setValue("player.data.job.holzfäller.blocks.oak", 0);
      this.fb.setValue("player.data.job.holzfäller.blocks.spruce", 0);
      this.fb.setValue("player.data.job.holzfäller.blocks.birch", 0);
      this.fb.setValue("player.data.job.holzfäller.blocks.jungle", 0);
      this.fb.setValue("player.data.job.holzfäller.blocks.acacia", 0);
      this.fb.setValue("player.data.job.holzfäller.blocks.darkoak", 0);
      this.fb.setValue("player.data.job.holzfäller.currentQuest.ID", 1);
      this.fb.setValue("player.data.job.holzfäller.currentQuest.xp", 345);
      this.fb.setValue("player.data.job.holzfäller.currentQuest.abgebaut", 0);
      this.fb.setValue("player.data.job.holzfäller.currentQuest.needed", 20);
      this.fb.setValue("player.data.job.holzfäller.currentQuest.dn", "Holzstämme");
      this.fb.setValue("player.data.job.fischer.level", 0);//Fischer
      this.fb.setValue("player.data.job.fischer.xp", 0);
      this.fb.setValue("player.data.job.fischer.xpToNextLevel", 200);
      this.fb.setValue("player.data.job.fischer.items.fish", 0);
      this.fb.setValue("player.data.job.fischer.items.salmon", 0);
      this.fb.setValue("player.data.job.fischer.items.clownfish", 0);
      this.fb.setValue("player.data.job.fischer.items.pufferfish", 0);
      this.fb.setValue("player.data.job.fischer.currentQuest.ID", 1);
      this.fb.setValue("player.data.job.fischer.currentQuest.xp", 345);
      this.fb.setValue("player.data.job.fischer.currentQuest.abgebaut", 0);
      this.fb.setValue("player.data.job.fischer.currentQuest.needed", 20);
      this.fb.setValue("player.data.job.fischer.currentQuest.dn", "Fische");
      this.fb.setValue("player.data.job.miner.level", 0);//Miner
      this.fb.setValue("player.data.job.miner.xp", 0);
      this.fb.setValue("player.data.job.miner.xpToNextLevel", 200);
      this.fb.setValue("player.data.job.miner.ores.gold", 0);
      this.fb.setValue("player.data.job.miner.ores.iron", 0);
      this.fb.setValue("player.data.job.miner.ores.coal", 0);
      this.fb.setValue("player.data.job.miner.ores.lapis", 0);
      this.fb.setValue("player.data.job.miner.ores.diamond", 0);
      this.fb.setValue("player.data.job.miner.ores.redstone", 0);
      this.fb.setValue("player.data.job.miner.ores.emerald", 0);
      this.fb.setValue("player.data.job.miner.ores.quartz", 0);
      this.fb.setValue("player.data.job.miner.currentQuest.ID", 1);
      this.fb.setValue("player.data.job.miner.currentQuest.xp", 345);
      this.fb.setValue("player.data.job.miner.currentQuest.abgebaut", 0);
      this.fb.setValue("player.data.job.miner.currentQuest.needed", 20);
      this.fb.setValue("player.data.job.miner.currentQuest.dn", "Erze");
      this.fb.setValue("player.data.job.farmer.level", 0);//Farmer
      this.fb.setValue("player.data.job.farmer.xp", 0);
      this.fb.setValue("player.data.job.farmer.xpToNextLevel", 200);
      this.fb.setValue("player.data.job.farmer.items.wheat", 0 );
      this.fb.setValue("player.data.job.farmer.items.potato", 0 );
      this.fb.setValue("player.data.job.farmer.items.carrot", 0 );
      this.fb.setValue("player.data.job.farmer.items.melon", 0 );
      this.fb.setValue("player.data.job.farmer.items.pumpkin", 0 );
      this.fb.setValue("player.data.job.farmer.items.cacoa", 0 );
      this.fb.setValue("player.data.job.farmer.items.wart", 0 );
      this.fb.setValue("player.data.job.farmer.currentQuest.ID", 1);
      this.fb.setValue("player.data.job.farmer.currentQuest.xp", 345);
      this.fb.setValue("player.data.job.farmer.currentQuest.abgebaut", 0);
      this.fb.setValue("player.data.job.farmer.currentQuest.needed", 20);
      this.fb.setValue("player.data.job.farmer.currentQuest.dn", "Items");
      this.fb.setValue("player.data.job.forscher.level", 0);//Forscher
      this.fb.setValue("player.data.job.techniker.level", 0);//Techniker
      this.fb.setValue("player.data.job.schmied.level", 0);//Schmied
      this.fb.save();
   }

   public void setQuest(String job, int id, int xp, int max, ItemStack mat, String dn){
      this.fb.setValue("player.data.job." + job + ".currentQuest.ID", id);
      this.fb.setValue("player.data.job." + job + ".currentQuest.block", mat);
      this.fb.setValue("player.data.job." + job + ".currentQuest.xp", xp);
      this.fb.setValue("player.data.job." + job + ".currentQuest.needed", max);
      this.fb.setValue("player.data.job." + job + ".currentQuest.dn", dn);
      this.fb.save();
   }

   public void addBlock(String job, int amount) {
      if ((getAbgebauteBlöcke(job) + amount) >= getNeededBlocks(job)) {
         Quest quest = new Quest(job, player);
         int xp = getQuestXP(job);
         Random random = new Random();
         int rn = random.nextInt(quest.getIndex() + 1 - 1) + 1;
         this.fb.setValue("player.data.job." + job + ".currentQuest.ID", 0);
         this.fb.setValue("player.data.job." + job + ".currentQuest.abgebaut", 0);
         this.fb.setValue("player.data.job." + job + ".currentQuest.needed", 0);
         this.fb.setValue("player.data.job." + job + ".currentQuest.dn", "null");
         this.fb.setValue("player.data.job." + job + ".currentQuest.xp", 0);
         this.fb.setValue("player.data.job." + job + ".currentQuest.block", null);
         this.fb.save();
         player.sendMessage(Citybuild.getPrefix() + "§aDu hast eine Quest abgeschlossen und bekommst dafür §8'§3" + xp + "XP§8'§7!");
         player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
         addXP(job, xp);
         Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
            @Override
            public void run() {
               quest.getNewQuest(getJob());
            }
         }, 20);
      } else {
         this.fb.setValue("player.data.job." + job + ".currentQuest.abgebaut", (getAbgebauteBlöcke(job)+amount));
      }
   }

   public String getQuestDN(String job){
      return this.fb.getString("player.data.job." + job + ".currentQuest.dn");
   }

   public int getAbgebauteBlöcke(String job){ return this.fb.getInt("player.data.job." + job + ".currentQuest.abgebaut"); }

   public ItemStack getQuestBlock(String job){ return this.fb.getItemStack("player.data.job." + job + ".currentQuest.block"); }

   public int getNeededBlocks(String job){ return this.fb.getInt("player.data.job." + job + ".currentQuest.needed"); }

   public int getQuestXP(String job){ return this.fb.getInt("player.data.job." + job + ".currentQuest.xp"); }

   public int getQuestID(String job){
      return this.fb.getInt("player.data.job." + job + ".currentQuest.ID");
   }
   
   public void addXP(String job, int amount){
      if((getXP(job)+amount) >= getXPToNextLevel(job)){
         this.fb.setValue("player.data.job." + job + ".level", getLevel(job) + 1);
         this.fb.setValue("player.data.job." + job + ".xp", (getXP(job)+amount) - getXPToNextLevel(job));
         this.fb.setValue("player.data.job." + job + ".xpToNextLevel",  Math.round(getXPToNextLevel(job) * 1.5));
         this.fb.save();
         this.player.sendMessage(Citybuild.getPrefix() + "§aDu bist ein Level in dem Job §8'§3" + getJobName() + "§8' §aaufgestiegen! Du bist nun Level §8'§3" + getLevel(job) + "§8'§a!");
         player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
      }else{
         setXP(job, getXP(job)+amount);
      }
   }

   public int getLevel(String job){
      return this.fb.getInt("player.data.job." + job + ".level");
   }

   public void setXPToNextLevel(String job, int amount){
      this.fb.setValue("player.data.job." + job + ".xpToNextLevel", amount);
      this.fb.save();
   }

   public int getXPToNextLevel(String job){
      return this.fb.getInt("player.data.job." + job + ".xpToNextLevel");
   }

   public void setXP(String job, int amount){
      this.fb.setValue("player.data.job." + job + ".xp", amount);
      this.fb.save();
   }

   public int getXP(String job){
      return this.fb.getInt("player.data.job." + job +".xp");
   }

   public void setFarmerItem(String item, int amount){
      this.fb.setValue("player.data.job.farmer.items." + item, amount);
      this.fb.save();
   }

   public int getFarmerItems(String item){
      return this.fb.getInt("player.data.job.farmer.items." + item);
   }

   public void setMinerOre(String ore, int amount){
      this.fb.setValue("player.data.job.miner.ores." + ore, amount);
      this.fb.save();
   }

   public int getMinerOres(String ore){
      return this.fb.getInt("player.data.job.miner.ores." + ore);
   }

   public void setFischerItem(String item, int amount){
      this.fb.setValue("player.data.job.fischer.items." + item, amount);
      this.fb.save();
   }

   public int getFischerItems(String item){
      return this.fb.getInt("player.data.job.fischer.items." + item);
   }

   public void setHolzfällerBlock(String block, int amount){
      this.fb.setValue("player.data.job.holzfäller.blocks." + block, amount);
      this.fb.save();
   }

   public int getHolzfällerBlock(String block){
      return this.fb.getInt("player.data.job.holzfäller.blocks." + block);
   }

   public void setJob(String job){
      this.fb.setValue("player.data.job.currentjob", job);
      this.fb.save();
   }

   public String getJob(){
      return this.fb.getString("player.data.job.currentjob");
   }

   public void setVanished() {
      this.fb.setValue("player.data.vanish", true);
      this.fb.save();
   }

   public String getJobName(){
      String name = "Arbeitslos";
      if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("arbeitslos")){
         name = "Arbeitslos";
      }else if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("holzfäller")){
         name = "Holzfäller";
      }else if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("fischer")){
         name = "Fischer";
      }else if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("miner")){
         name = "Miner";
      }else if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("farmer")){
         name = "Farmer";
      }else if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("forscher")){
         name = "Forscher";
      }else if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("techniker")){
         name = "Techniker";
      }else if(this.fb.getString("player.data.job.currentjob").equalsIgnoreCase("schmied")){
         name = "Techniker";
      }
      return name;
   }

   public void setUnVanished() {
      this.fb.setValue("player.data.vanish", false);
      this.fb.save();
   }

   public boolean getVanish() {
      return this.fb.getBoolean("player.data.vanish");
   }

}
