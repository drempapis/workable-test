package net.movierama.test.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.movierama.test.domain.Movie;

import java.util.List;

public class MovieInfoRotten extends BaseMovie<Movie> {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("year")
    private String year;

    @JsonProperty("abridged_cast")
    private List<MovieCastRotten> cast;

    @JsonProperty("synopsis")
    private String synopsis;

    public Movie mapEntityToDomain() {
        return new Movie(id, title, synopsis, mapReleaseDate(year));
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public List<MovieCastRotten> getCast() {
        return cast;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
