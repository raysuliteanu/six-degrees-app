package org.kidoni.sixdegrees.tmdb.graph;

import java.util.List;

public record ConnectionPath(
    List<ConnectionNode> nodes,
    List<ConnectionEdge> edges,
    Integer degree
) {}
