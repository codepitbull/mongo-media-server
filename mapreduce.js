var map1 = function() { emit(this.genre, 1); };
var red1 = function(key, val) { return Array.sum(val); };
db.data.mapReduce(map1,red1,{out:"mapped"})