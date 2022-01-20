package infra.handle;

import domain.catalog.command.CreateCatalogCommand;
import infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;
import usecase.CreateCatalogUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateCourseHandle extends UseCaseHandle {

    private final CreateCatalogUseCase useCase;

    public CreateCourseHandle(CreateCatalogUseCase useCase) {
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.catalog.create")
    void consumeBlocking(CreateCatalogCommand command) {
        var events = useCase.apply(command);
        process(command.getCatalogId(), events);
    }
}
