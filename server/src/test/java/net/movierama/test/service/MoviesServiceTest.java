package net.movierama.test.service;

import net.movierama.test.api.MovieDbApi;
import net.movierama.test.api.RottenApi;
import net.movierama.test.domain.Movie;
import net.movierama.test.domain.Review;
import net.movierama.test.repository.MoviesCacheRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoviesServiceTest {

    @InjectMocks
    private MoviesService moviesService;

    @Mock
    private MoviesCacheRepository movieCacheRepository;

    @Mock
    private MovieDbApi movieDbApi;

    @Mock
    private RottenApi rottenApi;

    @Test
    public void getNowPlayingMovies_Merged_Lists_Test() {
        final Movie movie1 = new Movie("movie");
        movie1.setDescription("Big Description for testing purposes");
        movie1.setReviewList(Arrays.asList(new Review()));
        final Movie movie2 = new Movie("movie");
        movie2.setDescription("Short Description");
        movie2.setReviewList(Arrays.asList(new Review()));

        when(movieDbApi.getNowPlayingMovies()).thenReturn(Arrays.asList(movie1));
        when(rottenApi.getNowPlayingMovies()).thenReturn(Arrays.asList(movie2));
        final List<Movie> movieList = moviesService.getNowPlayingMovies();
        assertNotNull(movieList);
        assertEquals(1, movieList.size());
        assertEquals(2, movieList.get(0).getReviewList().size());
        assertEquals("Big Description for testing purposes", movieList.get(0).getDescription());

        verify(movieDbApi, times(1)).getNowPlayingMovies();
        verify(rottenApi, times(1)).getNowPlayingMovies();
        verify(movieCacheRepository, times(1)).cacheMovie(anyString(), any(Movie.class));
    }

    @Test
    public void getNowPlayingMovies_No_Merged_Lists_Test() {
        final Movie movie1 = new Movie("movie1");
        movie1.setDescription("Big Description for testing purposes");
        movie1.setReviewList(Arrays.asList(new Review()));
        final Movie movie2 = new Movie("movie2");
        movie2.setDescription("Short Description");
        movie2.setReviewList(Arrays.asList(new Review()));

        when(movieDbApi.getNowPlayingMovies()).thenReturn(Arrays.asList(movie1));
        when(rottenApi.getNowPlayingMovies()).thenReturn(Arrays.asList(movie2));
        final List<Movie> movieList = moviesService.getNowPlayingMovies();
        assertNotNull(movieList);
        assertEquals(2, movieList.size());

        verify(movieDbApi, times(1)).getNowPlayingMovies();
        verify(rottenApi, times(1)).getNowPlayingMovies();
        verify(movieCacheRepository, times(2)).cacheMovie(anyString(), any(Movie.class));
    }


    @Test
    public void getNowPlayingMovies_EmptyList_Test() {
        when(movieDbApi.getNowPlayingMovies()).thenReturn(Collections.emptyList());
        final List<Movie> movieList = moviesService.getNowPlayingMovies();
        assertNotNull(movieList);

        verify(movieDbApi, times(1)).getNowPlayingMovies();
        verify(movieCacheRepository, times(0)).cacheMovie(anyString(), any(Movie.class));
    }

    @Test
    public void searchMovie_CachedMovie_Test() {
        when(movieCacheRepository.findByTitle(anyString())).thenReturn(new Movie());
        final List<Movie> movieList = moviesService.searchMovie(anyString());
        assertNotNull(movieList);
        assertEquals(1, movieList.size());

        verify(movieCacheRepository, times(1)).findByTitle(anyString());
        verify(movieDbApi, times(0)).searchMovie(anyString());
        verify(rottenApi, times(0)).searchMovie(anyString());
        verify(movieCacheRepository, times(0)).cacheMovie(anyString(), any(Movie.class));
    }

    @Test
    public void searchMovie_NoCachedMovie_NoMerged_Lists_Test() {
        when(movieCacheRepository.findByTitle(anyString())).thenReturn(null);
        when(movieDbApi.searchMovie(anyString())).thenReturn(Arrays.asList(new Movie("movie1")));
        when(rottenApi.searchMovie(anyString())).thenReturn(Arrays.asList(new Movie("movie2")));
        final List<Movie> movieList = moviesService.searchMovie(anyString());
        assertNotNull(movieList);
        assertEquals(2, movieList.size());

        verify(movieCacheRepository, times(1)).findByTitle(anyString());
        verify(movieDbApi, times(1)).searchMovie(anyString());
        verify(rottenApi, times(1)).searchMovie(anyString());
        verify(movieCacheRepository, times(2)).cacheMovie(anyString(), any(Movie.class));
    }

    @Test
    public void searchMovie_NoCachedMovie_Merged_Lists_Test() {
        final Movie movie1 = new Movie("movie");
        movie1.setDescription("Big Description for testing purposes");
        movie1.setReviewList(Arrays.asList(new Review()));
        final Movie movie2 = new Movie("movie");
        movie2.setDescription("Short Description");
        movie2.setReviewList(Arrays.asList(new Review()));

        when(movieCacheRepository.findByTitle(anyString())).thenReturn(null);
        when(movieDbApi.searchMovie(anyString())).thenReturn(Arrays.asList(movie1));
        when(rottenApi.searchMovie(anyString())).thenReturn(Arrays.asList(movie2));
        final List<Movie> movieList = moviesService.searchMovie(anyString());
        assertNotNull(movieList);
        assertEquals(1, movieList.size());
        assertEquals(2, movieList.get(0).getReviewList().size());
        assertEquals("Big Description for testing purposes", movieList.get(0).getDescription());

        verify(movieCacheRepository, times(1)).findByTitle(anyString());
        verify(movieDbApi, times(1)).searchMovie(anyString());
        verify(rottenApi, times(1)).searchMovie(anyString());
        verify(movieCacheRepository, times(1)).cacheMovie(anyString(), any(Movie.class));
    }
}
