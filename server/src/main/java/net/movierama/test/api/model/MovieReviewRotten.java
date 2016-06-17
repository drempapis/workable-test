package net.movierama.test.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.movierama.test.domain.Review;

public class MovieReviewRotten extends BaseMovie<Review> {

    @JsonProperty("critic")
    private String critic;

    @JsonProperty("date")
    private String date;

    @JsonProperty("publication")
    private String publication;

    @JsonProperty("quote")
    private String quote;

    public Review mapEntityToDomain() {
        return new Review(critic, quote);
    }

    public String getCritic() {
        return critic;
    }

    public String getDate() {
        return date;
    }

    public String getPublication() {
        return publication;
    }

    public String getQuote() {
        return quote;
    }
}
