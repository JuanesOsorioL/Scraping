package domain.catalog.event;

import domain.generic.DomainEvent;

public class MovieAssigned extends DomainEvent {
    private final String movieId;
    private final String name;
    private final String year;
    private final String duration;
    private final String description;
    private final String gender;
    private final String path;


    public MovieAssigned(String movieId, String name, String year, String duration, String description, String gender, String path) {
        super("sofkau.catalog.MovieAssigned");
        this.movieId = movieId;
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.description = description;
        this.gender = gender;
        this.path=path;
    }

    public String getPath() {
        return path;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String getGender() {
        return gender;
    }
}
