package me.davidrdc.composer.menu;

import java.util.Arrays;
import me.davidrdc.composer.util.AbstractMenu;
import me.davidrdc.composer.util.MenuItem;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AddMenu extends AbstractMenu {

  private ComposeMenu composeMenu;

  public AddMenu(Player owner, ComposeMenu composeMenu) {
    super(owner, "&aAdd Instrument", 27);
    this.composeMenu = composeMenu;
  }

  @Override
  protected void initialize() {
    // Control buttons
    setItem(18, new MenuItem(getBackButton(), () -> composeMenu.open()));

    // Instruments
    setItem(10, getInstrument("Piano", Instrument.PIANO));
    setItem(12, getInstrument("Bass Drum", Instrument.BASS_DRUM));
    setItem(14, getInstrument("Bell", Instrument.BELL));
    setItem(16, getInstrument("Flute", Instrument.FLUTE));
  }

  private MenuItem getInstrument(String name, Instrument instrument) {
    String used = String.valueOf(composeMenu.getSong().hasInstrument(instrument)).toUpperCase();
    ItemStack itemStack = new ItemStack(Material.NOTE_BLOCK, 1);
    ItemMeta meta = itemStack.getItemMeta();

    meta.setDisplayName("§a" + name);
    meta.setLore(Arrays.asList("", "§7Being used: §a" + used));
    itemStack.setItemMeta(meta);

    return new MenuItem(itemStack, getInstrumentAction(instrument));
  }

  private Runnable getInstrumentAction(Instrument instrument) {
    return () -> {
      if (!composeMenu.getSong().hasInstrument(instrument))
        composeMenu.getSong().addInstrument(instrument);
      else composeMenu.getSong().removeInstrument(instrument);
      open();
    };
  }

  private ItemStack getBackButton() {
    ItemStack itemStack = new ItemStack(Material.ARROW, 1);
    ItemMeta meta = itemStack.getItemMeta();
    meta.setDisplayName("§cGo Back");
    itemStack.setItemMeta(meta);
    return itemStack;
  }
}
