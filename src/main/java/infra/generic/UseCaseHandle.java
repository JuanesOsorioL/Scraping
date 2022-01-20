package infra.generic;


import domain.generic.DomainEvent;

import domain.generic.EventStoreRepository;
import infra.message.BusService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public abstract class UseCaseHandle {

    @Inject
    private EventStoreRepository repository;

    @Inject
    private BusService busService;;

    public void process(String catalogId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("catalog", catalogId, storedEvent));

        events.forEach(busService::send);
    }
}
