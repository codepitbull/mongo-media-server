package de.codepitbull.mongodb.ressource;

import de.codepitbull.mongodb.repository.Song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Jochen Mader
 */
public class Songs {
    private List<Song> songs = new ArrayList<Song>();

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addAll(Collection<Song> songs) {
        this.songs.addAll(songs);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

}
