package cosmo.betterminecraft.gui;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.items.REU;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Map;

public class CustomItemGui implements Listener {
    private final Inventory inv;

    public CustomItemGui() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 9);
        // Put the items into the inventory
        initializeItems();
        // Registers events for this gui
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        for (Map.Entry<String, ItemStack> entry : Core.getInstance().getReu().items.entrySet()) {
            String key = entry.getKey();
            ItemStack itemStack = entry.getValue();
            inv.addItem(itemStack);
        }
    }

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final Player p = (Player) e.getWhoClicked();

        p.getInventory().addItem(clickedItem);


    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }
}

