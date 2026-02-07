package org.kidoni.sixdegrees.tmdb.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Node
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class TvShow implements Credit {
    @Id
    private Integer id;

    private String title;

    private String overview;

    private String posterPath;

    private String backdropPath;

    private Float popularity;

    private Float voteAverage;

    private Integer voteCount;

    @Relationship(type = "CAST", direction = Relationship.Direction.OUTGOING)
    private List<CastRelationship> castRelationships;

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
    public String backdropPath() {
        return backdropPath;
    }

    @Override
    public Float popularity() {
        return popularity;
    }

    @Override
    public Float voteAverage() {
        return voteAverage;
    }

    @Override
    public Integer voteCount() {
        return voteCount;
    }

    @Override
    public List<Person> cast() {
        if (castRelationships == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(
            castRelationships.stream()
                .map(CastRelationship::getActor)
                .toList()
        );
    }

    @Override
    public Optional<Date> releaseDate() {
        return Optional.empty();
    }

    @Override
    public Optional<Date> firstAirDate() {
        return Optional.ofNullable(firstAirDate);
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

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public void setCast(List<Person> cast) {
        if (cast == null) {
            this.castRelationships = null;
            return;
        }
        this.castRelationships = cast.stream()
            .filter(p -> p instanceof Actor)
            .map(p -> new CastRelationship((Actor) p, null, null))
            .toList();
    }

    public List<CastRelationship> getCastRelationships() {
        return castRelationships;
    }

    public void setCastRelationships(List<CastRelationship> castRelationships) {
        this.castRelationships = castRelationships;
    }

    public void setFirstAirDate(Date firstAirDate) {
        this.firstAirDate = firstAirDate;
    }
}
