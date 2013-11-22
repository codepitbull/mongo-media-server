package de.codepitbull.mongodb.ressource;

import com.mongodb.gridfs.GridFSDBFile;
import de.codepitbull.mongodb.repository.Song;
import de.codepitbull.mongodb.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Jochen Mader
 */

@Controller
public class LibraryRessource {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private SongRepository songRepository;

    @RequestMapping(value = "/library", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Links getLinks(HttpServletRequest request) {
        return new Links.Builder()
                .setArtists(request.getRequestURL().append("artists").toString())
                .setGenres(request.getRequestURL().append("genres").toString())
                .setFiles(request.getRequestURL().append("files").toString())
                .setSongs(request.getRequestURL().append("songs").toString()).build();
    }

    @RequestMapping(value = "/library/genres", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Genres getGenres() {
        Genres genres = new Genres();
        for (Object genreName : mongoTemplate.getCollection("song").distinct("genre")) {
            genres.addGenre(genreName.toString());
        }
        return genres;
    }

    @RequestMapping(value = "/library/songs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Songs getSongs(HttpServletRequest request) {
        Songs songs = new Songs();
        for (Song song: songRepository.findAll()) {
            GridFSDBFile file = gridFsTemplate.findOne(query(where("_id").is(song.getFileId())));
            String requestBase = request.getRequestURL().toString().split("song")[0];
            song.setFileHref(requestBase + "files/" + file.getMD5());
            songs.addSong(song);
        }
        return songs;
    }

    @RequestMapping(value = "/library/artists", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Artists getArtists() {
        Artists artists = new Artists();
        artists.addAll(mongoTemplate.getCollection("song").distinct("artist"));
        return artists;
    }

    @RequestMapping(value = "/library/files", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Files getFiles() {
        Files files = new Files();
        files.addAll(mongoTemplate.getCollection("fs.files").distinct("md5"));
        return files;
    }

    @RequestMapping(value = "/library/files/{md5}", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response, @PathVariable("md5") String md5) throws IOException {
        gridFsTemplate.findOne(query(where("md5").is(md5))).writeTo(response.getOutputStream());
    }

}
