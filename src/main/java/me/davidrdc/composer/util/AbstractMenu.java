package me.davidrdc.composer.util;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class AbstractMenu implements InventoryHolder {

  private Player owner;
  private String name;
  private int size;
  private Map<Integer, MenuItem> items;

  public AbstractMenu(Player owner, String name, int size) {
    this.owner = owner;
    this.name = name;
    this.size = size;
    this.items = new HashMap<>();
  }

  public void open() {
    this.initialize();
    owner.openInventory(getInventory());
  }

  protected abstract void initialize();

  protected void setItem(int slot, MenuItem item) {
    items.put(slot, item);
  }

  public Player getOwner() {
    return owner;
  }

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
