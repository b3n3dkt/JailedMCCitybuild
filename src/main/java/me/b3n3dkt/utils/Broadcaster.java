package me.b3n3dkt.utils;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Broadcaster {

    private final long MESSAGE_DELAY = 6000;
    private final String ROOT = "broadcastMessages";

    private Citybuild plugin;
    private FileConfiguration config;

    public Broadcaster(Citybuild plugin){
        this.plugin = plugin;
        config = plugin.getConfig();

        createFefaults();
    }

    public void startBroadcast(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    all.sendMessage(ChatColor.translateAlternateColorCodes('&', pickMessage()));
                }
            }
        }, 0, MESSAGE_DELAY);
    }

    public void createFefaults(){
        if(config.contains(ROOT)) return;
        List<String> defaults = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            defaults.add("Broadcast Message " + i);
            config.set(ROOT, defaults);
            plugin.saveConfig();
        }
    }

    private String pickMessage(){
    List<String> messages = config.getStringList(ROOT);
    int random = new Random().nextInt(messages.size());
    return messages.get(random);
    }

}
