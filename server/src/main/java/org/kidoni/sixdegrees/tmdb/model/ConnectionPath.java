package org.kidoni.sixdegrees.tmdb.model;

import java.util.List;

public record ConnectionPath(
    List<ConnectionNode> nodes,
    List<ConnectionEdge> edges,
    Integer degree
) {}
