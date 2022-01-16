package domain.catalog.event;

import domain.generic.DomainEvent;

public class MovieAssigned extends DomainEvent {
    private final String id;
    private final String name;
    private final String year;
    private final String duration;
    private final String description;
    private final String gender;

    public MovieAssigned(String id, String name, String year, String duration, String description, String gender) {
        super("sofkau.catalog.MovieAssigned");
        this.id = id;
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.description = description;
        this.gender = gender;
    }

    public String getId() {
        return id;
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
