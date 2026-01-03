package org.kidoni.sixdegrees.tmdb.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import com.fasterxml.jackson.annotation.JsonProperty;

@Node
public class TvShow implements Credit {
    @Id
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("popularity")
    private Float popularity;

    @Relationship
    @JsonProperty("cast")
    private List<Person> cast;

    @JsonProperty("first_air_date")
    private Date firstAirDate;

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String overview() {
        return overview;
    }

    @Override
    public String posterPath() {
        return posterPath;
    }

    @Override
    public Float popularity() {
        return popularity;
    }

    @Override
    public List<Person> cast() {
        return Collections.unmodifiableList(cast);
    }

    @Override
    public Optional<Date> releaseDate() {
        return Optional.empty();
    }

    @Override
    public Optional<Date> firstAirDate() {
        return Optional.of(firstAirDate);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public void setCast(List<Person> cast) {
        this.cast = cast;
    }

    public void setFirstAirDate(Date firstAirDate) {
        this.firstAirDate = firstAirDate;
    }
}
