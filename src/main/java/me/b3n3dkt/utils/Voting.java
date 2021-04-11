package me.b3n3dkt.utils;

import java.util.ArrayList;

import me.b3n3dkt.Citybuild;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Voting {
    Player creator;
    Player target;
    static ArrayList<String> voted;
    public static ArrayList<String> ban = new ArrayList();

    public enum Vote {
        YES,
        NO;
    }

    static int count_yes = 0;
    static int count_no = 0;
    
    public Voting(Player p, Player t, String reason) {
        this.creator = p;
        this.target = t;
        voted = new ArrayList();


        sendMessage(reason);
    }
    
    public static void vote(Player p, Vote v) {
        if (!voted.contains(p.getName())) {
            voted.add(p.getName());
            if (v == Vote.YES) {
                count_yes++;
                p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§7Du hast dafür gestimmt!");
            } else if (v == Vote.NO) {
                p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§7Du hast dagegen gestimmt!");
                count_no++;

            }

        }
        else {

            p.sendMessage(String.valueOf(Citybuild.getPrefix()) + "§cDu hast bereits abgestimmt!");
        }
    }

    public void endVote() {
        int parti = count_no + count_yes;
        double oneP = (100 / parti);

        double yesP = oneP * count_yes;

        double noP = oneP * count_no;

        if (yesP >= 60.0D) {
            broadcast("§7]=============== §eVoteKick §7===============[");
            broadcast("");
            broadcast("§8> §aDie Abstimmung war erfolgreich");
            broadcast("");
            broadcast(String.valueOf(getStatistic(yesP, 50, ChatColor.DARK_GREEN)) + " §8- §2" + Math.round(yesP) + "%");
            broadcast(String.valueOf(getStatistic(noP, 50, ChatColor.DARK_RED)) + " §8- §4" + Math.round(noP) + "%");
            broadcast("");
            broadcast("§8> §e" + this.target.getName() + " §7wird von dem Server gekickt!");
            broadcast("");
            broadcast("§7]=============== §eVoteKick §7===============[");

            Bukkit.getScheduler().scheduleSyncDelayedTask(Citybuild.getMain(), new Runnable()
            {
                public void run()
                {
                    Voting.this.target.kickPlayer("§8§m------------§8[§b§lJailedRP§8]§8§m------------\n\n§7Du wurdest Aufgrund eines\n§e§lVoteKicks §7f§r\n§e§l5 §7Minuten §4§lGebannt!\n\n§8§m------------§8[§b§lJailedRP§8]§8§m------------");
                    
                    Citybuild.activeVote = null;
                    Voting.ban.add(Voting.this.target.getName());
                }
            },  100L);

            ban.add(this.target.getName());
            Bukkit.getScheduler().scheduleSyncDelayedTask(Citybuild.getMain(), new Runnable()
                    {
                        public void run()
                        {
                            Voting.ban.remove(Voting.this.target.getName());
                        }
                    },
                    6000L);
        } else {

            broadcast("§7]=============== §eVoteKick §7===============[");
            broadcast("");
            broadcast("§8> §cDie Abstimmung nicht war erfolgreich");
            broadcast("");
            broadcast(String.valueOf(getStatistic(yesP, 50, ChatColor.DARK_GREEN)) + " §8- §2" + Math.round(yesP) + "%");
            broadcast(String.valueOf(getStatistic(noP, 50, ChatColor.DARK_RED)) + " §8- §4" + Math.round(noP) + "%");
            broadcast("");
            broadcast("§8> §e" + this.target.getName() + " §7wird nicht von dem Server gekickt!");
            broadcast("");
            broadcast("§7]=============== §eVoteKick §7===============[");
            Citybuild.activeVote = null;
        }
    }

    public String getStatistic(double totalP, int scale, ChatColor c) {
        String msg = " §8[";

        int divide = 100 / scale;
        double toatlB = totalP / divide;
        int finalB = (int)Math.round(toatlB);

        int difference = scale - finalB;

        for (int i = 0; i < finalB; i++) {
            msg = String.valueOf(msg) + c + "|";
        }

        for (int i = 0; i < difference; i++) {
            msg = String.valueOf(msg) + "§7|";
        }

        return String.valueOf(msg) + "§8]";
    }
    
    public void broadcast(String msg) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(msg);
        }
    }

    private void sendMessage(String reason) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage("§7]=============== §eVoteKick §7===============[");
            all.sendMessage("          §7Es wurde eine Abstimmung gestartet, um");
            all.sendMessage("          §e" + this.target.getName() + " §7vom Server zu kicken");
            all.sendMessage("          §7Grund: §e" + reason);
            all.sendMessage("          §7Bitte stimme mit §aJa §7oder §cNein §7ab!");
            all.sendMessage("          §7Der VoteKick endet in §e30 §7Sekunden!");
            all.sendMessage("");

            TextComponent yes = new TextComponent("       [JA]       ");
            yes.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§2§lStimme dafür")).color(ChatColor.GRAY).create()));
            yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/votekick yes"));
            yes.setColor(ChatColor.DARK_GREEN);
            yes.setBold(Boolean.valueOf(true));
            yes.addExtra("       ");

            TextComponent no = new TextComponent("       [NEIN]       ");
            no.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("§4§lStimme dagegen")).color(ChatColor.GRAY).create()));
            no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/votekick no"));
            no.setColor(ChatColor.DARK_RED);
            no.setBold(Boolean.valueOf(true));

            all.spigot().sendMessage(yes);
            all.spigot().sendMessage(no);
            all.sendMessage("");
            all.sendMessage("§7]=============== §eVoteKick §7===============[");

            Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable()
                    {
                        public void run()
                        {
                            Voting.this.endVote();
                        }
                    },
                    600L);
        }
    }
}
