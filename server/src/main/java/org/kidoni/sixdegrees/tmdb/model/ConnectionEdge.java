package org.kidoni.sixdegrees.tmdb.model;

public record ConnectionEdge(
    String from,
    String to,
    String label         // e.g., "as Tony Stark"
) {}
