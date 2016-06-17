package net.movierama.test.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.movierama.test.domain.Actor;

import java.util.List;

public class MovieCastRotten extends BaseMovie<Actor> {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("characters")
    private List<String> characters;

    public Actor mapEntityToDomain() { return new Actor(name); }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCharacters() {
        return characters;
    }
}
