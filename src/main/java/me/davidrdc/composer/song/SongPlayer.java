package me.davidrdc.composer.song;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * A {@link BukkitRunnable} that plays all the {@link Note}s in a {@link Song} to a {@link Player}
 *
 * @author David Rodriguez
 */
public class SongPlayer extends BukkitRunnable {

    private Player player;
    private Map<Instrument, List<Note>> instruments;

    /**
     * Constructor
     *
     * @param player to play song to
     * @param song   to play
     */
    public SongPlayer(Player player, Song song) {
        this.player = player;
        this.instruments =
              song.getInstrumentMap().entrySet().stream()
                    .collect(
                          Collectors.toMap(
                                Map.Entry::getKey,
                                e -> new LinkedList<>(Arrays.asList(e.getValue()))));
    }

    @Override
    public void run() {
        if (instruments.values().stream().mapToInt(List::size).sum() == 0) {
            player.sendMessage("§aFinished playing");
            cancel();
        }

        // Play notes
        instruments.forEach(
              (instrument, notes) -> {
                  Iterator<Note> noteIterator = notes.iterator();
                  if (!noteIterator.hasNext()) {
                      return;
                  }
                  Note note = noteIterator.next();
                  if (note == null) {
                      noteIterator.remove();
                      return;
                  }
                  player.playNote(player.getLocation(), instrument, note);
                  noteIterator.remove();
              });
    }
}
