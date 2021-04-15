package me.b3n3dkt.npc;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;

public class PlayerInteractCreatureEntityEvent extends Event implements Cancellable {
    public static HandlerList handlers = new HandlerList();
    public boolean cancelled = false;
    CreatureEntity creatureEntity;
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

    public PlayerInteractCreatureEntityEvent(CreatureEntity creatureEntity, Player player, ClickType clickType) {
        this.creatureEntity = creatureEntity;
        this.player = player;
        this.clickType = clickType;
    }

    public CreatureEntity getCreatureEntity() {
        return this.creatureEntity;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ClickType getClickType() {
        return this.clickType;
    }
}