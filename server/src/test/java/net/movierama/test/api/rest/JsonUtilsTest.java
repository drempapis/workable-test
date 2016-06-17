package net.movierama.test.api.rest;

import net.movierama.test.api.model.*;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class JsonUtilsTest {

    @Test
    public void deserialize_MovieInfoMovieDb_Test() throws IOException {
        final String json = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("model/movieInfoMovieDb.json"));
        final List<MovieInfoMovieDb> movieDbList = JsonUtils.deserializeAsList(json, MovieInfoMovieDb.class);
        assertNotNull(movieDbList);
        MovieInfoMovieDb movieDb = movieDbList.get(0);
        assertNotNull(movieDb.getId());
        assertNotNull(movieDb.getOverview());
        assertNotNull(movieDb.getReleaseDate());
        assertNotNull(movieDb.getTitle());
    }

    @Test
    public void deserialize_MovieCastMovieDb_Test() throws IOException {
        final String json = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("model/movieCastMovieDb.json"));
        final List<MovieCastMovieDb> movieDbList = JsonUtils.deserializeAsList(json, MovieCastMovieDb.class);
        assertNotNull(movieDbList);
        assertEquals(2, movieDbList.size());
        MovieCastMovieDb movieDb = movieDbList.get(0);
        assertNotNull(movieDb.getId());
        assertNotNull(movieDb.getCastId());
        assertNotNull(movieDb.getCharacter());
        assertNotNull(movieDb.getCreditId());
        assertNotNull(movieDb.getName());
        assertNotNull(movieDb.getOrder());
        assertNotNull(movieDb.getProfilePath());
    }

    @Test
    public void deserialize_MovieReviewMovieDb_Test() throws IOException {
        final String json = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("model/movieReviewMovieDb.json"));
        final List<MovieReviewMovieDb> movieDbList = JsonUtils.deserializeAsList(json, MovieReviewMovieDb.class);
        assertNotNull(movieDbList);
        MovieReviewMovieDb movieDb = movieDbList.get(0);
        assertNotNull(movieDb.getId());
        assertNotNull(movieDb.getAuthor());
        assertNotNull(movieDb.getContent());
    }

    @Test
    public void deserialize_MovieInfoRotten_Test() throws IOException {
        final String json = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("model/movieInfoRotten.json"));
        final List<MovieInfoRotten> movieDbList = JsonUtils.deserializeAsList(json, MovieInfoRotten.class);
        assertNotNull(movieDbList);
        MovieInfoRotten movieDb = movieDbList.get(0);
        assertNotNull(movieDb.getId());
        assertNotNull(movieDb.getCast());
        assertNotNull(movieDb.getSynopsis());
        assertNotNull(movieDb.getTitle());
        assertNotNull(movieDb.getYear());
    }

    @Test
    public void deserialize_MovieCastRotten_Test() throws IOException {
        final String json = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("model/movieCastRotten.json"));
        final List<MovieCastRotten> movieDbList = JsonUtils.deserializeAsList(json, MovieCastRotten.class);
        assertNotNull(movieDbList);
        assertEquals(2, movieDbList.size());
        MovieCastRotten movieDb = movieDbList.get(0);
        assertNotNull(movieDb.getId());
        assertNotNull(movieDb.getCharacters());
        assertNotNull(movieDb.getName());
    }

    @Test
    public void deserialize_MovieReviewRotten_Test() throws IOException {
        final String json = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("model/movieReviewMovieDb.json"));
        final List<MovieReviewMovieDb> movieDbList = JsonUtils.deserializeAsList(json, MovieReviewMovieDb.class);
        assertNotNull(movieDbList);
        MovieReviewMovieDb movieDb = movieDbList.get(0);
        assertNotNull(movieDb.getId());
        assertNotNull(movieDb.getAuthor());
        assertNotNull(movieDb.getContent());
    }
}
