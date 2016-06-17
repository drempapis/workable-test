package net.movierama.test.controller;

import net.movierama.test.controller.response.MoviesResponse;
import net.movierama.test.domain.Movie;
import net.movierama.test.service.MoviesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MoviesController {

    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    @Autowired
    private MoviesService moviesService;

    @RequestMapping(value = "/now_playing",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MoviesResponse getNowPlayingMovies() {
        final List<Movie> movieList = moviesService.getNowPlayingMovies();
        logger.debug("[/movies/now_playing]: movies:[{}]", movieList.size());
        return new MoviesResponse(movieList);
    }

    @RequestMapping(value = "/search/{text}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MoviesResponse getSearchResults(final @PathVariable("text") String text) {
        final List<Movie> movieList = moviesService.searchMovie(text);
        logger.debug("[/movies/no/search/{}]: movies:[{}]", text, movieList.size());
        return new MoviesResponse(movieList);
    }
}
