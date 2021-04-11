package me.b3n3dkt.home;

import java.util.HashMap;
import java.util.Iterator;

import me.b3n3dkt.Citybuild;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.ContainerAnvil;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilGUI {
    private Player player;
    private AnvilGUI.AnvilClickEventHandler handler;
    private HashMap<AnvilGUI.AnvilSlot, ItemStack> items = new HashMap();
    private Inventory inv;
    private Listener listener;

    public AnvilGUI(Player player, final AnvilGUI.AnvilClickEventHandler handler) {
        this.player = player;
        this.handler = handler;
        this.listener = new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (event.getWhoClicked() instanceof Player) {
                    Player clicker = (Player)event.getWhoClicked();
                    if (event.getInventory().equals(AnvilGUI.this.inv)) {
                        event.setCancelled(true);
                        ItemStack item = event.getCurrentItem();
                        int slot = event.getRawSlot();
                        String name = "";
                        if (item != null && item.hasItemMeta()) {
                            ItemMeta meta = item.getItemMeta();
                            if (meta.hasDisplayName()) {
                                name = meta.getDisplayName();
                            }
                        }

                        AnvilGUI.AnvilClickEvent clickEvent = new AnvilGUI.AnvilClickEvent(AnvilGUI.AnvilSlot.bySlot(slot), name);
                        handler.onAnvilClick(clickEvent);
                        if (clickEvent.getWillClose()) {
                            event.getWhoClicked().closeInventory();
                        }

                        if (clickEvent.getWillDestroy()) {
                            AnvilGUI.this.destroy();
                        }
                    }
                }

            }

            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getPlayer() instanceof Player) {
                    Player player = (Player)event.getPlayer();
                    Inventory inv = event.getInventory();
                    if (inv.equals(AnvilGUI.this.inv)) {
                        inv.clear();
                        AnvilGUI.this.destroy();
                    }
                }

            }

            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent event) {
                if (event.getPlayer().equals(AnvilGUI.this.getPlayer())) {
                    AnvilGUI.this.destroy();
                }

            }
        };
        Bukkit.getPluginManager().registerEvents(this.listener, Citybuild.getMain());
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setSlot(AnvilGUI.AnvilSlot slot, ItemStack item) {
        this.items.put(slot, item);
    }

    public void open() {
        EntityPlayer p = ((CraftPlayer)this.player).getHandle();
        AnvilGUI.AnvilContainer container = new AnvilGUI.AnvilContainer(p);
        this.inv = container.getBukkitView().getTopInventory();
        Iterator var3 = this.items.keySet().iterator();

        while(var3.hasNext()) {
            AnvilGUI.AnvilSlot slot = (AnvilGUI.AnvilSlot)var3.next();
            this.inv.setItem(slot.getSlot(), (ItemStack)this.items.get(slot));
        }

        int c = p.nextContainerCounter();
        p.playerConnection.sendPacket(new PacketPlayOutOpenWindow(c, "minecraft:anvil", new ChatMessage("Repairing", new Object[0]), 0));
        p.activeContainer = container;
        p.activeContainer.windowId = c;
        p.activeContainer.addSlotListener(p);
    }

    public void destroy() {
        this.player = null;
        this.handler = null;
        this.items = null;
        HandlerList.unregisterAll(this.listener);
        this.listener = null;
    }

    public interface AnvilClickEventHandler {
        void onAnvilClick(AnvilGUI.AnvilClickEvent var1);
    }

    public static class AnvilClickEvent {
        private AnvilGUI.AnvilSlot slot;
        private String name;
        private boolean close = true;
        private boolean destroy = true;

        public AnvilClickEvent(AnvilGUI.AnvilSlot slot, String name) {
            this.slot = slot;
            this.name = name;
        }

        public AnvilGUI.AnvilSlot getSlot() {
            return this.slot;
        }

        public String getName() {
            return this.name;
        }

        public boolean getWillClose() {
            return this.close;
        }

        public void setWillClose(boolean close) {
            this.close = close;
        }

        public boolean getWillDestroy() {
            return this.destroy;
        }

        public void setWillDestroy(boolean destroy) {
            this.destroy = destroy;
        }
    }

    public static enum AnvilSlot {
        INPUT_LEFT(0),
        INPUT_RIGHT(1),
        OUTPUT(2);

        private int slot;

        private AnvilSlot(int slot) {
            this.slot = slot;
        }

        public int getSlot() {
            return this.slot;
        }

        public static AnvilGUI.AnvilSlot bySlot(int slot) {
            AnvilGUI.AnvilSlot[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                AnvilGUI.AnvilSlot anvilSlot = var1[var3];
                if (anvilSlot.getSlot() == slot) {
                    return anvilSlot;
                }
            }

            return null;
        }
    }

    private class AnvilContainer extends ContainerAnvil {
        public AnvilContainer(EntityHuman entity) {
            super(entity.inventory, entity.world, new BlockPosition(0, 0, 0), entity);
        }

        public boolean a(EntityHuman entityhuman) {
            return true;
        }
    }
}
