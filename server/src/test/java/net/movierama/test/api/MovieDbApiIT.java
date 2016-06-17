package net.movierama.test.api;

import net.movierama.test.base.TestConfig;
import net.movierama.test.domain.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class MovieDbApiIT {

    @Autowired
    private MovieDbApi movieDbApi;

    @Test
    public void getNowPlayingMovies_Test() {
        final List<Movie> movieList = movieDbApi.getNowPlayingMovies();
        assertNotNull(movieList);
        final Movie movie = movieList.get(0);
        assertNotNull(movie.getId());
        assertNotNull(movie.getTitle());
        assertNotNull(movie.getDescription());
        assertNotNull(movie.getActorList());
        assertNotNull(movie.getReviewList());
    }

    @Test
    public void searchMovie_Test() {
        final List<Movie> movieList = movieDbApi.searchMovie("The Big Lebowski");
        assertNotNull(movieList);
        final Movie movie = movieList.get(0);
        assertNotNull(movie.getId());
        assertNotNull(movie.getTitle());
        assertNotNull(movie.getDescription());
        assertNotNull(movie.getActorList());
        assertNotNull(movie.getReviewList());
    }
}
