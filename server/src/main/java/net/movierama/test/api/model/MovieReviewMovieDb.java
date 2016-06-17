package net.movierama.test.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.movierama.test.domain.Review;

public class MovieReviewMovieDb extends BaseMovie<Review> {

    @JsonProperty("id")
    private String id;

    @JsonProperty("author")
    private String author;

    @JsonProperty("content")
    private String content;

    public Review mapEntityToDomain() { return new Review(author, content); }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
