package me.davidrdc.composer.song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import me.davidrdc.composer.Composer;
import org.bukkit.entity.Player;

/**
 * This singleton acts as a manager for all the {@link Song}s created by players
 *
 * @author David Rodriguez
 */
public class SongManager {

    private static SongManager instance;
    private List<Song> playerSongs;

    private SongManager() {
        this.playerSongs = new ArrayList<>();
    }

    public static SongManager getInstance() {
        return instance == null ? instance = new SongManager() : instance;
    }

    /**
     * Plays a song for a certain player
     *
     * @param player to play song to
     * @param song   to play
     */
    public void playSong(Player player, Song song) {
        player.sendMessage("§aPlaying song: §e§l" + song.getName());
        new SongPlayer(player, song).runTaskTimer(Composer.getPlugin(Composer.class), 0, 20);
    }

    /**
     * Adds a song to the song directory
     *
     * @param song to add
     */
    public void addSong(Song song) {
        playerSongs.add(song);
    }

    /**
     * Removes a song from the song directory
     *
     * @param song to remove
     */
    public void removeSong(Song song) {
        playerSongs.remove(song);
    }

    /**
     * Gets a list of {@link Song}s made by a certain {@link Player}
     *
     * @param player author of the songs
     * @return {@link List} of {@link Song}s made by that {@link Player}
     */
    public List<Song> getSongs(Player player) {
        return playerSongs.stream()
              .filter(song -> song.getAuthor().getUniqueId().equals(player.getUniqueId()))
              .collect(Collectors.toList());
    }

    /**
     * Clears the list of songs in the manager
     */
    public void clearSongs() {
        playerSongs.clear();
    }

    /**
     * Gets an unmodifiable list of all the songs in {@link SongManager#playerSongs}
     *
     * @return Unmodifiable {@link List} of {@link Song}s
     */
    public List<Song> getPlayerSongs() {
        return Collections.unmodifiableList(playerSongs);
    }
}
