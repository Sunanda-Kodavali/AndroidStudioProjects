package org.dci.starwars;

import java.util.Objects;

public class StarWarsFilm {
    // 4 - Create a POJO class to hold the required Info: Year, Director, Title
    private final String year;
    private final String title;
    private final String director;

    public StarWarsFilm(String year, String title, String director) {
        this.year = year;
        this.title = title;
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "StarWars{" +
                "year='" + year + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarWarsFilm starWarsFilm = (StarWarsFilm) o;
        return Objects.equals(year, starWarsFilm.year) && Objects.equals(title, starWarsFilm.title) && Objects.equals(director, starWarsFilm.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, title, director);
    }
}
