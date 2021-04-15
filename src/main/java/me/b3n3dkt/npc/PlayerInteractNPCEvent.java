package me.b3n3dkt.npc;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;

public class PlayerInteractNPCEvent extends Event implements Cancellable {
    public static HandlerList handlers = new HandlerList();
    public boolean cancelled = false;
    NPCEntity npcEntity;
    Player player;
    ClickType clickType;

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public PlayerInteractNPCEvent(NPCEntity npcEntity, Player player, ClickType clickType) {
        this.npcEntity = npcEntity;
        this.player = player;
        this.clickType = clickType;
    }

    public NPCEntity getNpcEntity() {
        return this.npcEntity;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ClickType getClickType() {
        return this.clickType;
    }
}
    