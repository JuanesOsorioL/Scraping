package usecase;

import domain.catalog.Catalog;
import domain.catalog.command.AddMovieCommand;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class ExtraerCatalogUseCase implements Function<AddMovieCommand, List<DomainEvent>> {

        private final EventStoreRepository repository;

        final String baseURL = "https://pelisplus.so/estrenos";

    public ExtraerCatalogUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<DomainEvent> apply(AddMovieCommand command) {

        var events = repository.getEventsBy("catalog", command.getCatalogId());
        var catalogo = Catalog.from(command.getCatalogId(),events);

        var document = urlBase();
        Elements entradas = document.select(".items-peliculas .item-pelicula a");

        for (Element elem : entradas) {
            final String urlPelicula = elem.attr("href");
            try {
                final Document movie = Jsoup.connect("https://pelisplus.so" + urlPelicula).get();
                String nombrePelicula = movie.select(".info-content h1").text();
                String genero = movie.select(".info-content p:nth-of-type(4) span:nth-of-type(2)").text();
                String descripcion = movie.select(".sinopsis").text();
                String year = movie.select(".info-content p:nth-of-type(2) span:nth-of-type(2)").text();
                String path = movie.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)").attr("data-video");
                String duracion = movie.select(".info-content p:nth-of-type(3) span:nth-of-type(2)").text();

                catalogo.addMovie(
                        command.getMovieId(),
                        nombrePelicula,
                        year,
                        duracion,
                        descripcion,
                        genero,
                        path
                );

            }catch (Exception e){
                throw new RuntimeException("No se pudo obtener la información de la pelicula");
            }

        }
        return catalogo.getUncommittedChanges();
    }


    public Document urlBase() {
        try {
            return Jsoup.connect(baseURL).get();
        }catch (IOException e){
            throw new RuntimeException("Error con es status de la página");
        }
    }

}
