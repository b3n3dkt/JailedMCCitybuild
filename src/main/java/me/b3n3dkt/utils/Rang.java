package me.b3n3dkt.utils;

import me.b3n3dkt.mysql.MySQL;

public class Rang {

    private MySQL sql;

    public static String getPrefix(String uuid){
        String prefix = "§7Spieler";
        String rang = MySQL.getRang(uuid.toString());
        if(rang.equalsIgnoreCase("spieler")){
            prefix = "§7Spieler";
        }else if(rang.equalsIgnoreCase("mitglied")){
            prefix = "§bMitglied";
        }else if(rang.equalsIgnoreCase("premium")){
            prefix = "§6Premium";
        }else if(rang.equalsIgnoreCase("obsidian")){
            prefix = "§dObsi";
        }else if(rang.equalsIgnoreCase("titan")){
            prefix = "§9Titan";
        }else if(rang.equalsIgnoreCase("bedrock")){
            prefix = "§5Bedrock";
        }else if(rang.equalsIgnoreCase("legende")){
            prefix = "§c§lLegende";
        }else if(rang.equalsIgnoreCase("phoenix")){
            prefix = "§e§lPhoenix";
        }else if(rang.equalsIgnoreCase("vip")){
            prefix = "§3VIP";
        }else if(rang.equalsIgnoreCase("vip")){
            prefix = "§3§lVIP+";
        }else if(rang.equalsIgnoreCase("elite")){
            prefix = "§b§lElite";
        }else if(rang.equalsIgnoreCase("donator")){
            prefix = "§eDonator";
        }else if(rang.equalsIgnoreCase("freund")){
            prefix = "§9Freund/in";
        }else if(rang.equalsIgnoreCase("youtuber")){
            prefix = "§cYTer";
        }else if(rang.equalsIgnoreCase("streamer")){
            prefix = "§dStreamer";
        }else if(rang.equalsIgnoreCase("azubi")){
            prefix = "§1Azubi";
        }else if(rang.equalsIgnoreCase("supporter")){
            prefix = "§1Sup";
        }else if(rang.equalsIgnoreCase("builder")){
            prefix = "§2Builder";
        }else if(rang.equalsIgnoreCase("testmoderator")){
            prefix = "§cTMod";
        }else if(rang.equalsIgnoreCase("moderator")){
            prefix = "§cMod";
        }else if(rang.equalsIgnoreCase("testdeveloper")){
            prefix = "§bTDev";
        }else if(rang.equalsIgnoreCase("developer")){
            prefix = "§bDev";
        }else if(rang.equalsIgnoreCase("testadministrator")){
            prefix = "§cTAdmin";
        }else if(rang.equalsIgnoreCase("administrator")){
            prefix = "§cAdmin";
        }else if(rang.equalsIgnoreCase("owner")){
            prefix = "§4Owner";
        }

        return prefix;
    }

    public static String getSuffix(String uuid){
        String suffix = "§7Spieler";
        String rang = MySQL.getRang(uuid.toString());
        if(rang.equalsIgnoreCase("spieler")){
            suffix = "§7Spieler";
        }else if(rang.equalsIgnoreCase("mitglied")){
            suffix = "§bMitglied";
        }else if(rang.equalsIgnoreCase("premium")){
            suffix = "§6Premium";
        }else if(rang.equalsIgnoreCase("obsidian")){
            suffix = "§dObsidian";
        }else if(rang.equalsIgnoreCase("titan")){
            suffix = "§9Titan";
        }else if(rang.equalsIgnoreCase("bedrock")){
            suffix = "§5Bedrock";
        }else if(rang.equalsIgnoreCase("legende")){
            suffix = "§c§lLegende";
        }else if(rang.equalsIgnoreCase("phoenix")){
            suffix = "§e§lPhoenix";
        }else if(rang.equalsIgnoreCase("vip")){
            suffix = "§3VIP";
        }else if(rang.equalsIgnoreCase("vip")){
            suffix = "§3§lVIP+";
        }else if(rang.equalsIgnoreCase("elite")){
            suffix = "§b§lElite";
        }else if(rang.equalsIgnoreCase("donator")){
            suffix = "§eDonator";
        }else if(rang.equalsIgnoreCase("freund")){
            suffix = "§9Freund/in";
        }else if(rang.equalsIgnoreCase("youtuber")){
            suffix = "§cYTer";
        }else if(rang.equalsIgnoreCase("streamer")){
            suffix = "§dStreamer";
        }else if(rang.equalsIgnoreCase("azubi")){
            suffix = "§1Azubi";
        }else if(rang.equalsIgnoreCase("supporter")){
            suffix = "§1Supporter";
        }else if(rang.equalsIgnoreCase("builder")){
            suffix = "§2Builder";
        }else if(rang.equalsIgnoreCase("testmoderator")){
            suffix = "§cT-Mod";
        }else if(rang.equalsIgnoreCase("moderator")){
            suffix = "§cModerator";
        }else if(rang.equalsIgnoreCase("testdeveloper")){
            suffix = "§bT-Dev";
        }else if(rang.equalsIgnoreCase("developer")){
            suffix = "§bDeveloper";
        }else if(rang.equalsIgnoreCase("testadministrator")){
            suffix = "§cT-Admin";
        }else if(rang.equalsIgnoreCase("administrator")){
            suffix = "§cAdministrator";
        }else if(rang.equalsIgnoreCase("owner")){
            suffix = "§4Owner";
        }

        return suffix;
    }

