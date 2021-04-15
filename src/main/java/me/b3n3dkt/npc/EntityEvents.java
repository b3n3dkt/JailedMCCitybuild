package me.b3n3dkt.npc;

import me.b3n3dkt.Citybuild;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.sql.SQLSyntaxErrorException;

public class EntityEvents implements Listener {
    @EventHandler
    public void onHandle(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Citybuild.getExecutorService().execute(() -> {
            try {
                Thread.sleep(20L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

            EntityAPI.getNpcEntities().forEach((entity) -> {
                entity.sendSpawnPackets(player);
            });
            EntityAPI.getCreatureEntities().forEach((entity) -> {
                entity.sendSpawnPackets(player);
            });
        });
    }

    @EventHandler
    public void onHandle(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Citybuild.getExecutorService().execute(() -> {
            EntityAPI.getNpcEntities().forEach((entity) -> {
                if(event.getTo().getWorld().getName().equalsIgnoreCase(entity.getLocation().getWorld().getName())){
                    entity.sendDespawnPackets(player);
                    entity.sendSpawnPackets(player);
                }else{
                    entity.sendDespawnPackets(player);
                }
            });
            EntityAPI.getCreatureEntities().forEach((entity) -> {
                if(event.getTo().getWorld().getName().equalsIgnoreCase(entity.getLocation().getWorld().getName())){
                    entity.sendDespawnPackets(player);
                    entity.sendSpawnPackets(player);
                }else{
                    entity.sendDespawnPackets(player);
                }

            });
        });
    }

    @EventHandler
    public void onHandle(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Citybuild.getExecutorService().execute(() -> {
            EntityAPI.getNpcEntities().forEach((entity) -> {
                if (player.getWorld().getName().equalsIgnoreCase(entity.getLocation().getWorld().getName())) {
                    int locationFrom = (int)event.getFrom().distance(entity.getLocation());
                    int locationTo = (int)event.getTo().distance(entity.getLocation());
                    if (locationFrom > 128 && locationTo <= 128) {
                        entity.sendDespawnPackets(player);
                        entity.sendSpawnPackets(player);
                    } else if (locationTo > 128 && locationFrom <= 128) {
                        entity.sendDespawnPackets(player);
                    }

                    if (locationTo < 10) {
                        entity.sendLookPackets(event.getPlayer());
                    }
                }

            });
            EntityAPI.getCreatureEntities().forEach((entity) -> {
                if (player.getWorld().getName().equalsIgnoreCase(entity.getLocation().getWorld().getName())) {
                    int locationFrom = (int)event.getFrom().distance(entity.getLocation());
                    int locationTo = (int)event.getTo().distance(entity.getLocation());
                    if (locationFrom > 128 && locationTo <= 128) {
                        entity.sendDespawnPackets(player);
                        entity.sendSpawnPackets(player);
                    } else if (locationTo > 128 && locationFrom <= 128) {
                        entity.sendDespawnPackets(player);
                    }

                    if (locationTo < 10) {
                        entity.sendLookPackets(event.getPlayer());
                    }
                }

            });
        });
    }
}