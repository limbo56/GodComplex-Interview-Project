package me.davidrdc.composer.util;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Represents a menu that can be opened and that contains various {@link MenuItem}
 *
 * @author David Rodriguez
 */
public abstract class AbstractMenu implements InventoryHolder {

    private Player owner;
    private String name;
    private int size;
    private Map<Integer, MenuItem> items;

    /**
     * Constructor
     *
     * @param owner of the menu
     * @param name  of the {@link Inventory}
     * @param size  of the {@link Inventory}
     */
    public AbstractMenu(Player owner, String name, int size) {
        this.owner = owner;
        this.name = name;
        this.size = size;
        this.items = new HashMap<>();
    }

    /**
     * Opens the menu to the owner
     */
    public void open() {
        this.initialize();
        owner.openInventory(getInventory());
    }

    protected abstract void initialize();

    protected void setItem(int slot, MenuItem item) {
        items.put(slot, item);
    }

    /**
     * Gets the owner of this menu
     *
     * @return {@link Player}
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Gets a {@link MenuItem} on a certain slot
     *
     * @param slot where the item is
     * @return {@link MenuItem}
     */
    public MenuItem getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public Inventory getInventory() {
        String name = translateColors(this.name);
        Inventory inventory = Bukkit.createInventory(this, this.size, name);
        this.items.forEach((slot, item) -> inventory.setItem(slot, item.getItemStack()));
        return inventory;
    }

    private String translateColors(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
