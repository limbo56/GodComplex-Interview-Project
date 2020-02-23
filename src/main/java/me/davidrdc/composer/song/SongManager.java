package me.davidrdc.composer.song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import me.davidrdc.composer.Composer;
import org.bukkit.entity.Player;

public class SongManager {

  private static SongManager instance;
  private List<Song> playerSongs;

  private SongManager() {
    this.playerSongs = new ArrayList<>();
  }

  public static SongManager getInstance() {
    return instance == null ? instance = new SongManager() : instance;
  }

  public void playSong(Player player, Song song) {
    player.sendMessage("§aPlaying song: §e§l" + song.getName());
    new SongPlayer(player, song).runTaskTimer(Composer.getPlugin(Composer.class), 0, 20);
  }

  public void addSong(Song song) {
    playerSongs.add(song);
  }

  public void removeSong(Song song) {
    playerSongs.remove(song);
  }

  public List<Song> getSongs(Player player) {
    return playerSongs.stream()
        .filter(song -> song.getAuthor().getUniqueId().equals(player.getUniqueId()))
        .collect(Collectors.toList());
  }

  public void clearSongs() {
    playerSongs.clear();
  }

  public List<Song> getPlayerSongs() {
    return Collections.unmodifiableList(playerSongs);
  }
}
