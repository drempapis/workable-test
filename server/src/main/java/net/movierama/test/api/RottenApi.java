package net.movierama.test.api;

import net.movierama.test.api.rest.ResponseDto;
import net.movierama.test.api.model.MovieCastRotten;
import net.movierama.test.api.model.MovieInfoRotten;
import net.movierama.test.api.model.MovieReviewRotten;
import net.movierama.test.domain.Actor;
import net.movierama.test.domain.Movie;
import net.movierama.test.domain.Review;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RottenApi extends BaseApi {

    private static final String HOST = "api.rottentomatoes.com/api/public/v1.0";

    /**
     * Connects rottentomatoes source, getting now_playing movie information.
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
     * Connects rottentomatoes source, searching for the specified text.
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
        final ResponseDto responseDto = connect().getResponse(HOST, "/lists/movies/opening.json", getParams());
        return getListFromResponse(responseDto, "movies", MovieInfoRotten.class);
    }

    private List<Actor> getMovieActors(final int movieId) {
        final ResponseDto responseDto = connect().getResponse(HOST, String.format("/movies/%s/cast.json", movieId), getParams());
        return getListFromResponse(responseDto, "cast", MovieCastRotten.class);
    }

    private List<Review> getMovieReviews(final int movieId) {
        final ResponseDto responseDto = connect().getResponse(HOST, String.format("/movies/%s/reviews.json", movieId), getParams());
        return getListFromResponse(responseDto, "reviews", MovieReviewRotten.class);
    }

    private List<Movie> searchByTitle(final String title) {
        final Map<String, String> params = getParams();
        params.put("q", title);
        final ResponseDto responseDto = connect().getResponse(HOST, "/movies.json", params);
        return getListFromResponse(responseDto, "movies", MovieInfoRotten.class);
    }

    private Map<String, String> getParams() {
        final Map<String, String> params = new HashMap<>();
        params.put("apikey", "qtqep7qydngcc7grk4r4hyd9");
        return params;
    }
}
