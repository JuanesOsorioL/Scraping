package infra.entrypoint;


import domain.catalog.command.AddMovieCommand;
import domain.catalog.command.CreateCatalogCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/catalog")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response executor(CreateCatalogCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addmovie")
    public Response executor(AddMovieCommand command) {
        bus.publish(command.getType(), command);//emitir comandos, los casos de uso
        return Response.ok().build();
    }
}
