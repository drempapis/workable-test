package net.movierama.test.api.model;

public abstract class BaseMovie<T> {

    protected String mapReleaseDate(final String releaseDate) {
        String date = releaseDate;
        if(releaseDate.contains("-")) {
            date = releaseDate.split("-")[0];
        }
        return date;
    }

    public abstract T mapEntityToDomain();
}
