package net.movierama.test.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.movierama.test.domain.Actor;

public class MovieCastMovieDb extends BaseMovie<Actor> {

    @JsonProperty("cast_id")
    private int castId;

    @JsonProperty("character")
    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("order")
    private int order;

    @JsonProperty("profile_path")
    private String profilePath;

    public Actor mapEntityToDomain() { return new Actor(name); }

    public int getCastId() {
        return castId;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
