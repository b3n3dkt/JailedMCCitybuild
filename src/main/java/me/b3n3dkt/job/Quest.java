package me.b3n3dkt.job;

import me.b3n3dkt.utils.FileBuilder;
import me.b3n3dkt.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Quest {
    private FileBuilder fb;
    private String job;
    private Player player;

    public Quest(String job, Player player){
        this.job = job;
        this.player = player;
        this.fb = new FileBuilder("plugins//Citybuild//Quest", job + ".yml");
    }

    public void createNewQuestConfig(){
        this.fb.setValue("index", 0);
        this.fb.save();
    }

    public boolean questExists(){ return this.fb.exist();}

    public void setIndex(int amount){
        this.fb.setValue("index", amount);
        this.fb.save();
    }

    public int getIndex(){
        return this.fb.getInt("index");
    }

    public String getMSG(int index){
        return this.fb.getString("quest." + index + ".msg");
    }

    public int getMin(int index){
        return this.fb.getInt("quest." + index + ".min");
    }

    public int getMax(int index){
        return this.fb.getInt("quest." + index + ".max");
    }

    public int getBelohnung(int index){
        return this.fb.getInt("quest." + index + ".belohnung");
    }

    public double getMoneyPrice(int index) {
        double money = Double.valueOf(this.fb.getString("quest." + index + ".money"));
        return money;
    }

    public String getReplacedMSG(int index, int needed, String name){
        String msg = getMSG(index);
        return msg.replace("%number%", ""+needed).replace("%block%", ""+name).replace("&", "§");
    }

    public void getNewQuest(String job){
        String dn = "";
        PlayerData data = new PlayerData(player, player.getUniqueId());
        ItemStack stack = null;
        Random random = new Random();
        int index = random.nextInt(getIndex() + 1 - 1) + 1;
        int min = getMin(index);
        int max = getMax(index);
        int rn = random.nextInt(max + 1 - min) + min;
        int belohnung = (int) Math.round((rn * getBelohnung(index))/1.5);
        String msg = getMSG(index);
        if(job.equalsIgnoreCase("holzfäller")){
            int randomNum = random.nextInt(6 + 1 - 1) + 1;
            dn = "Holzstämme";
            if(msg.contains("%block%")){
                if(randomNum == 1){
                    dn = "Eichenholzstämme";
                    stack = new ItemStack(Material.OAK_LOG, 1, (short) 0);
                }else if(randomNum == 2){
                    dn = "Fichtenholzstämme";
                    stack = new ItemStack(Material.SPRUCE_LOG, 1, (short) 1);
                }else if(randomNum == 3){
                    dn = "Birkenholzstämme";
                    stack = new ItemStack(Material.BIRCH_LOG, 1, (short) 2);
                }else if(randomNum == 4){
                    dn = "Jungleholzstämme";
                    stack = new ItemStack(Material.JUNGLE_LOG, 1, (short) 3);
                }else if(randomNum == 5){
                    dn = "Acacienholzstämme";
                    stack = new ItemStack(Material.ACACIA_LOG, 1, (short) 0);
                }else if(randomNum == 6){
                    dn = "Schwarzeichenholzstämme";
                    stack = new ItemStack(Material.DARK_OAK_LOG, 1, (short) 1);
                }
            }else{
                dn = "Holzstämme";
                stack = null;
            }
        }else if(job.equalsIgnoreCase("fischer")){
            int randomNum = random.nextInt(4 + 1 - 1) + 1;
            dn = "Fische";
            if(msg.contains("%block%")){
                if(randomNum == 1){
                    dn = "Kabeljau";
                    stack = new ItemStack(Material.LEGACY_RAW_FISH);
                }else if(randomNum == 2){
                    dn = "Lachs";
                    stack = new ItemStack(Material.SALMON, 1, (short) 1);
                }else if(randomNum == 3){
                    dn = "Tropische Fische";
                    stack = new ItemStack(Material.TROPICAL_FISH, 1, (short) 2);
                }else if(randomNum == 4){
                    dn = "Kugelfische";
                    stack = new ItemStack(Material.PUFFERFISH, 1, (short) 3);
                }
            }else{
                dn = "Fische";
                stack = null;
            }
        }else if(job.equalsIgnoreCase("miner")){
            int randomNum = random.nextInt(7 + 1 - 1) + 1;
            dn = "Erze";
            if(msg.contains("%block%")){
                if(randomNum == 1){
                    dn = "Golderze";
                    stack = new ItemStack(Material.GOLD_ORE);
                }else if(randomNum == 2){
                    dn = "Eisenerze";
                    stack = new ItemStack(Material.IRON_ORE);
                }else if(randomNum == 3){
                    dn = "Kohleerze";
                    stack = new ItemStack(Material.COAL_ORE);
                }else if(randomNum == 4){
                    dn = "Lapiserze";
                    stack = new ItemStack(Material.LAPIS_ORE);
                }else if(randomNum == 5){
                    dn = "Diamanterze";
                    stack = new ItemStack(Material.DIAMOND_ORE);
                }else if(randomNum == 6){
                    dn = "Redstoneerze";
                    stack = new ItemStack(Material.REDSTONE_ORE);
                }else if(randomNum == 7){
                    dn = "Quartzerze";
                    stack = new ItemStack(Material.NETHER_QUARTZ_ORE);
                }
            }else{
                dn = "Erze";
                stack = null;
            }
        }else if(job.equalsIgnoreCase("farmer")){
            int randomNum = random.nextInt(5 + 1 - 1) + 1;
            dn = "Items";
            if(msg.contains("%block%")){
                if(randomNum == 1){
                    dn = "Weizen";
                    stack = new ItemStack(Material.WHEAT);
                }else if(randomNum == 2){
                    dn = "Kartoffeln";
                    stack = new ItemStack(Material.POTATO);
                }else if(randomNum == 3){
                    dn = "Karotten";
                    stack = new ItemStack(Material.CARROT);
                }else if(randomNum == 4){
                    dn = "Kakao";
                    stack = new ItemStack(Material.LEGACY_INK_SACK, 1, (short) 2);
                }else if(randomNum == 5){
                    dn = "Warzen";
                    stack = new ItemStack(Material.LEGACY_NETHER_WARTS);
                }
            }else{
                dn = "Items";
                stack = null;
            }
        }
        data.setQuest(job, index, belohnung, rn, stack, dn);
    }

    public void addQuest(int index, String msg, int min, int max, int xp){
        this.fb.setValue("quest." + index + ".msg", msg);
        this.fb.setValue("quest." + index + ".min", min);
        this.fb.setValue("quest." + index + ".max", max);
        this.fb.setValue("quest." + index + ".belohnung", xp);
        this.fb.setValue("index", index);
        this.fb.save();
    }

}
