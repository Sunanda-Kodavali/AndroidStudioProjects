package org.dci.starwars;

import java.util.Objects;

public class StarWars {
    // 4 - Create a POJO class to hold the required Info: Year, Director, Title
    private final String year;
    private final String title;
    private final String director;

    public StarWars(String year, String title, String director) {
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
        StarWars starWars = (StarWars) o;
        return Objects.equals(year, starWars.year) && Objects.equals(title, starWars.title) && Objects.equals(director, starWars.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, title, director);
    }
}
