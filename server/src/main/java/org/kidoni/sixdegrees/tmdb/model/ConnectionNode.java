package org.kidoni.sixdegrees.tmdb.model;

import java.util.Map;

public record ConnectionNode(
    String id,           // "actor-{id}" or "movie-{id}"
    String type,         // "actor" or "movie"
    String name,
    String imageUrl,     // profilePath or posterPath
    Map<String, Object> metadata
) {}
