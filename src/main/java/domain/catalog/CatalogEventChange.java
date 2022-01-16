package domain.catalog;

import domain.catalog.event.CatalogCreated;
import domain.catalog.event.MovieAssigned;
import domain.generic.EventChange;

import java.util.HashMap;

public class CatalogEventChange implements EventChange {
    public CatalogEventChange(Catalog catalog) {

        listener((CatalogCreated event)-> {
            catalog.name = event.getName();
            catalog.movies = new HashMap<>();
        });

        listener((MovieAssigned event)-> {
            var movie =  new Movie(event.getId(), event.getName(),event.getYear(),event.getDuration(),event.getDescription(),event.getGender());
            catalog.movies.put(event.getId(), movie);
        });

    }

}
