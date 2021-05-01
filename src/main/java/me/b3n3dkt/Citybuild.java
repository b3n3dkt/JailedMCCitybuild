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
import me.b3n3dkt.commands.Belohnungen;
import me.b3n3dkt.commands.Rang;
import me.b3n3dkt.home.Home;
import me.b3n3dkt.home.InventoryClickHome;
import me.b3n3dkt.job.*;
import me.b3n3dkt.listener.*;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.rand.InvetoryClick_Rand;
import me.b3n3dkt.rand.Rand;
import me.b3n3dkt.shop.InventoryClick_Listener;
import me.b3n3dkt.shop.Shop_CMD;
import me.b3n3dkt.utils.*;
import org.bukkit.Bukkit;
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
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
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
        getCommand("belohnungen").setExecutor(new Belohnungen());
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
        Bukkit.getPluginManager().registerEvents(new InventoryClick_Listener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_Belohnungen(), this);
        Bukkit.getPluginManager().registerEvents(new InvetoryClick_Rand(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_Auktionshaus(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick_YourItems(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
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

}
