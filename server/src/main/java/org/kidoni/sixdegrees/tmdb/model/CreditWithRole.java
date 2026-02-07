package org.kidoni.sixdegrees.tmdb.model;

public record CreditWithRole(
    Integer id,
    String title,
    String overview,
    String releaseDate,
    String posterPath,
    String backdropPath,
    String character,
    String creditId,
    Integer order,
    String mediaType,
    Float voteAverage,
    Integer voteCount,
    Float popularity,
    String department,
    String job
) {}
