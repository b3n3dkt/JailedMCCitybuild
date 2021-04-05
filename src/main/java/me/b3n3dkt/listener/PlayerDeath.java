package me.b3n3dkt.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onHandle(PlayerDeathEvent event){
        event.setDeathMessage(null);
    }

}
