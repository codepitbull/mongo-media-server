package de.codepitbull.mongodb.repository;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Jochen Mader
 */
public class Song {
    @Id
    private ObjectId id;
    private String album;
    private String run;
    private String artist;
    private String title;
    private String encodedby;
    private String date;
    private String tracknumber;
    private String discnumber;
    private String genre;
    private String filename;
    private ObjectId fileId;
    private String fileHref;

    public String getFileHref() {
        return fileHref;
    }

    public void setFileHref(String fileHref) {
        this.fileHref = fileHref;
    }

    public ObjectId getFileId() {
        return fileId;
    }

    public void setFileId(ObjectId fileId) {
        this.fileId = fileId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEncodedby() {
        return encodedby;
    }

    public void setEncodedby(String encodedby) {
        this.encodedby = encodedby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTracknumber() {
        return tracknumber;
    }

    public void setTracknumber(String tracknumber) {
        this.tracknumber = tracknumber;
    }

    public String getDiscnumber() {
        return discnumber;
    }

    public void setDiscnumber(String discnumber) {
        this.discnumber = discnumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
