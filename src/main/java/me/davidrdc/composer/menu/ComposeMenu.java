package me.davidrdc.composer.menu;

import java.util.List;
import java.util.stream.IntStream;
import me.davidrdc.composer.song.Song;
import me.davidrdc.composer.song.SongManager;
import me.davidrdc.composer.util.AbstractMenu;
import me.davidrdc.composer.util.MenuItem;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * This class represents an {@link AbstractMenu} where a {@link Player} can compose {@link Song}
 *
 * @author David Rodriguez
 */
public class ComposeMenu extends AbstractMenu {

    private Song song;

    /**
     * Constructor
     *
     * @param owner of the menu
     */
    public ComposeMenu(Player owner) {
        super(owner, "&eCompose Song", 54);
        this.song = new Song(owner, "Song " + SongManager.getInstance().getPlayerSongs().size());
    }

    @Override
    protected void initialize() {
        SongManager songManager = SongManager.getInstance();

        // Control items
        setItem(53, new MenuItem(getSaveButton(), getSaveAction(songManager)));
        setItem(52, new MenuItem(getCancelButton(), () -> new ShareMenu(getOwner()).open()));
        setItem(45, new MenuItem(getAddButton(), () -> new AddMenu(getOwner(), this).open()));

        // Instruments
        List<Instrument> instrumentList = song.getInstruments();
        for (int i = 0; i < 5; i++) {
            if (i >= instrumentList.size()) {
                return;
            }

            Instrument instrument = instrumentList.get(i);
            int slot = i * 9;

            setItem(slot, getInstrumentItem(instrument));

            // Set note actions
            IntStream.range(1, 9)
                  .forEach(value -> setItem(slot + value, getNoteItem(instrument, value - 1)));
        }
    }

    /**
     * Gets the song created by this menu
     *
     * @return {@link Song} created
     */
    public Song getSong() {
        return song;
    }

    private Runnable getSaveAction(SongManager songManager) {
        return () -> {
            getOwner().sendMessage("§aSong created: §e§l" + song.getName());
            songManager.addSong(song);
            new ShareMenu(getOwner()).open();
        };
    }

    private MenuItem getNoteItem(Instrument instrument, int index) {
        Material type =
              song.containsNote(instrument, index) ? Material.GLASS_PANE : Material.BEDROCK;
        ItemStack itemStack = new ItemStack(type, 1);
        return new MenuItem(itemStack, getNoteItemAction(instrument, index));
    }

    private Runnable getNoteItemAction(Instrument instrument, int index) {
        return () -> {
            if (!song.containsNote(instrument, index)) {
                song.addNote(instrument, index);
            } else {
                song.removeNote(instrument, index);
            }
            open();
        };
    }

    private MenuItem getInstrumentItem(Instrument instrument) {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§e" + instrument.name().toUpperCase());
        itemStack.setItemMeta(meta);
        return new MenuItem(itemStack, getInstrumentItemAction(instrument));
    }

    private Runnable getInstrumentItemAction(Instrument instrument) {
        return () -> getOwner()
              .playNote(getOwner().getLocation(), instrument, Note.natural(5, Tone.C));
    }

    private ItemStack getSaveButton() {
        ItemStack itemStack = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§aSave Song");
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack getCancelButton() {
        ItemStack itemStack = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§cCancel");
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack getAddButton() {
        ItemStack itemStack = new ItemStack(Material.NOTE_BLOCK, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§aAdd Instrument");
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
