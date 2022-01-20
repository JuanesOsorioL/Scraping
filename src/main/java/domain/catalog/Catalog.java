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
        Objects.requireNonNull(name);
        subscribe(new CatalogEventChange(this));
        appendChange(new CatalogCreated(name)).apply();
    }

    private Catalog(String catalogId){
        super(catalogId);
        subscribe(new CatalogEventChange(this));
    }

    public static Catalog from(String catalogId, List<DomainEvent> events){
        var catalog = new Catalog(catalogId);
        events.forEach(catalog::applyEvent);
        return catalog;
    }

    public void addMovie(String movieId, String name, String year, String duration, String description, String gender,String path){
        Objects.requireNonNull(movieId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(year);
        Objects.requireNonNull(duration);
        Objects.requireNonNull(description);
        Objects.requireNonNull(gender);
        Objects.requireNonNull(path);
        appendChange(new MovieAssigned(movieId, name, year,duration,description,gender,path)).apply();
    }



    public Map<String, Movie> movies() {
        return movies;
    }

    public String name() {
        return name;
    }
}
