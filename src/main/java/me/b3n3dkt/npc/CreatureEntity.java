package me.b3n3dkt.npc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CreatureEntity {
    Location location;
    Entity entity;
    WorldServer nmsWorld;
    List<Packet> spawnPackets = new ArrayList();

    public CreatureEntity(Location loc) {
        this.location = loc;
        this.nmsWorld = ((CraftWorld)this.location.getWorld()).getHandle();
    }

    public void init(String Name, Entity Creature) {
        this.entity = Creature;
        if (Name != null) {
            this.entity.setCustomName(Name);
        }

        this.entity.setLocation(this.location.getX(), this.location.getY(), this.location.getZ(), this.location.getYaw(), this.location.getPitch());
        EntityAPI.getCreatureEntities().add(this);
        this.spawnPackets.add(new PacketPlayOutSpawnEntityLiving((EntityLiving) this.entity));
    }

    public void sendLookPackets(Player player) {
        PlayerConnection connection = this.getConnection(player);
        Location location = this.location.setDirection(player.getLocation().subtract(this.location).toVector());
        this.entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        connection.sendPacket(new PacketPlayOutEntityTeleport(this.entity));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(this.entity, (byte)((int)(location.getYaw() * 256.0F / 360.0F))));
    }

    public void setAnimation(Player player, Animation animation) {
        PlayerConnection connection = this.getConnection(player);
        if (animation.equals(Animation.HIT)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entity, 0));
        } else if (animation.equals(Animation.TakeDamage)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entity, 1));
        } else if (animation.equals(Animation.Effect)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entity, 4));
        } else if (animation.equals(Animation.MagicDamage)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entity, 5));
        } else {
            player.sendMessage("HIT,SWING ARM,EFFECT,MAGICEFFECT");
        }

    }

    public void sendSpawnPackets(Player player) {
        PlayerConnection connection = this.getConnection(player);
        Iterator var3 = this.spawnPackets.iterator();

        while(var3.hasNext()) {
            Packet packet = (Packet)var3.next();
            connection.sendPacket(packet);
        }

    }

    public void sendDespawnPackets(Player player) {
        PlayerConnection connection = this.getConnection(player);
        connection.sendPacket(new PacketPlayOutEntityDestroy(new int[]{this.entity.getId()}));
    }

    public PlayerConnection getConnection(Player player) {
        return ((CraftPlayer)player).getHandle().playerConnection;
    }

    public WorldServer getNmsWorld() {
        return this.nmsWorld;
    }

    public Entity getEntityCreature() {
        return this.entity;
    }

    public Location getLocation() {
        return this.location;
    }
}