package de.codepitbull.mongodb.ressource;

/**
 * @author Jochen Mader
 */
public class Links {
    private String artists;
    private String Files;
    private String Genres;
    private String Songs;

    private Links() {
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getFiles() {
        return Files;
    }

    public void setFiles(String files) {
        Files = files;
    }

    public String getGenres() {
        return Genres;
    }

    public void setGenres(String genres) {
        Genres = genres;
    }

    public String getSongs() {
        return Songs;
    }

    public void setSongs(String songs) {
        Songs = songs;
    }

    public static class Builder {
        private String artists;
        private String files;
        private String genres;
        private String songs;

        public Builder setArtists(String artists) {
            this.artists = artists;
            return this;
        }

        public Builder setFiles(String files) {
            this.files = files;
            return this;
        }

        public Builder setGenres(String genres) {
            this.genres = genres;
            return this;
        }

        public Builder setSongs(String songs) {
            this.songs = songs;
            return this;
        }

        public Links build() {
            Links ret = new Links();
            ret.setArtists(artists);
            ret.setFiles(files);
            ret.setGenres(genres);
            ret.setSongs(songs);
            return ret;
        }
    }
}
