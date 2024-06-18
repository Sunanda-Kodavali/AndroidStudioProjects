package org.dci.starwars;

import java.util.Objects;

public class StarWarsFilm {
    // 4 - Create a POJO class to hold the required Info: Year, Director, Title
    private final String year;
    private final String title;
    private final String director;
    private final String openingCrawl;

    public StarWarsFilm(String year, String title, String director, String openingCrawl) {
        this.year = year;
        this.title = title;
        this.director = director;
        this.openingCrawl = openingCrawl;
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

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    @Override
    public String toString() {
        return "StarWarsFilm{" +
                "year='" + year + '\'' +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", openingCrawl='" + openingCrawl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarWarsFilm that = (StarWarsFilm) o;
        return Objects.equals(year, that.year) && Objects.equals(title, that.title) && Objects.equals(director, that.director) && Objects.equals(openingCrawl, that.openingCrawl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, title, director, openingCrawl);
    }
}
