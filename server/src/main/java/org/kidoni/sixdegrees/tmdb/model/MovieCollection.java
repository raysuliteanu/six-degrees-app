package org.kidoni.sixdegrees.tmdb.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import com.fasterxml.jackson.annotation.JsonProperty;

@Node
public record MovieCollection(
        @JsonProperty("id") @Id Integer id,

        @JsonProperty("name") String name,

        @JsonProperty("poster_path") String posterPath,

        @JsonProperty("backdrop_path") String backdropPath) {
}
