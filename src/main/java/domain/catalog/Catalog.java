package domain.catalog;

import domain.catalog.event.CatalogCreated;
import domain.catalog.event.MovieAssigned;
import domain.generic.AggregateRoot;
import domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Catalog extends AggregateRoot {
    protected Map<String, Movie> movies;
    protected String name;

    public Catalog(String catalogId, String name) {
        super(catalogId);
        subscribe(new CatalogEventChange(this));
        appendChange(new CatalogCreated(name)).apply();
    }

    public void addMovie(String id, String name, String year, String duration, String description, String gender){
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(year);
        Objects.requireNonNull(duration);
        Objects.requireNonNull(description);
        Objects.requireNonNull(gender);
        appendChange(new MovieAssigned(id, name, year,duration,description,gender)).apply();
    }

    private Catalog(String id){
        super(id);
        subscribe(new CatalogEventChange(this));
    }

    public static Catalog from(String id, List<DomainEvent> events){
        var catalog = new Catalog(id);
        events.forEach(catalog::applyEvent);
        return catalog;
    }

    public Map<String, Movie> movies() {
        return movies;
    }

    public String name() {
        return name;
    }
}
