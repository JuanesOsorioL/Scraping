package domain.catalog.event;

import domain.generic.DomainEvent;

public class CatalogCreated extends DomainEvent {

    private final String name;

    public CatalogCreated(String name) {
        super("sofkau.catalog.CatalogCreated");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
