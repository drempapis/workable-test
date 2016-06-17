package net.movierama.test.domain;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    private int id;

    private String title;

    private String description;

    private String releaseDate;

    private List<Actor> actorList;

    private String bestActors;

    private List<Review> reviewList;

    private int reviewsNumber;

    public Movie() {
    }

    public Movie(final String title) {
        this.title = title;
    }

    public Movie(int id, String title, String description, String releaseDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public String getBestActors() {
        return bestActors;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public int getReviewsNumber() {
        return reviewsNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
        setBestActors(actorList);
    }

    private void setBestActors(List<Actor> actorList) {
        final StringBuffer actors = new StringBuffer();
        actorList.stream().limit(4).forEach(actor -> actors.append(" " + actor.getName() + " "));
        this.bestActors = actors.toString();
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        this.reviewsNumber = reviewList.size();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", actorList=" + actorList +
                ", reviewList=" + reviewList +
                '}';
    }
}