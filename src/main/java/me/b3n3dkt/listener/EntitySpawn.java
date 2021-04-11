package me.b3n3dkt.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {

    @EventHandler
    public void onHandle(CreatureSpawnEvent event){
        if(event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER) || event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)){
            event.setCancelled(false);
        }else{
            event.setCancelled(true);
        }
    }

}
