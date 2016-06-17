var express = require('express');
var json2html = require('node-json2html');
var movierama = require('./movierama');
var app = express();

app.use(express.static('public'));

app.get('/index.html', function (req, res) {
   res.sendFile( __dirname + "/" + "index.html" );
})

var transform = [
                    {"tag":"h3","html":"${title}"},
                    {"tag":"p","html":"${releaseDate} - Starring: ${bestActors}"},
                    {"tag":"p","html":"${description}"},
                    {"tag":"p","html":"${reviewsNumber} Reviews"}
                ];

app.get('/now_playing', function (req, res) {
    movierama.nowPlaying(function(movieramaResponse) {
        var jsonObj = JSON.parse(JSON.stringify(movieramaResponse));
        var html = json2html.transform(jsonObj.movieList,transform);
        res.send(html);
    });
})

app.get('/search_movie', function (req, res) {
    movierama.searchMovie(req.query.title, function(movieramaResponse){
        var jsonObj = JSON.parse(JSON.stringify(movieramaResponse));
        var html = json2html.transform(jsonObj.movieList,transform);
        res.send(html);
    });
})

var server = app.listen(8081, function () {
  var host = server.address().address
  var port = server.address().port
  console.log("Listening at http://%s:%s", host, port)
})