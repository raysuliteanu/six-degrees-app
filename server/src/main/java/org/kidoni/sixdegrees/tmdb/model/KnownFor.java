package org.kidoni.sixdegrees.tmdb.model;

import java.util.List;

public record KnownFor(
    Integer id,
    String title,
    String overview,
    String releaseDate,
    String posterPath,
    String backdropPath,
    String mediaType,
    List<Integer> genreIds,
    Float popularity,
    Float voteAverage,
    Integer voteCount
) {}
