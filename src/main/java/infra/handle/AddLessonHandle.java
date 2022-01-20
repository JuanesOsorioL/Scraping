package infra.handle;

import domain.catalog.command.AddMovieCommand;
import infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;
import usecase.AddMovieUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddLessonHandle extends UseCaseHandle {

    private final AddMovieUseCase useCase;

    public AddLessonHandle(AddMovieUseCase useCase) {
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.catalog.addmovie")
    void consumeBlocking(AddMovieCommand command) {
        var events = useCase.apply(command);
        process(command.getCatalogId(), events);
    }


}
