package de.codepitbull.mongodb.ressource;

import de.codepitbull.mongodb.repository.Song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Jochen Mader
 */
public class Files {
    private List<String> files = new ArrayList<String>();

    public List<String> getFiles() {
        return Collections.unmodifiableList(files);
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public void addAll(Collection<String> files) {
        this.files.addAll(files);
    }

    public void addFile(String file) {
        files.add(file);
    }

    public void removeFile(String file) {
        files.remove(file);
    }

}
