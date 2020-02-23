package me.davidrdc.composer.util;

import org.bukkit.inventory.ItemStack;

public class MenuItem {

  private ItemStack itemStack;
  private Runnable runnable;

  public MenuItem(ItemStack itemStack, Runnable runnable) {
    this.itemStack = itemStack;
    this.runnable = runnable;
  }

  public ItemStack getItemStack() {
    return itemStack;
  }

  public Runnable getRunnable() {
    return runnable;
  }
}
