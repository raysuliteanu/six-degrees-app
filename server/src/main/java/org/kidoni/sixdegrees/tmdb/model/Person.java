package org.kidoni.sixdegrees.tmdb.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.neo4j.core.schema.Node;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Node
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Actor.class, name = "actor"),
    @JsonSubTypes.Type(value = Director.class, name = "director")
})
public interface Person {
    Integer id();

    String name();

    String biography();

    String placeOfBirth();

    String homepage();

    Float popularity();

    Integer gender();

    Date birthday();

    Date deathday();

    List<Credit> credits();
}
