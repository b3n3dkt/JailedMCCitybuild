package me.b3n3dkt.listener;

import me.b3n3dkt.commands.VoteKick;
import me.b3n3dkt.utils.Voting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LogIn implements Listener {

    @EventHandler
    public void onHandle(PlayerLoginEvent event){
        Player player = event.getPlayer();
        if(Voting.ban.contains(player.getName())){
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "" +
                    "§8§m          §r§8(§b§lJailedMC§8)§m          " +
                    "\n" +
                    "\n§7Du wurdest für 15 Minuten" +
                    "\n§7von unserem Server Aufgrund" +
                    "\n§7eines Votekicks ausgeschlossen!" +
                    "\n" +
                    "\n§8§m          §r§8(§b§lJailedMC§8)§m          ");
        }
    }

}
