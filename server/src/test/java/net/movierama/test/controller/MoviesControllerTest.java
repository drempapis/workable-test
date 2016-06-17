package net.movierama.test.controller;

import net.movierama.test.service.MoviesService;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class MoviesControllerTest {

    @InjectMocks
    private MoviesController moviesController;

    @Mock
    private MoviesService moviesService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(moviesController).build();
    }

    @Test
    public void getNowPlayingMovies() throws Exception {
        this.mockMvc.perform(get("/movies/now_playing").
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)).
                andExpect(status().is(HttpStatus.SC_OK)).andReturn();
    }

    @Test
    public void getSearchResults() throws Exception {
        this.mockMvc.perform(get("/movies/search/{text}", "test").
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)).
                andExpect(status().is(HttpStatus.SC_OK)).andReturn();
    }

    @Test
    public void getSearchResults_EmptyText() throws Exception {
        this.mockMvc.perform(get("/movies/search/{text}", "").
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)).
                andExpect(status().is(HttpStatus.SC_NOT_FOUND)).andReturn();
    }
}
