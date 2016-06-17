package net.movierama.test.domain;

import java.io.Serializable;

public class Review implements Serializable {

    private String author;

    private String review;

    public Review() {
    }

    public Review(final String author, final String review) {
        this.author = author;
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
