package me.davidrdc.composer;

import me.davidrdc.composer.util.AbstractMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickListener implements Listener {

  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    int slot = event.getSlot();

    if (event.getView().getTopInventory().getHolder() instanceof AbstractMenu) {
      event.setCancelled(true);
      AbstractMenu inventoryHolder = (AbstractMenu) event.getInventory().getHolder();
      if (inventoryHolder.getItem(slot) != null) inventoryHolder.getItem(slot).getRunnable().run();
    }
  }
}
