package de.codepitbull.mongodb.ressource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Jochen Mader
 */
public class Artists {
    private List<String> artists = new ArrayList<String>();

    public List<String> getArtists() {
        return Collections.unmodifiableList(artists);
    }

    public void addAll(Collection<String> artists) {
        this.artists.addAll(artists);
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public void addArtist(String artist) {
        artists.add(artist);
    }

    public void removeArtist(String artist) {
        artists.remove(artist);
    }

}
