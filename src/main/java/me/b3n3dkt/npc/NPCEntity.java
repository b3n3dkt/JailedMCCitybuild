package me.b3n3dkt.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.b3n3dkt.Citybuild;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class NPCEntity {
    Location location;
    EntityPlayer entityPlayer;
    UUID uuid;
    Packet spawn;
    String texture;
    String signature;
    List<Packet> spawnPackets = new ArrayList();

    public NPCEntity(Location location, String texture, String signature) {
        this.location = location;
        this.texture = texture;
        this.signature = signature;
    }

    public void init(String name) {
        MinecraftServer nmsServer = ((CraftServer)Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld)this.location.getWorld()).getHandle();
        Random random = new Random();
        UUID uuid = new UUID(random.nextLong(), 0L);
        this.uuid = uuid;
        GameProfile gameProfile = new GameProfile(uuid, name);
        this.entityPlayer = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));
        this.entityPlayer.setLocation(this.location.getX(), this.location.getY(), this.location.getZ(), this.location.getYaw(), this.location.getPitch());
        EntityAPI.getNpcEntities().add(this);
        if (this.texture == null && this.signature == null) {
            this.defaultProfile(gameProfile);
        } else {
            gameProfile.getProperties().put("textures", new Property("textures", this.texture, this.signature));
        }

        DataWatcher dataWatcher = this.entityPlayer.getDataWatcher();
        dataWatcher.watch(10, (byte)127);
        this.spawnPackets.add(new PacketPlayOutEntityMetadata(this.entityPlayer.getId(), dataWatcher, false));
        this.spawnPackets.add(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{this.entityPlayer}));
        this.spawnPackets.add(new PacketPlayOutNamedEntitySpawn(this.entityPlayer));
    }

    public void sendSpawnPackets(Player player) {
        PlayerConnection connection = this.getConnection(player);
        Iterator var3 = this.spawnPackets.iterator();

        while(var3.hasNext()) {
            Packet packet = (Packet)var3.next();
            connection.sendPacket(packet);
        }

        Bukkit.getScheduler().scheduleAsyncDelayedTask(Citybuild.getMain(), () -> {
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[]{this.entityPlayer}));
        }, 40L);
    }

    public void sendDespawnPackets(Player player) {
        PlayerConnection connection = this.getConnection(player);
        connection.sendPacket(new PacketPlayOutEntityDestroy(new int[]{this.entityPlayer.getId()}));
    }

    public void sendLookPackets(Player player) {
        PlayerConnection connection = this.getConnection(player);
        Location location = this.location.setDirection(player.getLocation().subtract(this.location).toVector());
        this.entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        connection.sendPacket(new PacketPlayOutEntityMetadata(this.entityPlayer.getId(), this.entityPlayer.getDataWatcher(), false));
        connection.sendPacket(new PacketPlayOutEntityTeleport(this.entityPlayer));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(this.entityPlayer, (byte)((int)(location.getYaw() * 256.0F / 360.0F))));
    }

    public void setAnimation(Player player, Animation animation) {
        PlayerConnection connection = this.getConnection(player);
        if (animation.equals(Animation.HIT)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entityPlayer, 0));
        } else if (animation.equals(Animation.TakeDamage)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entityPlayer, 1));
        } else if (animation.equals(Animation.Effect)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entityPlayer, 4));
        } else if (animation.equals(Animation.MagicDamage)) {
            connection.sendPacket(new PacketPlayOutAnimation(this.entityPlayer, 5));
        } else {
            player.sendMessage("HIT,SWING ARM,EFFECT,MAGICEFFECT");
        }

    }

    public LivingEntity getLivingEntity() {
        return this.entityPlayer.getBukkitEntity();
    }

    public PlayerConnection getConnection(Player player) {
        return ((CraftPlayer)player).getHandle().playerConnection;
    }

    public void defaultProfile(GameProfile profile) {
        String texture = "eyJ0aW1lc3RhbXAiOjE1NTIzMjcxNTM2MTMsInByb2ZpbGVJZCI6Ijg1MmI4ZGZkYjZiMzQ4MWNiYmYwMTM3YjM2YmFlN2JmIiwicHJvZmlsZU5hbWUiOiJaMkk3TzJLOExHNjciLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWEzNjEyZDY5OWMxYzg2YmExMjAzY2MxM2UzY2I2MjI5OWVmMWI4ZTYzYzZjZDA1ZTk0OGU4M2Y3YzNjZGM1NyJ9fX0=";
        String signature = "e7eIA9Msnhwuh3p9PigJg2FKA4n6o9kDd/9pWNqCntm+8lEGFWWBmvaHhURuRs1vZvsxWFIMdTwdwKeGJ7XW4exhiPDffblx9odt3JyyGdAZG7sc8Owv2yG9q1ZB1UUyMICd7z9WWhoFsntYnHdrw4XGncjfEaR/AYaE8pMS5fVDzggyf3w4ffYOqmeKJEzt7XBcBovJKM1ZDRnyWO6O/LM6i/YJnKbuyGQHAAhP7Qtx1OD7sAPXyd7POMhfqK/EVnLOCFsTc6NP/wXNmUFjmAY96/eD5nl9ckuCUYl7kWClUb9oRJSC52SyI5P+YyK5KbWPVFRqK4uqNqOG4+5KTSWb0av3MzFxVOJhuOEtDzCmJhsSKhVXMUD7jPN+nQ5f0QJJm+xoqJZa4iPLots3L6/qOwcW5FHL5xlsObVor7C4vMuaDNb1Ii7dyoUknjdShuC/iiwMohob5NzYK1Wd+TCSki91juzNVPjGR9/kORXE9aMZtbbGWRrrzAbaauFDHdgdgFrLofqywKJer68xkQisfnalqYvKrOFKSYXYGYtMqsDwvtu+VY1upBqZjQsL30Fz7JHp9iyf+m3b4qExrwlB0vBbZ3uZIHb4tclivqfKxym7ESNJOSVlgFn/5J+8IRkk16vYwj+rkFXMFZhMRMvXl6dL3mXj4HCcsptXoXU=";
        profile.getProperties().put("textures", new Property("textures", texture, signature));
    }

    public Location getLocation() {
        return this.location;
    }

    public EntityPlayer getEntityPlayer() {
        return this.entityPlayer;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getTexture() {
        return this.texture;
    }

    public String getSignature() {
        return this.signature;
    }
}