    public static String getGroupNumber(String group) {
        String number = "0001";
        if (group.equalsIgnoreCase("spieler")) {
            number = "0025";
        } else if (group.equalsIgnoreCase("mitglied")) {
            number = "0024";
        } else if (group.equalsIgnoreCase("premium")) {
            number = "0023";
        } else if (group.equalsIgnoreCase("obsidian")) {
            number = "0022";
        } else if (group.equalsIgnoreCase("titan")) {
            number = "0021";
        } else if (group.equalsIgnoreCase("bedrock")) {
            number = "0020";
        } else if (group.equalsIgnoreCase("legende")) {
            number = "0019";
        } else if (group.equalsIgnoreCase("phoenix")) {
            number = "0018";
        } else if (group.equalsIgnoreCase("vip")) {
            number = "0017";
        } else if (group.equalsIgnoreCase("vip")) {
            number = "0016";
        } else if (group.equalsIgnoreCase("elite")) {
            number = "0015";
        } else if (group.equalsIgnoreCase("donator")) {
            number = "0014";
        }else if(group.equalsIgnoreCase("freund")){
            number= "0013";
        }else if(group.equalsIgnoreCase("youtuber")){
            number = "0012";
        }else if(group.equalsIgnoreCase("streamer")){
            number = "0011";
        } else if (group.equalsIgnoreCase("azubi")) {
            number = "0010";
        } else if (group.equalsIgnoreCase("supporter")) {
            number = "0009";
        } else if (group.equalsIgnoreCase("builder")) {
            number = "0008";
        } else if (group.equalsIgnoreCase("testmoderator")) {
            number = "0007";
        } else if (group.equalsIgnoreCase("moderator")) {
            number = "0006";
        } else if (group.equalsIgnoreCase("testdeveloper")) {
            number = "0005";
        } else if (group.equalsIgnoreCase("developer")) {
            number = "0004";
        } else if (group.equalsIgnoreCase("testadministrator")) {
            number = "0003";
        } else if (group.equalsIgnoreCase("administrator")) {
            number = "0002";
        } else if (group.equalsIgnoreCase("owner")) {
            number = "0001";
        }
        return number;
    }

    public static String getTeamNumber(String uuid){
        String number = "0001";
        String rang = MySQL.getRang(uuid);
        if (rang.equalsIgnoreCase("spieler")) {
            number = "0025";
        } else if (rang.equalsIgnoreCase("mitglied")) {
            number = "0024";
        } else if (rang.equalsIgnoreCase("premium")) {
            number = "0023";
        } else if (rang.equalsIgnoreCase("obsidian")) {
            number = "0022";
        } else if (rang.equalsIgnoreCase("titan")) {
            number = "0021";
        } else if (rang.equalsIgnoreCase("bedrock")) {
            number = "0020";
        } else if (rang.equalsIgnoreCase("legende")) {
            number = "0019";
        } else if (rang.equalsIgnoreCase("phoenix")) {
            number = "0018";
        } else if (rang.equalsIgnoreCase("vip")) {
            number = "0017";
        } else if (rang.equalsIgnoreCase("vip")) {
            number = "0016";
        } else if (rang.equalsIgnoreCase("elite")) {
            number = "0015";
        } else if (rang.equalsIgnoreCase("donator")) {
            number = "0014";
        }else if(rang.equalsIgnoreCase("freund")){
            number= "0013";
        }else if(rang.equalsIgnoreCase("youtuber")){
            number = "0012";
        }else if(rang.equalsIgnoreCase("streamer")){
            number = "0011";
        } else if (rang.equalsIgnoreCase("azubi")) {
            number = "0010";
        } else if (rang.equalsIgnoreCase("supporter")) {
            number = "0009";
        } else if (rang.equalsIgnoreCase("builder")) {
            number = "0008";
        } else if (rang.equalsIgnoreCase("testmoderator")) {
            number = "0007";
        } else if (rang.equalsIgnoreCase("moderator")) {
            number = "0006";
        } else if (rang.equalsIgnoreCase("testdeveloper")) {
            number = "0005";
        } else if (rang.equalsIgnoreCase("developer")) {
            number = "0004";
        } else if (rang.equalsIgnoreCase("testadministrator")) {
            number = "0003";
        } else if (rang.equalsIgnoreCase("administrator")) {
            number = "0002";
        } else if (rang.equalsIgnoreCase("owner")) {
            number = "0001";
        }

        return number;
    }

}
