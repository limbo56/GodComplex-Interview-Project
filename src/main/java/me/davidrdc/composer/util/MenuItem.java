package me.davidrdc.composer.util;

import org.bukkit.inventory.ItemStack;

/**
 * Represents an item in a {@link AbstractMenu} that calls a {@link Runnable} when clicked
 *
 * @author David Rodriguez
 */
public class MenuItem {

    private ItemStack itemStack;
    private Runnable runnable;

    /**
     * Constructor
     *
     * @param itemStack of the item
     * @param runnable  called when the item is clicked
     */
    public MenuItem(ItemStack itemStack, Runnable runnable) {
        this.itemStack = itemStack;
        this.runnable = runnable;
    }

    /**
     * Gets the item shown in the menu
     *
     * @return {@link ItemStack}
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Gets the callback called whe n the item is clicked
     *
     * @return {@link Runnable}
     */
    public Runnable getRunnable() {
        return runnable;
    }
}
