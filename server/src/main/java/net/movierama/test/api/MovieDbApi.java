package net.movierama.test.api;

import net.movierama.test.api.model.*;
import net.movierama.test.api.rest.JsonUtils;
import net.movierama.test.api.rest.ResponseDto;
import net.movierama.test.api.rest.RestApiHelper;
import net.movierama.test.domain.Actor;
import net.movierama.test.domain.Movie;
import net.movierama.test.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieDbApi extends BaseApi {

    private static final String HOST = "api.themoviedb.org/3";

    @Autowired
    private RestApiHelper restApiHelper;

    /**
     * Connects themoviedb source, getting now_playing movie information.
     *
     * @return The list of the now playing movies.
     */
    public List<Movie> getNowPlayingMovies() {
        final List<Movie> movieList = getNowPlaying();
        movieList.stream().forEach(movie -> {
            movie.setActorList(getMovieActors(movie.getId()));
            movie.setReviewList(getMovieReviews(movie.getId()));
        });
        return movieList;
    }

    /**
     * Connects themoviedb source, searching for the specified text.
     *
     * @param title The title or fragment to search for.
     * @return The list of the searching results.
     */
    public List<Movie> searchMovie(final String title) {
        final List<Movie> movieList = searchByTitle(title);
        movieList.stream().forEach(movie -> {
            movie.setActorList(getMovieActors(movie.getId()));
            movie.setReviewList(getMovieReviews(movie.getId()));
        });
        return movieList;
    }

    private List<Movie> getNowPlaying() {
        final ResponseDto responseDto = restApiHelper.getResponse(HOST, "/movie/now_playing", getParams());
        return getListFromResponse(responseDto, "results", MovieInfoMovieDb.class);
    }

    private List<Actor> getMovieActors(final int movieId) {
        final ResponseDto responseDto = restApiHelper.getResponse(HOST, String.format("/movie/%s/credits", movieId), getParams());
        return getListFromResponse(responseDto, "cast", MovieCastMovieDb.class);
    }

    private List<Review> getMovieReviews(final int movieId) {
        final ResponseDto responseDto = restApiHelper.getResponse(HOST, String.format("/movie/%s/reviews", movieId), getParams());
        return getListFromResponse(responseDto, "results", MovieReviewMovieDb.class);
    }

    private List<Movie> searchByTitle(final String title) {
        final Map<String, String> params = getParams();
        params.put("query", title);
        final ResponseDto responseDto = restApiHelper.getResponse(HOST, "/search/movie", params);
        return getListFromResponse(responseDto, "results", MovieInfoMovieDb.class);
    }

    private Map<String, String> getParams() {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", "186b266209c2da50f898b7977e2a44dd");
        return params;
    }
}
