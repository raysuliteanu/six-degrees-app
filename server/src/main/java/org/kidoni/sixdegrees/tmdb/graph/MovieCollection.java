package org.kidoni.sixdegrees.tmdb.graph;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public record MovieCollection(
    @JsonProperty("id") @Id Integer id,

    @JsonProperty("name") String name,

    @JsonProperty("poster_path") String posterPath,

    @JsonProperty("backdrop_path") String backdropPath) {
}
