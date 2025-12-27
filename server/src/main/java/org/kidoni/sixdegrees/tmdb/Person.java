package org.kidoni.sixdegrees.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Person(
    Boolean adult,
    int gender,
    int id,
    @JsonProperty("known_for_department")
    String knownForType,
    String name,
    @JsonProperty("original_name")
    String originalName,
    float popularity,
    @JsonProperty("known_for")
    List<Credit> knownFor,
    @JsonProperty("also_known_as")
    List<String> alsoKnownAs,
    String biography,
    String birthday,
    String deathday,
    String homepage,
    @JsonProperty("imdb_id")
    String imdbId,
    @JsonProperty("place_of_birth")
    String placeOfBirth,
    @JsonProperty("profile_path")
    String profilePath
) {
}
