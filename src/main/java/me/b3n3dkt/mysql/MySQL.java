package me.b3n3dkt.mysql;

import me.b3n3dkt.Citybuild;
import me.b3n3dkt.utils.ServerConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySQL {

    private static ServerConfig scfg;
    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;
    public static Connection con;

    public static void connect() {
        Bukkit.getScheduler().runTaskAsynchronously(Citybuild.getMain(), new Runnable()
        {
            public void run()
            {
                if (!MySQL.isConnected()) {
                    try {
                        long start = System.currentTimeMillis();
                        MySQL.con = DriverManager.getConnection("jdbc:mysql://" + MySQL.host + ":" + MySQL.port + "/" + MySQL.database + "?useJDBCCompliantTimezoneShift=true&&serverTimezone=UTC&&useUnicode=true&&autoReconnect=true", MySQL.username, MySQL.password);
                        Update("CREATE TABLE IF NOT EXISTS Homes(UUID varchar(250), Name varchar(255), LocationX VARCHAR(255), LocationY VARCHAR(255), LocationZ VARCHAR(255), LocationYAW VARCHAR(255), LocationPITCH VARCHAR(255))");
                        long end = System.currentTimeMillis();
                        System.out.println("Connection to MySQL server established! (" + host + ":" + port + ")");
                        System.out.println("Connection took " + ((end - start)) + "ms!");
                    } catch (SQLException e1) {
                        System.out.println("Could not connect to MySQL server! because: " + e1.getMessage());
                    }
                }
            }
        });
    }

    public static void close() {
        if (MySQL.isConnected()) {
            try {
                MySQL.con.close();
                System.out.println("MySQL Connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() { return (con != null); }

    public static void update(final String qry) {
        Bukkit.getScheduler().runTaskAsynchronously(Citybuild.getMain(), new Runnable()
        {
            public void run()
            {
                if (MySQL.isConnected()) {
                    try {
                        MySQL.con.createStatement().executeUpdate(qry);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable()
                            {
                                public void run()
                                {
                                    if (!MySQL.isConnected()) {
                                        MySQL.connect();
                                    }
                                }
                            },
                            60L);
                }
            }
        });
    }

    public static ResultSet getResult(String qry) {
        if (isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable()
                    {
                        public void run()
                        {
                            if (!MySQL.isConnected()) {
                                MySQL.connect();
                            }
                        }
                    },
                    60L);
        }
        return null;
    }


    public static Connection getConnection() { return con; }

    public static void Update(String qry) {
        if(MySQL.isConnected()){
            try {
                Statement st = con.createStatement();
                st.executeUpdate(qry);
                st.close();
            } catch (SQLException var3) {
                Bukkit.getConsoleSender().sendMessage("§cMySQL: Error while updating ->" + var3.getMessage());
                var3.printStackTrace();
            }
        } else {
            Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable()
                    {
                        public void run()
                        {
                            if (!MySQL.isConnected()) {
                                MySQL.connect();
                                Update(qry);
                            }
                        }
                    },
                    60L);
        }

    }

    public static PreparedStatement getStatement(String sql) {
        if (MySQL.isConnected()) {
            try {
                return (PreparedStatement) con.prepareStatement(sql);
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }else{
            Bukkit.getScheduler().runTaskLater(Citybuild.getMain(), new Runnable()
                    {
                        public void run()
                        {
                            if (!MySQL.isConnected()) {
                                MySQL.connect();
                                getStatement(sql);
                            }
                        }
                    },
                    60L);
        }

        return null;
    }

    public static Long getX(String uuid, String name) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ? AND Name= ?");
            ps.setString(1, uuid.toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("LocationX");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static Location getLocation(Player p, String name) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ? AND Name= ?");
            ps.setString(1, p.getUniqueId().toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Location loc = new Location(p.getWorld(), (double)getX(p.getUniqueId().toString(), name), (double)getY(p.getUniqueId().toString(), name), (double)getZ(p.getUniqueId().toString(), name));
                loc.setPitch((float)getPitch(p.getUniqueId().toString(), name));
                loc.setYaw((float)getYaw(p.getUniqueId().toString(), name));
                return loc;
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public static Long getY(String uuid, String name) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ? AND Name= ?");
            ps.setString(1, uuid.toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("LocationY");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static Long getZ(String uuid, String name) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ? AND Name= ?");
            ps.setString(1, uuid.toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("LocationZ");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static Long getYaw(String uuid, String name) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ? AND Name= ?");
            ps.setString(1, uuid.toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("LocationYAW");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static Long getPitch(String uuid, String name) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ? AND Name= ?");
            ps.setString(1, uuid.toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("LocationPITCH");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static void addHome(Player p, String name) {
        try {
            PreparedStatement pr = getStatement("INSERT INTO Homes (UUID, Name, LocationX, LocationY, LocationZ, LocationYAW, LocationPITCH) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pr.setString(1, p.getUniqueId().toString());
            pr.setString(2, name);
            pr.setString(3, String.valueOf(p.getLocation().getX()));
            pr.setString(4, String.valueOf(p.getLocation().getY()));
            pr.setString(5, String.valueOf(p.getLocation().getZ()));
            pr.setString(6, String.valueOf(p.getLocation().getYaw()));
            pr.setString(7, String.valueOf(p.getLocation().getPitch()));
            pr.executeUpdate();
            pr.close();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public static boolean isHomeExist(Player p, String name) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID= ? AND Name= ?");
            ps.setString(1, p.getUniqueId().toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            boolean Playercoins = rs.next();
            rs.close();
            rs.close();
            return Playercoins;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public static List<String> getHomes(Player p) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            ArrayList list = new ArrayList();

            while(rs.next()) {
                list.add(rs.getString("Name"));
            }

            return list;
        } catch (SQLException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static void deleteHome(final String home, final Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(Citybuild.getMain(), new Runnable() {
            public void run() {
                MySQL.Update("DELETE FROM `Homes` WHERE Name = '" + home + "' AND UUID = '" + p.getUniqueId().toString() + "'");
            }
        });
    }

    public static int gethomesint(Player p) {
        try {
            PreparedStatement sql = getStatement("SELECT COUNT(*) FROM `Homes` WHERE UUID = '" + p.getUniqueId().toString() + "'");
            ResultSet rs = sql.executeQuery();
            rs.first();
            int numberOfRows = rs.getInt("COUNT(*)");
            sql.close();
            return numberOfRows;
        } catch (Exception var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    public static List<String>[] getHomes2(Player p) {
        try {
            List<String>[] lists = new List[3];
            PreparedStatement ps = getStatement("SELECT * FROM Homes WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            lists[0] = new ArrayList<>();

            while(rs.next()) {
                byte var5 = -1;
                switch(var5) {
                    case 0:
                        lists[0].add(rs.getString("Name"));
                }
            }

            return lists;
        } catch (SQLException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static boolean isRegistered(String uuid) {
        try (PreparedStatement ps = getStatement("SELECT UUID FROM Playerdata WHERE UUID= ?")) {
            ps.setString(1, uuid.toString());
            if(ps.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static double getcoins(String uuid) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM Playerdata WHERE UUID= ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            double points = rs.getDouble("Coins");
            rs.close();
            ps.close();
            return points;
        } catch (Exception var5) {
            var5.printStackTrace();
            return -1.0;
        }
    }

    public static void setcoins(String uuid, double coins) {
        try {
            PreparedStatement ps = getStatement("UPDATE Playerdata SET Coins= ? WHERE UUID= ?");
            ps.setDouble(1, coins);
            ps.setString(2, uuid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static boolean hasEnoughMoney(String uuid, double amount){
        if(getcoins(uuid) >= amount || getcoins(uuid) == amount){
            return true;
        }else{
            return false;
        }
    }

    public static String getRang(String uuid) {
        try (PreparedStatement ps = getStatement("SELECT Rang FROM Playerdata WHERE UUID= ?")) {
            ps.setString(1, uuid);
            if(ps.executeQuery().next()) {
                return ps.getResultSet().getString("Rang");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "null";
    }


    public static void setRang(String uuid, String Rang) {
        try {
            PreparedStatement ps = getStatement("UPDATE Playerdata SET Rang= ? WHERE UUID= ?");
            ps.setString(1, Rang);
            ps.setString(2, uuid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static String getClannameFromUser(UUID user) {
        try (PreparedStatement ps = getStatement("SELECT * FROM clanmembers WHERE UUID= ?")) {
            ps.setString(1, user.toString());
            if(ps.executeQuery().next()) {
                return ps.getResultSet().getString("clanname");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Clanlos";
    }

}
