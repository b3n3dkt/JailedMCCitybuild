package me.b3n3dkt.job;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.PlayerData;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.MaterialData;

public class BreakBlock implements Listener {

    @EventHandler
    public void onHandle(BlockBreakEvent event){
        Player player = (Player) event.getPlayer();
        PlayerData data = new PlayerData(player);
        Material stack = event.getBlock().getType();
        int id = event.getBlock().getData();
        ItemStack item = new ItemStack(stack);
        String dn = data.getQuestDN(data.getJob());
        if(player.getItemInHand().hasItemMeta()){
            if(player.getItemInHand().getItemMeta().hasEnchants() == true){
                if(player.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH) == true){return;}
            }
        }
        /*
        HOLZFÄLLER
        */
        if(data.getJob().equalsIgnoreCase("holzfäller")){
            if(stack == Material.LOG){ //OAK
                if(id == 0){ //OAK
                    if(dn.equalsIgnoreCase("Eichenholzstämme") || dn.equalsIgnoreCase("Holzstämme")){

                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("holzfäller", 25);
                    data.setHolzfällerBlock("oak", data.getHolzfällerBlock("oak") + 1);
                    setActionBar(player, "§7Du hast §8'§325XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
                }else if(id == 3){ //JUNGLE
                    if(dn.equalsIgnoreCase("Jungleholzstämme") || dn.equalsIgnoreCase("Holzstämme")){

                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("holzfäller", 10);
                    data.setHolzfällerBlock("jungle", data.getHolzfällerBlock("jungle") + 1);
                    setActionBar(player, "§7Du hast §8'§310XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
                }else if(id == 1){ //SPRUCE
                    if(dn.equalsIgnoreCase("Fichtenholzstämme") || dn.equalsIgnoreCase("Holzstämme")){

                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("holzfäller", 20);
                    data.setHolzfällerBlock("spruce", data.getHolzfällerBlock("spruce") + 1);
                    setActionBar(player, "§7Du hast §8'§320XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
                }else if(id == 2){ //BIRCH
                    if(dn.equalsIgnoreCase("Birkenholzstämme") || dn.equalsIgnoreCase("Holzstämme")){

                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("holzfäller", 25);
                    data.setHolzfällerBlock("birch", data.getHolzfällerBlock("birch") + 1);
                    setActionBar(player, "§7Du hast §8'§325XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
                }
            }else if(stack == Material.LOG_2){ 
                if(id == 0){//ACACIA
                    if(dn.equalsIgnoreCase("Acacienholzstämme") || dn.equalsIgnoreCase("Holzstämme")){

                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("holzfäller", 50);
                    data.setHolzfällerBlock("acacia", data.getHolzfällerBlock("acacia") + 1);
                    setActionBar(player, "§7Du hast §8'§350XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
                }else if(id == 1){//DARKOAK
                    if(dn.equalsIgnoreCase("Schwarzeichenholzstämme") || dn.equalsIgnoreCase("Holzstämme")){

                        data.addBlock(data.getJob(), 1);
                    }
                    data.addXP("holzfäller", 10);
                    data.setHolzfällerBlock("darkoak", data.getHolzfällerBlock("darkoak") + 1);
                    setActionBar(player, "§7Du hast §8'§310XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
                }
                
            }
            /*
            MINER
            */
        }else if(data.getJob().equalsIgnoreCase("miner")){
            if(stack == Material.GOLD_ORE){ //GOLD
                if(dn.equalsIgnoreCase("Golderze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 50);
                data.setMinerOre("gold", data.getMinerOres("gold") + 1);
                setActionBar(player, "§7Du hast §8'§350XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.IRON_ORE){ //IRON
                if(dn.equalsIgnoreCase("Eisenerze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 20);
                data.setMinerOre("iron", data.getMinerOres("iron") + 1);
                setActionBar(player, "§7Du hast §8'§320XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.COAL_ORE){ //COAL
                if(dn.equalsIgnoreCase("Kohleerze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 5);
                data.setMinerOre("coal", data.getMinerOres("coal") + 1);
                setActionBar(player, "§7Du hast §8'§35XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.LAPIS_ORE){ //LAPIS
                if(dn.equalsIgnoreCase("Lapiserze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 15);
                data.setMinerOre("lapis", data.getMinerOres("lapis") + 1);
                setActionBar(player, "§7Du hast §8'§315XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.DIAMOND_ORE){ //DIAMOND
                if(dn.equalsIgnoreCase("Diamanterze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 75);
                data.setMinerOre("diamond", data.getMinerOres("diamond") + 1);
                setActionBar(player, "§7Du hast §8'§375XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.REDSTONE_ORE){ //REDSTONE
                if(dn.equalsIgnoreCase("Redstoneerze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 15);
                data.setMinerOre("redstone", data.getMinerOres("redstone") + 1);
                setActionBar(player, "§7Du hast §8'§315XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.EMERALD_ORE){ //EMERALD
                if(dn.equalsIgnoreCase("Emeralderze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 150);
                data.setMinerOre("emerald", data.getMinerOres("emerald") + 1);
                setActionBar(player, "§7Du hast §8'§3150XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.QUARTZ_ORE) { //QUARTZ
                if(dn.equalsIgnoreCase("Quartzerze") || dn.equalsIgnoreCase("Erze")){

                    data.addBlock(data.getJob(), 1);
                }
                data.addXP("miner", 25);
                data.setMinerOre("quartz", data.getMinerOres("quartz") + 1);
                setActionBar(player, "§7Du hast §8'§325XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }
            /*
            FARMER
             */
        }else if(data.getJob().equalsIgnoreCase("farmer")){
            if(stack == Material.CROPS){ //WHEAT
                if(dn.equalsIgnoreCase("Weizen") || dn.equalsIgnoreCase("Items")){

                    data.addBlock(data.getJob(), 1);
                }
                if(event.getBlock().getData() != 0x7){return;}
                data.addXP("farmer", 25);
                data.setFarmerItem("wheat", data.getFarmerItems("wheat") + 1);
                setActionBar(player, "§7Du hast §8'§325XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.CARROT){ //CARROT
                if(dn.equalsIgnoreCase("Karotten") || dn.equalsIgnoreCase("Items")){

                    data.addBlock(data.getJob(), 1);
                }
                if(event.getBlock().getData() != 0x7){return;}
                data.addXP("farmer", 25);
                data.setFarmerItem("carrot", data.getFarmerItems("carrot") + 1);
                setActionBar(player, "§7Du hast §8'§325XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.POTATO){ //POTATO
                if(dn.equalsIgnoreCase("Kartoffeln") || dn.equalsIgnoreCase("Items")){

                    data.addBlock(data.getJob(), 1);
                }
                if(event.getBlock().getData() != 0x7){return;}
                data.addXP("farmer", 25);
                data.setFarmerItem("potato", data.getFarmerItems("potato") + 1);
                setActionBar(player, "§7Du hast §8'§325XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.COCOA) {//COCOA
                if(dn.equalsIgnoreCase("Kakao") || dn.equalsIgnoreCase("Items")){

                    data.addBlock(data.getJob(), 1);
                }
                System.out.println("COCOA");
                if(event.getBlock().getData() < 0x8){return;}
                data.addXP("farmer", 25);
                data.setFarmerItem("cacoa", data.getFarmerItems("cacoa") + 1);
                setActionBar(player, "§7Du hast §8'§325XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }else if(stack == Material.NETHER_WARTS){ //WART
                if(dn.equalsIgnoreCase("Warzen") || dn.equalsIgnoreCase("Items")){

                    data.addBlock(data.getJob(), 1);
                }
                if(event.getBlock().getData() != 0x3){return;}
                data.addXP("farmer", 35);
                data.setFarmerItem("wart", data.getFarmerItems("wart") + 1);
                setActionBar(player, "§7Du hast §8'§335XP§8' §7für das Abbauen von §8'§3" + item.getType() + "§8' §7bekommen!");
            }/*else if(stack == Material.PUMPKIN){ //PUMPKIN
                if(isInArea(player.getLocation(), 15, Material.PUMPKIN_STEM)){
                    data.addXP("farmer", 50);
                    data.setFarmerItem("pumpkin", data.getFarmerItems("pumpkin") + 1);
                }
            }else if(stack == Material.MELON_BLOCK){ //MELON
                if(isInArea(player.getLocation(), 15, Material.MELON_STEM)) {
                    data.addXP("farmer", 50);
                    data.setFarmerItem("melon", data.getFarmerItems("melon") + 1);
                }
            }*/
        }

    }

    public static void setActionBar(Player player, String message) {
        CraftPlayer p = (CraftPlayer)player;
        IChatBaseComponent ibc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(ibc, (byte)2);
        p.getHandle().playerConnection.sendPacket(packet);
    }

}
