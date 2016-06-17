package net.movierama.test.service;

import net.movierama.test.api.MovieDbApi;
import net.movierama.test.api.RottenApi;
import net.movierama.test.domain.Movie;
import net.movierama.test.repository.MoviesCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class MoviesService {

    @Autowired
    private MoviesCacheRepository moviesCacheRepository;

    @Autowired
    private MovieDbApi movieDbApi;

    @Autowired
    private RottenApi rottenApi;

    /**
     * Connects themoviedb and rottentomatoes sources, getting now_playing movie information. Merges the retrieved resutls
     * keeping longest description for common movies, and caches all movies.
     *
     * @return The merged list of the now playing movies.
     */
    public List<Movie> getNowPlayingMovies() {
        final List<Movie> movieList = getMergedMovieLists(movieDbApi.getNowPlayingMovies(), rottenApi.getNowPlayingMovies());
        cacheMovies(movieList);
        return movieList;
    }

    /**
     * Searches for a title in the local cache. If the movie is found it is returned to client. If it is not in the cache,
     * connects themoviedb and rottentomatoes sources, searching for the specified text. Merges the retrieved resutls
     * keeping longest description for common movies, and caches all movies.
     *
     * @param title The title or fragment to search for.
     * @return The merged list of the searching results.
     */
    public List<Movie> searchMovie(final String title) {
        final Movie cachedMovie = moviesCacheRepository.findByTitle(title);

        List<Movie> movieList;
        if (cachedMovie != null) {
            movieList = Arrays.asList(cachedMovie);
        } else {
            movieList = getMergedMovieLists(movieDbApi.searchMovie(title), rottenApi.searchMovie(title));
            cacheMovies(movieList);
        }
        return movieList;
    }

    private void cacheMovies(final List<Movie> movieList) {
        movieList.stream().forEach(movie -> moviesCacheRepository.cacheMovie(movie.getTitle(), movie));
    }

    private List<Movie> getMergedMovieLists(final List<Movie> firstMovieList, final List<Movie> secondMovieList) {
        final Map<String, Movie> moviesMap = new HashMap<>();
        firstMovieList.stream().forEach(movie -> moviesMap.put(movie.getTitle(), movie));

        secondMovieList.stream().forEach(movie -> {
            if (moviesMap.containsKey(movie.getTitle())) {
                Movie movieInMap = moviesMap.get(movie.getTitle());
                movieInMap.setDescription(getLongestDescription(movieInMap, movie));
                movieInMap.setReviewList(Stream.concat(movieInMap.getReviewList().stream(), movie.getReviewList().
                        stream()).collect(toList()));
                moviesMap.put(movie.getTitle(), movieInMap);
            } else {
                moviesMap.put(movie.getTitle(), movie);
            }
        });
        return new ArrayList<Movie>(moviesMap.values());
    }

    private String getLongestDescription(final Movie firstMovie, final Movie secondMovie) {
        String description;
        if (firstMovie.getDescription().length() >= secondMovie.getDescription().length()) {
            description = firstMovie.getDescription();
        } else {
            description = secondMovie.getDescription();
        }
        return description;
    }
}
