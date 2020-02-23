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
 * saved by all {@link Player}s
 *
 * @author David Rodriguez
 */
public class ShareMenu extends AbstractMenu {

    /**
     * Constructor
     *
     * @param owner of the menu
     */
    public ShareMenu(Player owner) {
        super(owner, "&ePublic Songs", 54);
    }

    @Override
    protected void initialize() {
        SongManager songManager = SongManager.getInstance();

        // Control options
        setItem(53, new MenuItem(getCreateItem(), () -> new ComposeMenu(getOwner()).open()));
        setItem(52, new MenuItem(getSongItems(), () -> new SongsMenu(getOwner()).open()));

        // Add songs to menu
        int slot = 9;
        for (Song song : songManager.getPlayerSongs()) {
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

    private ItemStack getCreateItem() {
        ItemStack itemStack = new ItemStack(Material.CLOCK, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§aCreate your own song");
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack getSongItems() {
        ItemStack itemStack = new ItemStack(Material.MUSIC_DISC_CAT, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§eYour songs");
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
