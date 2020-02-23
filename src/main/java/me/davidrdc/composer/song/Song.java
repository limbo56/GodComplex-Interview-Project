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

/**
 * Represents a song composed of certain instruments and notes
 *
 * @author David Rodriguez
 */
public class Song {

    private Player author;
    private String name;
    private Map<Instrument, Note[]> instrumentMap;

    /**
     * Constructor
     *
     * @param author of the song
     * @param name   of the song
     */
    public Song(Player author, String name) {
        this.author = author;
        this.name = name;
        this.instrumentMap = new HashMap<>();
    }

    /**
     * Adds a {@link Instrument} to the song
     *
     * @param instrument to add
     */
    public void addInstrument(Instrument instrument) {
        this.instrumentMap.put(instrument, new Note[8]);
    }

    /**
     * Removes a {@link Instrument} from the song
     *
     * @param instrument to remove
     */
    public void removeInstrument(Instrument instrument) {
        this.instrumentMap.remove(instrument);
    }

    /**
     * Gets whether this song is using a certain {@link Instrument}
     *
     * @param instrument to check for
     * @return {@link Boolean} whether the song has that instrument
     */
    public boolean hasInstrument(Instrument instrument) {
        return this.instrumentMap.containsKey(instrument);
    }

    /**
     * Adds a {@link Note} to the song for a certain {@link Instrument}
     *
     * @param instrument to add note to
     * @param index      where the note is going to be added
     */
    public void addNote(Instrument instrument, int index) {
        Note[] notes = instrumentMap.get(instrument);
        notes[index] = Note.natural(1, Tone.C);
        instrumentMap.replace(instrument, notes);
    }

    /**
     * Removes a {@link Note} from the song for a certain {@link Instrument}
     *
     * @param instrument to remove note from
     * @param index      where the note is located
     */
    public void removeNote(Instrument instrument, int index) {
        Note[] notes = instrumentMap.get(instrument);
        notes[index] = null;
        instrumentMap.replace(instrument, notes);
    }

    /**
     * Gets whether this song contains a certain {@link Note} at a certain index for an {@link
     * Instrument}
     *
     * @param instrument to check for the note
     * @param index      where the note is located
     * @return {@link Boolean} whether the note is present
     */
    public boolean containsNote(Instrument instrument, int index) {
        return instrumentMap.get(instrument)[index] != null;
    }

    /**
     * Gets the author of this song
     *
     * @return {@link Player} author of the song
     */
    public Player getAuthor() {
        return author;
    }

    /**
     * Gets the name of this song
     *
     * @return {@link String} name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a map with all the {@link Instrument}s and their respective {@link Note}s
     *
     * @return {@link Map} of instruments and notes
     */
    public Map<Instrument, Note[]> getInstrumentMap() {
        return new HashMap<>(instrumentMap);
    }

    /**
     * Gets an unmodifiable list of all the {@link Instrument}s in this song
     *
     * @return {@link List} of {@link Instrument}s
     */
    public List<Instrument> getInstruments() {
        return Collections.unmodifiableList(new ArrayList<>(instrumentMap.keySet()));
    }
}
