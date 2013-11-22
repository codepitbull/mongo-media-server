MongoDB Media Server

_The included code is a show case, not a full media server! This is also a somewhat older project, if I missed something feel free to add an issue on GitHub._

So I've been going through _playground_ folder on my hard disk. That's the place I store all my projects I did for showcasing or simply to play around with new things.
And I found a little something I did about a year ago for the Senacor DevCon. It's a small media server project based on [Spring Data](http://projects.spring.io/spring-data/) and [MongoDB](http://www.mongodb.org/).

I am releasing it because I think it's a great example for the simplicity of Spring Data combined with SpringMVC.
__NOTE:  There's a a python script included to import your MP3s into the database.  You will need [Mutagen](https://code.google.com/p/mutagen/) on your machine to use it.__

All the interesting things happen in the LibraryRessource. Let's take a look at the most important parts. 
The things we nee to work with are the _SongRepository_ (a simple Spring Data repository), _MongoTemplate_ (here be CRUD) and the _GridFsTemplate_, which is required for documents >16 MB (see [GridFS](http://docs.mongodb.org/manual/core/gridfs/)).

```java
@Controller
public class LibraryRessource {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private SongRepository songRepository;
```

Most of the _@RequestMapping_-annotated classes should be self explaining so I will foxus on the "tricky" ones.
_getSongs(..)__ is responsible for assembling the Song-JSON documents which contain a link to the GridFS-file to start streaming. To be able to assemble I have to git hold of the MD5-checksum to reference it.

```java
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
```

The actual streaming is done in _getFile(..)_. Thanks to the fluent-API of _GridFsTemplate_ it turned out that the trickiest part of my application could be done in a single line of code. The only thing to be aware of is that getting hold of the _HttpServletResponse_ used by your request is by adding it to the method signature.

```java

    @RequestMapping(value = "/library/files/{md5}", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response, @PathVariable("md5") String md5) throws IOException {
        gridFsTemplate.findOne(query(where("md5").is(md5))).writeTo(response.getOutputStream());
    }
```

The generated URLs can be accessed using [VLC](https://www.videolan.org/vlc/).