package me.b3n3dkt.utils;

public class ServerConfig {

    private static FileBuilder fb;

    public ServerConfig() {
        fb = new FileBuilder("plugins//Citybuild//Citybuild//", "config.yml");
    }

    public boolean exist() {
        return fb.exist();
    }

    public void newConfig() {
        fb.setValue("Prefix", "§3§lJailed§f§lRP §8» ");
        fb.setValue("NoPerms", "§3§lJailed§f§lRP §8» §cYou don't have permissions to do that!");
        fb.setValue("MySQL.Username", "username");
        fb.setValue("MySQL.Password", "password");
        fb.setValue("MySQL.Database", "databse");
        fb.setValue("MySQL.Host", "host");
        fb.setValue("MySQL.Port", "port");
        fb.save();
    }

    public String getPrefix() {
        return fb.getString("Prefix");
    }

    public String getNoPerms() {
        return fb.getString("NoPerms");
    }



    public String getUsername() {
        return fb.getString("MySQL.Username");
    }
    public String getPassword() {
        return fb.getString("MySQL.Password");
    }
    public String getDatabase() {
        return fb.getString("MySQL.Database");
    }
    public String getHost() {
        return fb.getString("MySQL.Host");
    }
    public String getPort() {
        return fb.getString("MySQL.Port");
    }

}
