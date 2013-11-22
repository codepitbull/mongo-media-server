package de.codepitbull.mongodb.ressource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Jochen Mader
 */
public class Genres {
    private List<String> genres = new ArrayList<String>();

    public List<String> getGenres() {
        return Collections.unmodifiableList(genres);
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void addAll(Collection<String> genres) {
        this.genres.addAll(genres);
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }

    public void removeGenre(String genre) {
        genres.remove(genre);
    }

}
