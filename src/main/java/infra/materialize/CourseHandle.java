package infra.materialize;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import domain.catalog.event.CatalogCreated;
import domain.catalog.event.MovieAssigned;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class CourseHandle {
    private final MongoClient mongoClient;

    public CourseHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofkau.catalog.CatalogCreated", blocking = true)
    void consumeProgramCreated(CatalogCreated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getName());

        mongoClient.getDatabase("queries")
                .getCollection("catalog")
                .insertOne(new Document(document));
    }



    @ConsumeEvent(value = "sofkau.catalog.MovieAssigned", blocking = true)
    void consumeLessonAdded(MovieAssigned event) {
        BasicDBObject document = new BasicDBObject();
        document.put("movie."+event.getName()+".name",event.getName());
        document.put("movie."+event.getName()+".date", Instant.now());
        document.put("movie."+event.getName()+".descripcion", event.getDescription());
        document.put("movie."+event.getName()+".duracion", event.getDuration());
        document.put("movie."+event.getName()+".genero", event.getGender());
        document.put("movie."+event.getName()+".año", event.getYear());
        document.put("movie."+event.getName()+".url", event.getPath());

        System.out.println("holaaaaaaaaaaaa " + event.getName());
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("catalog")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
       }
}