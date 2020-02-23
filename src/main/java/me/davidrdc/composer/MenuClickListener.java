package me.davidrdc.composer;

import me.davidrdc.composer.util.AbstractMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * This listener is the one that handles all the menu clicks on {@link AbstractMenu}s
 *
 * @author David Rodriguez
 */
public class MenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof AbstractMenu))
            return;

        AbstractMenu inventoryHolder = (AbstractMenu) event.getInventory().getHolder();
        int slot = event.getSlot();

        // Cancel click
        event.setCancelled(true);

        if (inventoryHolder.getItem(slot) == null)
            return;

        // Execute runnable
        inventoryHolder.getItem(slot).getRunnable().run();
    }
}
