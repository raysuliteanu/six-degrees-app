package org.kidoni.sixdegrees.tmdb.graph;

public record ConnectionEdge(
    String from,
    String to,
    String label         // e.g., "as Tony Stark"
) {}
