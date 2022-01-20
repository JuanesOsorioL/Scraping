package domain.catalog;

import java.util.Objects;

public class Movie {
    private final String id;
    private String name;
    private String year;
    private String duration;
    private String description;
    private String gender;
    private String path;


    public Movie(String id, String name, String year, String duration, String description, String gender, String path) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.description = description;
        this.gender = gender;
        this.path = path;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String year() {
        return year;
    }

    public String duration() {
        return duration;
    }

    public String description() {
        return description;
    }

    public String gender() {
        return gender;
    }

    public String path() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name) && Objects.equals(year, movie.year) && Objects.equals(duration, movie.duration) && Objects.equals(description, movie.description) && Objects.equals(gender, movie.gender) && Objects.equals(path, movie.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, duration, description, gender, path);
    }
}
