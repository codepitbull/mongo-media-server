from pymongo import MongoClient
import gridfs
import json
from glob import glob
from mutagen.easyid3 import EasyID3

import fnmatch
import os

client = MongoClient()
db = client.test
fs = gridfs.GridFS(db)

matches = []
for root, dirnames, filenames in os.walk('/Users/jmader/Downloads/mp3test/'):
  for filename in fnmatch.filter(filenames, '*.mp3'):
      matches.append(os.path.join(root, filename))
for filename in matches:
	mp3info = EasyID3(filename)
	fileId = fs.put(open(filename, 'r'))
	song = dict()
	song["run"] = 2
	song["filename"] = filename
	song["fileId"] = fileId 
	for k, v in mp3info.items():
		song[k]=v[0]
	db.song.insert(song)
	#db.data.insert(json.dumps(dict(mp3info)))	

