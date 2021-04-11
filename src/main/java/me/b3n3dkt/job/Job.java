package me.b3n3dkt.job;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.ItemBuilder;
import me.b3n3dkt.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Jobs:
-Holzfäller (VErschiedene Holzsorten)
-Fischer (Fische)
-Miner (Eisen, Kohle, Redstone, etc.)
-Farmer (Weizen, Karotten etc.)
-Forscher (in der Welt rumlaufen -> Geld bekommen und random items finden)
-Techniker (Redstone sachen plazieren)
-Schmied
-Arbeitslos*/
public class Job implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0){
                PlayerData data = new PlayerData(player);
                if(data.getJob().equalsIgnoreCase("arbeitslos")) {

                    Inventory inv = Bukkit.createInventory(null, 3 * 9, "§3Job-Auswählen");
                    ItemStack holzfäller = (new ItemBuilder(Material.IRON_AXE).setAmount(1).setDisplayName("§7Holzfäller")).build();
                    ItemStack fischer = (new ItemBuilder(Material.FISHING_ROD).setAmount(1).setDisplayName("§7Fischer")).build();
                    ItemStack miner = (new ItemBuilder(Material.IRON_PICKAXE).setAmount(1).setDisplayName("§7Miner")).build();
                    ItemStack farmer = (new ItemBuilder(Material.IRON_HOE).setAmount(1).setDisplayName("§7Farmer")).build();
                    ItemStack forscher = (new ItemBuilder(Material.EMPTY_MAP).setAmount(1).setDisplayName("§7Forscher").setLore(Arrays.asList("§cSoon"))).build();
                    ItemStack techniker = (new ItemBuilder(Material.REDSTONE).setAmount(1).setDisplayName("§7Techniker").setLore(Arrays.asList("§cSoon"))).build();
                    ItemStack schmied = (new ItemBuilder(Material.ANVIL).setAmount(1).setDisplayName("§7Schmied").setLore(Arrays.asList("§cSoon"))).build();
                    ItemStack arbeitslos = (new ItemBuilder(Material.BARRIER).setAmount(1).setDisplayName("§7Arbeitslos")).build();
                    ItemStack glas = (new ItemBuilder(Material.STAINED_GLASS_PANE).setAmount(1).setDisplayName("§7")).build();
                    player.openInventory(inv);
                    for (int i = 0; i < 3 * 9; i++) {
                        inv.setItem(i, glas);
                    }
                    inv.setItem(10, holzfäller);
                    inv.setItem(11, fischer);
                    inv.setItem(12, miner);
                    inv.setItem(13, farmer);
                    inv.setItem(14, forscher);
                    inv.setItem(15, techniker);
                    inv.setItem(16, schmied);
                    inv.setItem(22, arbeitslos);
                    player.updateInventory();
                }else{
                    Inventory inv = Bukkit.createInventory(null, 3*9, "§3" + data.getJobName());
                    String xplore = "§8[";
                    String questlore = "§8[";
                    Quest quest = new Quest(data.getJob(), player);
                    List<String> statslore = new ArrayList<>();
                    int total, score, diff, total2, score2, diff2;
                    float percentage, percentage2;
                    total = data.getXPToNextLevel(data.getJob());
                    total2 = data.getNeededBlocks(data.getJob());
                    score = data.getXP(data.getJob());
                    score2 = data.getAbgebauteBlöcke(data.getJob());
                    percentage = (score * 100/total);
                    percentage2 = (score2 * 100/total2);
                    diff = (int) (100-percentage);
                    diff2 = (int) (100-percentage2);
                    for(int i = 0;i<percentage;i++){
                        xplore = xplore + "§a|";
                    }
                    for(int i = 0;i<diff;i++){
                        xplore = xplore + "§7|";
                    }
                    xplore = xplore + "§8] §3" + percentage + "§7%";
                    for(int i = 0;i<percentage2;i++){
                        questlore =  questlore + "§a|";
                    }
                    for(int i = 0;i<diff2;i++){
                        questlore = questlore + "§7|";
                    }
                    questlore = questlore + "§8] §3" + percentage2 + "§7%";

                    ItemStack stack = new ItemStack(Material.BARRIER);
                    if(data.getJob().equalsIgnoreCase("holzfäller")){
                        int allblocks = data.getHolzfällerBlock("oak") + data.getHolzfällerBlock("spruce") + data.getHolzfällerBlock("birch") + data.getHolzfällerBlock("jungle") + data.getHolzfällerBlock("acacia") + data.getHolzfällerBlock("darkaok");
                        stack = (new ItemBuilder(Material.IRON_AXE).setAmount(1).setDisplayName("§7Holzfäller").setLore(Arrays.asList("§7* Klicke um zum Shop zu gelangen *"))).build();
                        statslore.add("§7Oak Wood: §3" + data.getHolzfällerBlock("oak"));
                        statslore.add("§7Spruce Wood: §3" + data.getHolzfällerBlock("spruce"));
                        statslore.add("§7Birch Wood: §3" + data.getHolzfällerBlock("birch"));
                        statslore.add("§7Jungle Wood: §3" + data.getHolzfällerBlock("jungle"));
                        statslore.add("§7Acacia Wood: §3" + data.getHolzfällerBlock("acacia"));
                        statslore.add("§7Darkoak Wood: §3" + data.getHolzfällerBlock("darkoak"));
                        statslore.add("§7");
                        statslore.add("§3" + allblocks + " §7Holz hast du insgesammt gehackt!");
                    }else if(data.getJob().equalsIgnoreCase("fischer")){
                        int allfish = data.getFischerItems("fish") + data.getFischerItems("salmon") + data.getFischerItems("clownfish") + data.getFischerItems("pufferfish");
                        stack = (new ItemBuilder(Material.FISHING_ROD).setAmount(1).setDisplayName("§7Fischer").setLore(Arrays.asList("§7* Klicke um zum Shop zu gelangen *"))).build();
                        statslore.add("§7Fish: §3" + data.getFischerItems("fish"));
                        statslore.add("§7Salmon: §3" + data.getFischerItems("salmon"));
                        statslore.add("§7Clownfish: §3" + data.getFischerItems("clownfish"));
                        statslore.add("§7Pufferfish: §3" + data.getFischerItems("pufferfish"));
                        statslore.add("§7");
                        statslore.add("§3" + allfish + " §7Fisch(e) hast du insgesammt gefangen!");
                    }else if(data.getJob().equalsIgnoreCase("miner")){
                        int allblocks = data.getMinerOres("gold") + data.getMinerOres("iron") + data.getMinerOres("coal") + data.getMinerOres("lapis") + data.getMinerOres("diamond") + data.getMinerOres("redstone") + data.getMinerOres("emerald") + data.getMinerOres("quartz");
                        stack = (new ItemBuilder(Material.IRON_PICKAXE).setAmount(1).setDisplayName("§7Miner").setLore(Arrays.asList("§7* Klicke um zum Shop zu gelangen *"))).build();
                        statslore.add("§7Gold Ore: §3" + data.getMinerOres("gold"));
                        statslore.add("§7Iron Ore: §3" + data.getMinerOres("iron"));
                        statslore.add("§7Coal Ore: §3" + data.getMinerOres("coal"));
                        statslore.add("§7Lapis Ore: §3" + data.getMinerOres("lapis"));
                        statslore.add("§7Diamond Ore: §3" + data.getMinerOres("diamond"));
                        statslore.add("§7Redstone Ore: §3" + data.getMinerOres("redstone"));
                        statslore.add("§7Emerald Ore: §3" + data.getMinerOres("emerald"));
                        statslore.add("§7Quartz Ore: §3" + data.getMinerOres("quartz"));
                        statslore.add("§7");
                        statslore.add("§3" + allblocks + " §7Erz(e) hast du insgesammt abgebaut!");
                    }else if(data.getJob().equalsIgnoreCase("farmer")){
                        int allcrops = data.getFarmerItems("wheat") + data.getFarmerItems("potato") + data.getFarmerItems("carrot") + data.getFarmerItems("cacoa") + data.getFarmerItems("wart");
                        stack = (new ItemBuilder(Material.IRON_HOE).setAmount(1).setDisplayName("§7Farmer").setLore(Arrays.asList("§7* Klicke um zum Shop zu gelangen *"))).build();
                        statslore.add("§7Wheat: §3" + data.getFarmerItems("wheat"));
                        statslore.add("§7Potato: §3" + data.getFarmerItems("potato"));
                        statslore.add("§7Carrot: §3" + data.getFarmerItems("carrot"));
                        statslore.add("§7Cacoa: §3" + data.getFarmerItems("cacoa"));
                        statslore.add("§7");
                        statslore.add("§3" + allcrops + " §7Item(s) hast du insgesammt geerntet!");
                    }
                    ItemStack challanges = (new ItemBuilder(Material.BOOK).setAmount(1).setDisplayName("§7Challanges").setLore(Arrays.asList(questlore, "§5",quest.getReplacedMSG(data.getQuestID(data.getJob()), data.getNeededBlocks(data.getJob()), data.getQuestDN(data.getJob())), "§7Abgebaut: §a"+data.getAbgebauteBlöcke(data.getJob()) + "§7/§c" + data.getNeededBlocks(data.getJob()), "§7Du bekommst §a" + data.getQuestXP(data.getJob()) + "XP§7!")).build());
                    ItemStack stats = (new ItemBuilder(Material.EMPTY_MAP).setAmount(1).setDisplayName("§7Abgebaute Blöcke").setLore(statslore)).build();
                    ItemStack xp = (new ItemBuilder(Material.EMPTY_MAP).setAmount(1).setDisplayName("§7Dein XP Fortschritt").setLore(Arrays.asList(xplore, "§e", "§7Dein Aktuelles Level: §3" + data.getLevel(data.getJob()), "§7Deine Aktuelle XP: §a" + data.getXP(data.getJob()) + "§7/§c" + data.getXPToNextLevel(data.getJob())))).build();
                    ItemStack arbeitslos = (new ItemBuilder(Material.BARRIER).setAmount(1).setDisplayName("§7Kündige deinen Job").setLore(Arrays.asList("§7* Klicke zum Kündigen *"))).build();
                    ItemStack glas = (new ItemBuilder(Material.STAINED_GLASS_PANE).setAmount(1).setDisplayName("§7")).build();
                    player.openInventory(inv);
                    for (int i = 0; i < 3 * 9; i++) {
                        inv.setItem(i, glas);
                    }

                    inv.setItem(10, stack);
                    inv.setItem(12, stats);
                    inv.setItem(13, challanges);
                    inv.setItem(14, xp);
                    inv.setItem(16, arbeitslos);
                    player.updateInventory();
                }
            }else{
                player.sendMessage(Citybuild.getPrefix() + "§cNutze /job!");
            }
        }
        return false;
    }
}
