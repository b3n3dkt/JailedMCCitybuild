package me.b3n3dkt.utils;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class Score implements AutoCloseable {
    Player player;

    public Score(Player player) {
        this.player = player;
    }

    public void setscoreboard() {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.getObjective("aaa");
        if (obj == null) {
            obj = sb.registerNewObjective("aaa", "bbb");
        }

        Bukkit.getOnlinePlayers().forEach((players) -> {
            this.getTeam(sb, players, Rang.getTeamNumber(players.getUniqueId().toString()), Rang.getPrefix(players.getUniqueId().toString()) + "§8x§7", "§7").addPlayer(players);
            players.setDisplayName(Rang.getPrefix(players.getUniqueId().toString()) + "§8x§7" + players.getName());
            players.setPlayerListName(Rang.getPrefix(players.getUniqueId().toString()) + "§8x§7" + players.getName() + " §8[§e" + MySQL.getClannameFromUser(players.getUniqueId()) + "§8]");
            Scoreboard scoreboard = players.getScoreboard();
            Bukkit.getOnlinePlayers().forEach((p) -> {
                this.getTeam(scoreboard, p, Rang.getTeamNumber(p.getUniqueId().toString()), Rang.getPrefix(p.getUniqueId().toString()) + "§8x§7", "§7").addPlayer(p);
            });
        });

        obj.setDisplayName("§8§m   §r§8(§b§lJailedMC§8)§m   ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("§a                              ").setScore(14);
        obj.getScore("§b§lRang:").setScore(13);
        obj.getScore(updateTeam(sb, "Rang", "§8» §f", "§f§l" + Rang.getSuffix(this.player.getUniqueId().toString()), ChatColor.AQUA)).setScore(12);
        obj.getScore("§5 ").setScore(11);
        obj.getScore("§b§lCoins:").setScore(10);
        obj.getScore(updateTeam(sb, "Coins", "§8» §f", "§f§l" + MySQL.getcoins(this.player.getUniqueId().toString()), ChatColor.BLUE)).setScore(9);
        obj.getScore("§4").setScore(8);
        obj.getScore("§b§lClan:").setScore(7);
        obj.getScore("§8» §f§l" + MySQL.getClannameFromUser(player.getUniqueId())).setScore(6);
        obj.getScore("§3").setScore(5);
        obj.getScore("§b§lTeamspeak").setScore(4);
        obj.getScore("§8» §f§lJailedmc.net").setScore(3);
        obj.getScore("     ").setScore(2);

        this.player.setScoreboard(sb);
    }


    public void updatetab() {
        Scoreboard sb = this.player.getScoreboard();
        Bukkit.getOnlinePlayers().forEach((players) -> {
            this.getTeam(sb, players, Rang.getTeamNumber(players.getUniqueId().toString()), Rang.getPrefix(players.getUniqueId().toString()) + "§8x§7", "§7").addPlayer(players);
            players.setDisplayName(Rang.getPrefix(players.getUniqueId().toString()) + "§8x§7" + players.getName());
            players.setPlayerListName(Rang.getPrefix(players.getUniqueId().toString()) + "§8x§7" + players.getName() + " §8[§e" + MySQL.getClannameFromUser(players.getUniqueId()) + "§8]");
            Scoreboard scoreboard = players.getScoreboard();
            Bukkit.getOnlinePlayers().forEach((p) -> {
                this.getTeam(scoreboard, p, Rang.getTeamNumber(p.getUniqueId().toString()), Rang.getPrefix(p.getUniqueId().toString()) + "§8x§7", "§7").addPlayer(p);
            });
        });
    }


    public void update() {
        Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable() {

            public void run() {
                if (Score.this.player.getScoreboard() == null) {
                    Score.this.setscoreboard();
                }
                Objective obj = Score.this.player.getScoreboard().getObjective("aaa");

                obj.getScore(Score.this.updateTeam(Score.this.player.getScoreboard(), "Rang", "§8» §f", "§f" + Rang.getSuffix(player.getUniqueId().toString()), ChatColor.AQUA)).setScore(12);
                obj.getScore(Score.this.updateTeam(Score.this.player.getScoreboard(), "Coins", "§8» §f", "§f" + MySQL.getcoins(player.getUniqueId().toString()), ChatColor.BLUE)).setScore(9);
            }
        }, 20L);
    }


    public Team getTeam(Scoreboard sb, Player p, String Team, String prefix, String suffix) {
        Team team = sb.getTeam(Team);
        if (team == null) {
            team = sb.registerNewTeam(Team);
        }

        team.setDisplayName(prefix + p.getName());
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        return team;
    }

    public String updateTeam(Scoreboard sb, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = sb.getTeam(Team);
        if (team == null) {
            team = sb.registerNewTeam(Team);
        }

        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());
        return entry.toString();
    }


    public void close() throws Exception {
        this.player = null;
    }

}