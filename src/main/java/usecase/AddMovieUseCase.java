package usecase;

import java.util.function.Function;
import java.util.List;

import domain.catalog.Catalog;
import domain.catalog.command.AddMovieCommand;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;


@Dependent
public class AddMovieUseCase implements Function<AddMovieCommand, List<DomainEvent>> {

    private  final EventStoreRepository repository;

    public AddMovieUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AddMovieCommand command) {
        var events = repository.getEventsBy("catalog", command.getCatalogId());
        var course = Catalog.from(command.getCatalogId(),events);
        course.addMovie(command.getMovieId(),command.getName(),command.getYear(),command.getDuration(),command.getDescription(),command.getGender(),command.getPath());
        return course.getUncommittedChanges();
    }


}
