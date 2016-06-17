package net.movierama.test.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.movierama.test.domain.Movie;

public class MovieInfoMovieDb extends BaseMovie<Movie> {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("overview")
    private String overview;

    public Movie mapEntityToDomain() { return new Movie(id, title, overview, mapReleaseDate(releaseDate)); }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }
}
