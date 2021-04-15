package me.b3n3dkt.npc;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.PacketType.Play.Client;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers.EntityUseAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.b3n3dkt.Citybuild;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.plugin.PluginManager;

public class EntityAPI {
    private static List<NPCEntity> npcEntities = new ArrayList();
    private static List<CreatureEntity> creatureEntities = new ArrayList();

    public EntityAPI() {
        this.loadListener();
    }

    void loadListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Citybuild.getMain(), new PacketType[]{Client.USE_ENTITY}) {
            public void onPacketReceiving(PacketEvent event) {
                int id = (Integer)event.getPacket().getIntegers().read(0);
                Iterator var3 = EntityAPI.npcEntities.iterator();

                PluginManager pluginManager;
                while(var3.hasNext()) {
                    NPCEntity npcs = (NPCEntity) var3.next();
                    if (id == npcs.getEntityPlayer().getId()) {
                        pluginManager = Bukkit.getPluginManager();
                        if (((EntityUseAction)event.getPacket().getEntityUseActions().readSafely(0)).toString() == "INTERACT") {
                            pluginManager.callEvent(new PlayerInteractNPCEvent(npcs, event.getPlayer(), ClickType.RIGHT));
                        } else if (((EntityUseAction)event.getPacket().getEntityUseActions().readSafely(0)).toString() == "ATTACK") {
                            pluginManager.callEvent(new PlayerInteractNPCEvent(npcs, event.getPlayer(), ClickType.LEFT));
                        }
                    }
                }

                var3 = EntityAPI.creatureEntities.iterator();

                while(var3.hasNext()) {
                    CreatureEntity mob = (CreatureEntity)var3.next();
                    if (id == mob.getEntityCreature().getId()) {
                        pluginManager = Bukkit.getPluginManager();
                        if (((EntityUseAction)event.getPacket().getEntityUseActions().readSafely(0)).toString() == "INTERACT") {
                            pluginManager.callEvent(new PlayerInteractCreatureEntityEvent(mob, event.getPlayer(), ClickType.RIGHT));
                        } else if (((EntityUseAction)event.getPacket().getEntityUseActions().readSafely(0)).toString() == "ATTACK") {
                            pluginManager.callEvent(new PlayerInteractCreatureEntityEvent(mob, event.getPlayer(), ClickType.LEFT));
                        }
                    }
                }

            }
        });
    }

    public static List<NPCEntity> getNpcEntities() {
        return npcEntities;
    }

    public static List<CreatureEntity> getCreatureEntities() {
        return creatureEntities;
    }
}