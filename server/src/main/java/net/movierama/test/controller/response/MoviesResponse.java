package net.movierama.test.controller.response;

import net.movierama.test.domain.Movie;

import java.util.List;

public class MoviesResponse {

    private List<Movie> movieList;

    public MoviesResponse(final List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
