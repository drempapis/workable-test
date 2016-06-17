package net.movierama.test.repository;

import net.movierama.test.base.TestConfig;
import net.movierama.test.domain.Movie;
import net.movierama.test.service.MoviesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class MovieCacheRepositoryIT {

    @Autowired
    private MoviesCacheRepository moviesCacheRepository;

    @Autowired
    private MoviesService moviesService;

    @Test
    public void foundInCache_Test() {
        final String title = "Test";
        final Movie movie = new Movie();
        movie.setTitle(title);
        moviesCacheRepository.cacheMovie(title, movie);

        final Movie cachedMovie = moviesCacheRepository.findByTitle(title);
        assertEquals(title, cachedMovie.getTitle());
    }

    @Test
    public void notFoundInCache_Test() {
        final String title = "Test";
        final Movie movie = new Movie();
        movie.setTitle(title);
        moviesCacheRepository.cacheMovie(title, movie);

        final Movie cachedMovie = moviesCacheRepository.findByTitle("Other");
        assertNull(cachedMovie);
    }
}
