package org.kidoni.sixdegrees.tmdb.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "mediaType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Movie.class, name = "movie"),
    @JsonSubTypes.Type(value = TvShow.class, name = "tv")
})
public interface Credit {
    Integer id();

    String title();

    String overview();

    String posterPath();

    Float popularity();

    List<Person> cast();

    // for movie
    Optional<Date> releaseDate();

    // for tv
    Optional<Date> firstAirDate();
}
