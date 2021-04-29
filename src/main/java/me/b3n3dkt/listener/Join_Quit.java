package me.b3n3dkt.listener;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.commands.Event;
import me.b3n3dkt.commands.MSG;
import me.b3n3dkt.job.Quest;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.npc.EntityAPI;
import me.b3n3dkt.utils.Combat;
import me.b3n3dkt.utils.PlayerData;
import me.b3n3dkt.utils.Rang;
import me.b3n3dkt.utils.Score;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Join_Quit implements Listener {

    @EventHandler
    public void onHandle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Score sb = new Score(player);
        event.setJoinMessage("§8[§a+§8] §7" + player.getName());
        if (player.hasPlayedBefore()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group add spieler");
        }
        PlayerData data = new PlayerData(player);
        if(data.exist() != true){
            data.newData();
        }
        if(Event.event == true){
            if(!Event.eventPlayers.contains(player)){
                Event.eventPlayers.add(player);
            }
        }
        for(int i = 0; i < 200; ++i) {
            if (!player.hasPermission("jailedmc.command.clearchat.bypass")) {
                player.sendMessage(" ");
            }
        }

        Citybuild.getMain().sendCurrentPlayingGamemode(event.getPlayer(), true, "§b§lJailedMC.net\n §aCitybuild");

        player.sendMessage(" ");
        player.sendMessage(" §7Wilkommen auf §bJailedmc.net");
        player.sendMessage(" §7Wir sind gerade in der Beta Testphase und manche Dinge funktunieren noch nicht so wie sie sollen " +
                "oder sind noch nicht ganz fertig.");
        player.sendMessage(" §7Es gibt auch Spielfehler die uns nicht auffalen, Melde diese Bugs bitte auf unserem Discord oder Teamspeak");
        player.sendMessage(" §7Viel Spaß auf §bJailedmc.net§7!");
        player.sendMessage(" ");

        player.setDisplayName(String.valueOf(Rang.getPrefix(player.getUniqueId().toString() + "§8×§7" + player.getName())));

        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        sb.setscoreboard();

        File file = new File("plugins//Citybuild//spawns.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        String w = cfg.getString("Spawn.World");
        double x = cfg.getDouble("Spawn.X");
        double y = cfg.getDouble("Spawn.Y");
        double z = cfg.getDouble("Spawn.Z");
        double yaw = cfg.getDouble("Spawn.Yaw");
        double pitch = cfg.getDouble("Spawn.Pitch");
        Location loc = new Location(Bukkit.getWorld(w), x, y, z);
        loc.setYaw((float) yaw);
        loc.setPitch((float) pitch);
        player.teleport(loc);
        Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {
            @Override
            public void run() {
                player.teleport(loc);
            }
        }, 20);

        Tab(player, "\n§8» §bJailedmc.net §8«\n\n §fBETA Release\n", "\n     §7TeamSpeak | §bjailedmc.net     \n§7Server | §b§oCitybuild\n");

        Citybuild.getExecutorService().execute(() -> {
            EntityAPI.getNpcEntities().forEach((entity) -> {
                entity.sendSpawnPackets(player);
            });
            EntityAPI.getCreatureEntities().forEach((entity) -> {
                entity.sendSpawnPackets(player);
            });
        });

    }

    @EventHandler
    public void onHandle(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Score sb = new Score(player);
        event.setQuitMessage("§8[§c-§8] §7" + event.getPlayer().getName());
        try {
            sb.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if(MSG.map.containsKey(player)){
            Player temp = MSG.map.get(player);
            MSG.map.remove(temp);
            MSG.map.remove(player);
        }
        if(Event.event == true){
            if(Event.eventPlayers.contains(player)){
                Event.eventPlayers.remove(player);
            }
        }
    }

    public static void Tab(Player p, String header, String footer) {
        if (header == null) header = "";
        if (footer == null) footer = "";

        PlayerConnection connection = (((CraftPlayer)p).getHandle()).playerConnection;

        IChatBaseComponent Title = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent Foot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field fielda = headerPacket.getClass().getDeclaredField("a");
            fielda.setAccessible(true);
            fielda.set(headerPacket, Title);
            Field fieldb = headerPacket.getClass().getDeclaredField("b");
            fieldb.setAccessible(true);
            fieldb.set(headerPacket, Foot);
        }
        catch (Exception e) {

            e.printStackTrace();
        } finally {
            connection.sendPacket(headerPacket);
        }
    }

}
