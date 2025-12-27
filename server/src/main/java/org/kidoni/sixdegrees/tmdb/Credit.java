package org.kidoni.sixdegrees.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public record Credit(
    Boolean adult,
    @JsonProperty("backdrop_path")
    String backdropPath,
    int id,
    String title,
    @JsonProperty("original_title")
    String originalTitle,
    String overview,
    @JsonProperty("poster_path")
    String posterPath,
    @JsonProperty("media_type")
    String mediaType,
    @JsonProperty("original_language")
    String originalLanguage,
    @JsonProperty("genre_ids")
    List<Integer> genreIds,
    Double popularity,
    @JsonProperty("release_date")
    Date releaseDate,
    Boolean video,
    @JsonProperty("vote_average")
    Double voteAverage,
    @JsonProperty("vote_count")
    Double voteCount
) {}
