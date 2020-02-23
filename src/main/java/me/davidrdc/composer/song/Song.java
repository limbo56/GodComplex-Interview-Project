package me.davidrdc.composer.song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;

public class Song {

  private Player author;
  private String name;
  private Map<Instrument, Note[]> instrumentMap;

  public Song(Player author, String name) {
    this.author = author;
    this.name = name;
    this.instrumentMap = new HashMap<>();
  }

  public void addInstrument(Instrument instrument) {
    this.instrumentMap.put(instrument, new Note[8]);
  }

  public void removeInstrument(Instrument instrument) {
    this.instrumentMap.remove(instrument);
  }

  public boolean hasInstrument(Instrument instrument) {
    return this.instrumentMap.containsKey(instrument);
  }

  public void addNote(Instrument instrument, int index) {
    Note[] notes = instrumentMap.get(instrument);
    notes[index] = Note.natural(1, Tone.C);
    instrumentMap.replace(instrument, notes);
  }

  public void removeNote(Instrument instrument, int index) {
    Note[] notes = instrumentMap.get(instrument);
    notes[index] = null;
    instrumentMap.replace(instrument, notes);
  }

  public boolean containsNote(Instrument instrument, int index) {
    return instrumentMap.get(instrument)[index] != null;
  }

  public Player getAuthor() {
    return author;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<Instrument, Note[]> getInstrumentMap() {
    return new HashMap<>(instrumentMap);
  }

  public List<Instrument> getInstruments() {
    return Collections.unmodifiableList(new ArrayList<>(instrumentMap.keySet()));
  }
}
