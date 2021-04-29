package me.b3n3dkt;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.google.gson.JsonObject;
import me.b3n3dkt.auktionshaus.CMD_Auktionshaus;
import me.b3n3dkt.auktionshaus.InventoryClick_Auktionshaus;
import me.b3n3dkt.auktionshaus.InventoryClick_YourItems;
import me.b3n3dkt.auktionshaus.InventoryCloseListener;
import me.b3n3dkt.commands.*;
import me.b3n3dkt.commands.Rang;
import me.b3n3dkt.home.Home;
import me.b3n3dkt.home.InventoryClickHome;
import me.b3n3dkt.job.*;
import me.b3n3dkt.listener.*;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.npc.EntityAPI;
import me.b3n3dkt.npc.EntityEvents;
import me.b3n3dkt.npc.NPCEntity;
import me.b3n3dkt.rand.InvetoryClick_Rand;
import me.b3n3dkt.rand.Rand;
import me.b3n3dkt.shop.InventoryClick_Listener;
import me.b3n3dkt.shop.NPCInteract;
import me.b3n3dkt.shop.Shop_CMD;
import me.b3n3dkt.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Citybuild extends JavaPlugin{

    private ServerConfig scfg = new ServerConfig();
    private static Citybuild cb;
    public static Voting activeVote = null;
    private static String prefix;
    private static String noperm;
    private ProtocolManager protocolmanager;
    private static ExecutorService executorService;
    public static MessageManager mM;
    private static EntityAPI entityAPI;

    @Override
    public void onEnable(){
        if(scfg.exist() == false){
            scfg.newConfig();
        }
        try {
            loadUtils();
        } catch (IOException e) {}
        MySQL.connect();
        this.loadProtocolLib();
        //(new ClearLag()).startClearlag();
        new Broadcaster(this).startBroadcast();
        executorService = Executors.newFixedThreadPool(12);
        register();
        entityAPI = new EntityAPI();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
         (new NPCEntity(new Location(Bukkit.getWorld("plots"), 10.5D, 73.0D, 6.5D), "eyJ0aW1lc3RhbXAiOjE1NTIzMjcxNTM2MTMsInByb2ZpbGVJZCI6Ijg1MmI4ZGZkYjZiMzQ4MWNiYmYwMTM3YjM2YmFlN2JmIiwicHJvZmlsZU5hbWUiOiJaMkk3TzJLOExHNjciLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWEzNjEyZDY5OWMxYzg2YmExMjAzY2MxM2UzY2I2MjI5OWVmMWI4ZTYzYzZjZDA1ZTk0OGU4M2Y3YzNjZGM1NyJ9fX0=", "e7eIA9Msnhwuh3p9PigJg2FKA4n6o9kDd/9pWNqCntm+8lEGFWWBmvaHhURuRs1vZvsxWFIMdTwdwKeGJ7XW4exhiPDffblx9odt3JyyGdAZG7sc8Owv2yG9q1ZB1UUyMICd7z9WWhoFsntYnHdrw4XGncjfEaR/AYaE8pMS5fVDzggyf3w4ffYOqmeKJEzt7XBcBovJKM1ZDRnyWO6O/LM6i/YJnKbuyGQHAAhP7Qtx1OD7sAPXyd7POMhfqK/EVnLOCFsTc6NP/wXNmUFjmAY96/eD5nl9ckuCUYl7kWClUb9oRJSC52SyI5P+YyK5KbWPVFRqK4uqNqOG4+5KTSWb0av3MzFxVOJhuOEtDzCmJhsSKhVXMUD7jPN+nQ5f0QJJm+xoqJZa4iPLots3L6/qOwcW5FHL5xlsObVor7C4vMuaDNb1Ii7dyoUknjdShuC/iiwMohob5NzYK1Wd+TCSki91juzNVPjGR9/kORXE9aMZtbbGWRrrzAbaauFDHdgdgFrLofqywKJer68xkQisfnalqYvKrOFKSYXYGYtMqsDwvtu+VY1upBqZjQsL30Fz7JHp9iyf+m3b4qExrwlB0vBbZ3uZIHb4tclivqfKxym7ESNJOSVlgFn/5J+8IRkk16vYwj+rkFXMFZhMRMvXl6dL3mXj4HCcsptXoXU=")).init("§6§lBelohnungen");
         (new NPCEntity(new Location(Bukkit.getWorld("plots"), 19.5D, 73.0D, 0.5D), "eyJ0aW1lc3RhbXAiOjE1NTIzMjcxNTM2MTMsInByb2ZpbGVJZCI6Ijg1MmI4ZGZkYjZiMzQ4MWNiYmYwMTM3YjM2YmFlN2JmIiwicHJvZmlsZU5hbWUiOiJaMkk3TzJLOExHNjciLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWEzNjEyZDY5OWMxYzg2YmExMjAzY2MxM2UzY2I2MjI5OWVmMWI4ZTYzYzZjZDA1ZTk0OGU4M2Y3YzNjZGM1NyJ9fX0=", "e7eIA9Msnhwuh3p9PigJg2FKA4n6o9kDd/9pWNqCntm+8lEGFWWBmvaHhURuRs1vZvsxWFIMdTwdwKeGJ7XW4exhiPDffblx9odt3JyyGdAZG7sc8Owv2yG9q1ZB1UUyMICd7z9WWhoFsntYnHdrw4XGncjfEaR/AYaE8pMS5fVDzggyf3w4ffYOqmeKJEzt7XBcBovJKM1ZDRnyWO6O/LM6i/YJnKbuyGQHAAhP7Qtx1OD7sAPXyd7POMhfqK/EVnLOCFsTc6NP/wXNmUFjmAY96/eD5nl9ckuCUYl7kWClUb9oRJSC52SyI5P+YyK5KbWPVFRqK4uqNqOG4+5KTSWb0av3MzFxVOJhuOEtDzCmJhsSKhVXMUD7jPN+nQ5f0QJJm+xoqJZa4iPLots3L6/qOwcW5FHL5xlsObVor7C4vMuaDNb1Ii7dyoUknjdShuC/iiwMohob5NzYK1Wd+TCSki91juzNVPjGR9/kORXE9aMZtbbGWRrrzAbaauFDHdgdgFrLofqywKJer68xkQisfnalqYvKrOFKSYXYGYtMqsDwvtu+VY1upBqZjQsL30Fz7JHp9iyf+m3b4qExrwlB0vBbZ3uZIHb4tclivqfKxym7ESNJOSVlgFn/5J+8IRkk16vYwj+rkFXMFZhMRMvXl6dL3mXj4HCcsptXoXU=")).init("§6§lShop");
         (new NPCEntity(new Location(Bukkit.getWorld("plots"), 9.5D, 73.0D, -3.5D), "eyJ0aW1lc3RhbXAiOjE1ODY3MTkyMzQwODAsInByb2ZpbGVJZCI6IjAxM2IwZGQyZjc1MTQxNTY4OTg2NDY4NTgzZjg2NTcyIiwicHJvZmlsZU5hbWUiOiIwY2tyNG1NYyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBhNzAzMjEwYTc3MTU1YWE4Zjc4ZWI4MThhNDNhMjcwMGFlODI4MDQwNGM4ZmFjYzQ3N2QyYzI2OTM2MDg5IiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=", "i+WUG9bjl106YDZIkfYpiaZqPNLNSiEcTWupm6i41ITNYjTL3AlILm/JoxBCFdJD/wuG+rKKeU6gFNlanuoZAwiHP30SsrrYBfVq9kTSuoyYKPAFWg0cWypfQg9TkLQvVznv6FqvRygnUqvpyqpDF6TzCkexVGfs7BKvWhgCg6krbyLKhhEtJEwIAwlhpF6ju/ROlx3BiQKdWnpYa4yZEagpFRhoiCCq48BDNQ1HB02PuhXUXqczWMuk0PlNomzn/3J3f7HluTkNxESBdk2QgdHc8aRlyvvK9YfRaR/qBDGAMiS0rRUCrhbKb5oO5YeHFZixvzqugRwwNbWVTBSi0a10hQgnVhcEqh90Au705Qjf1yII9UCtDiPpZLcSVAUBqAzwY64MgT9KmKgpH7+OF4B1NugJo7cODJo3yZiU1ulC9XtC7ObzGz8QW/5HrpbnbHYHfFE0udPM1ZsIWxZ326HlW7Tp8NrRA4cmf4LOp2cHs/XItycI9uHzTKhPJGgNmuzR8AIKdEU5PagFmx2vDVVPFnwkQlIxyz5HAGiOT3R2iGMGwx4sNj6QXEySSXfjgqBTRtvmTquaQ9ivXAEFxKQJmVd2WI6S1OYBDNXcg+4ujOjc1SZ32VJCLXfvBaumLPBZr9q76FldAZLX/U7p7a7GSJZHjT9+pkKqqortJ4s=")).init("§6§lJobs");
         Bukkit.getConsoleSender().sendMessage("§aCitybuild System started!");
    }

    @Override
    public void onDisable(){
        MySQL.close();
        Bukkit.getConsoleSender().sendMessage("§aCitybuild System stopped!");
    }

    public void register(){
        getCommand("discord").setExecutor(new Discord());
        getCommand("fly").setExecutor(new Fly());
        getCommand("flyspeed").setExecutor(new FlySpeed());
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("money").setExecutor(new Money());
        getCommand("eco").setExecutor(new Eco());
        getCommand("rang").setExecutor(new Rang());
        getCommand("pay").setExecutor(new Pay());
        getCommand("votekick").setExecutor(new VoteKick());
        getCommand("chatclear").setExecutor(new ClearChat());
        getCommand("feed").setExecutor(new Feed());
        getCommand("globalmute").setExecutor(new GlobalMute());
        getCommand("heal").setExecutor(new Heal());
        getCommand("invsee").setExecutor(new Invsee());
        getCommand("rename").setExecutor(new Rename());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("event").setExecutor(new Event());
        getCommand("home").setExecutor(new Home());
        getCommand("clearlag").setExecutor(new Clearlag());
        getCommand("warp").setExecutor(new Warp());
        getCommand("job").setExecutor(new Job());
        getCommand("quest").setExecutor(new Quest_CMD());
        getCommand("shop").setExecutor(new Shop_CMD());
        getCommand("lag").setExecutor(new Lag());
        getCommand("tpa").setExecutor(new TPA());
        getCommand("rand").setExecutor(new Rand());
        getCommand("sign").setExecutor(new Sign());
        getCommand("enderchest").setExecutor(new Enderchest());
        getCommand("crash").setExecutor(new CMD_Crash());
        getCommand("merge").setExecutor(new Merge());
        getCommand("auktionshaus").setExecutor(new CMD_Auktionshaus());

        Bukkit.getPluginManager().registerEvents(new Join_Quit(), this);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawn(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new UnknownCommand(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getPluginManager().registerEvents(new LogIn(), this);
        Bukkit.getPluginManager().registerEvents(new DropEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickHome(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_Job(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new BreakBlock(), this);
        Bukkit.getPluginManager().registerEvents(new FishingEvent(), this);
        Bukkit.getPluginManager().registerEvents(new NPCInteract(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_Listener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_Belohnungen(), this);
        Bukkit.getPluginManager().registerEvents(new InvetoryClick_Rand(), this);
        Bukkit.getPluginManager().registerEvents(new EntityEvents(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_Auktionshaus(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_YourItems(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
    }

    public void loadUtils() throws IOException{
        prefix = scfg.getPrefix();
        noperm = scfg.getNoPerms();
        cb = this;
        MySQL.username = scfg.getUsername();
        MySQL.password = scfg.getPassword();
        MySQL.database = scfg.getDatabase();
        MySQL.host = scfg.getHost();
        MySQL.port = scfg.getPort();

    }

    private void loadProtocolLib() {
        this.protocolmanager = ProtocolLibrary.getProtocolManager();
        this.protocolmanager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, new PacketType[]{PacketType.Play.Server.TAB_COMPLETE}) {
            public void onPacketSending(PacketEvent e) {
                if (e.getPacketType() == PacketType.Play.Server.TAB_COMPLETE && !e.getPlayer().isOp()) {
                    e.setCancelled(true);
                }

            }
        });
    }

    public void sendCurrentPlayingGamemode(Player receiver, boolean visible, String gamemodeName) {
        JsonObject object = new JsonObject();
        object.addProperty("show_gamemode", visible);
        object.addProperty("gamemode_name", gamemodeName);
        LabyModProtocol.sendLMCMessage(receiver, "server_gamemode", object);
    }

    public static ExecutorService getExecutorService() { return executorService; }

    public static String getPrefix(){ return prefix; }

    public static String getNoperm(){ return noperm; }

    public static Citybuild getMain(){ return cb; }

    public static EntityAPI getEntityAPI(){ return entityAPI;}

}
