package net.movierama.test.domain;


import java.io.Serializable;

public class Actor implements Serializable {

    private String name;

    public Actor(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                '}';
    }
}
