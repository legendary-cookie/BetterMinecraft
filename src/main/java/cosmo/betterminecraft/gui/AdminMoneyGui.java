package cosmo.betterminecraft.gui;

import cosmo.betterminecraft.Core;
import cosmo.betterminecraft.event.custom.EconomyUpdateEvent;
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

import java.util.Map;

public class AdminMoneyGui implements Listener {
    private final Inventory inv;

    public AdminMoneyGui() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 27);
        // Put the items into the inventory
        initializeItems();
        // Registers events for this gui
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        // ItemStacks
        ItemStack giveItem = new ItemStack(Material.GREEN_DYE);
        ItemStack takeItem = new ItemStack(Material.RED_DYE);
        ItemStack fillerItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        // Meta
        ItemMeta giveMeta = giveItem.getItemMeta();
        giveMeta.setDisplayName("Give 100 coins");
        giveItem.setItemMeta(giveMeta);
        ItemMeta takeMeta = takeItem.getItemMeta();
        takeMeta.setDisplayName("Take 10 coins");
        takeItem.setItemMeta(takeMeta);
        // Inv fill
        inv.addItem(giveItem);
        inv.addItem(fillerItem);
        inv.addItem(takeItem);

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

        if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("Give 100 coins")) {
            Core.getInstance().getBankDatabase().addCoinsToBank(p.getUniqueId(), 100);
            Bukkit.getPluginManager().callEvent(new EconomyUpdateEvent(p));
        }
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }
}
