package me.davidrdc.composer.menu;

import java.util.Arrays;
import me.davidrdc.composer.song.Song;
import me.davidrdc.composer.song.SongManager;
import me.davidrdc.composer.util.AbstractMenu;
import me.davidrdc.composer.util.MenuItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * This class represents an {@link AbstractMenu} containing all the {@link Song}s that have been
 * created by a certain {@link Player}
 *
 * @author David Rodriguez
 */
public class SongsMenu extends AbstractMenu {

    /**
     * Constructor
     *
     * @param owner of the menu
     */
    public SongsMenu(Player owner) {
        super(owner, "&eYour Songs", 54);
    }

    @Override
    protected void initialize() {
        SongManager songManager = SongManager.getInstance();

        // Control options
        setItem(45, new MenuItem(getBackButton(), () -> new ShareMenu(getOwner()).open()));

        // Add songs to menu
        int slot = 9;
        for (Song song : songManager.getSongs(getOwner())) {
            if (slot == 45) {
                return;
            }
            if (slot % 9 == 0) {
                slot++;
            }
            if ((slot + 1) % 9 == 0) {
                slot += 2;
            }
            setItem(slot, new MenuItem(getSongItem(song), getSongAction(songManager, song)));
            slot++;
        }
    }

    private Runnable getSongAction(SongManager songManager, Song song) {
        return () -> songManager.playSong(getOwner(), song);
    }

    private ItemStack getBackButton() {
        ItemStack itemStack = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§cGo Back");
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack getSongItem(Song song) {
        ItemStack itemStack = new ItemStack(Material.NOTE_BLOCK, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(song.getName());
        meta.setLore(
              Arrays.asList("", "§7Author: " + song.getAuthor().getName(), "§a§lClick to play"));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
