package usecase;

import domain.catalog.Catalog;
import domain.catalog.command.CreateCatalogCommand;
import domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;


@Dependent
public class CreateCatalogUseCase implements Function<CreateCatalogCommand, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(CreateCatalogCommand command) {
        var course = new Catalog(command.getCatalogId(),command.getName());
        return course.getUncommittedChanges();
    }
}
