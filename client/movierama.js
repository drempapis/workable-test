var Client = require('node-rest-client').Client;

exports.nowPlaying = function(callback) {
    client = new Client();
    args = {
        headers:{"content-type":"application/x-www-form-urlencoded"}
    };

    var req = client.get("http://localhost:8080/movies/now_playing", args,
        function(data, response) {
            callback(data);
    });

    req.on('error', function(err){
    });
}

exports.searchMovie = function(title, callback) {
    client = new Client();
    args = {
        path:{"title":title},
        headers:{"content-type":"application/x-www-form-urlencoded"}
    };

    var req = client.get("http://localhost:8080/movies/search/${title}", args,
        function(data, response) {
            callback(data);
    });

    req.on('error', function(err){
    });
}
