package me.b3n3dkt;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.google.gson.JsonObject;
import me.b3n3dkt.commands.*;
import me.b3n3dkt.commands.Rang;
import me.b3n3dkt.listener.*;
import me.b3n3dkt.mysql.MySQL;
import me.b3n3dkt.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public class Citybuild extends JavaPlugin{

    private ServerConfig scfg = new ServerConfig();
    private static Citybuild cb;
    public static Voting activeVote = null;
    private static String prefix;
    private static String noperm;
    private ProtocolManager protocolmanager;
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
        (new ClearLag()).startClearlag();
        new Broadcaster(this).startBroadcast();
        register();
        mM = new MessageManager(this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getConsoleSender().sendMessage("§aCitybuild System started!");
    }

    @Override
    public void onDisable(){
        MySQL.close();
        Bukkit.getConsoleSender().sendMessage("§aCitybuild System stopped!");
    }

    public void register(){
        getCommand("fly").setExecutor(new Fly());
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
        getCommand("sign").setExecutor(new Sign());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("message").setExecutor(new MSG(this));
        getCommand("reply").setExecutor(new Reply(this));
        getCommand("event").setExecutor(new Event());
        getCommand("home").setExecutor(new Home());
        getCommand("clearlag").setExecutor(new Clearlag());
        getCommand("warp").setExecutor(new Warp());

        Bukkit.getPluginManager().registerEvents(new Join_Quit(), this);
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawn(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new Achievements(), this);
        Bukkit.getPluginManager().registerEvents(new UnknownCommand(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getPluginManager().registerEvents(new LogIn(), this);
        Bukkit.getPluginManager().registerEvents(new DropEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
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

    public static String getPrefix(){ return prefix; }

    public static String getNoperm(){ return noperm; }

    public static Citybuild getMain(){ return cb; }

}
