package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.model.ConnectionPath;
import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;

import java.util.List;

public interface SixDegreesService {
    PersonSearchResult searchPerson(String name);

    PersonDetails findPerson(final int id);

    PersonCombinedCredits getPersonCredits(final int id);

    MovieSearchResult movieSearch(final String name);

    MovieDetails findMovie(final int id);

    /**
     * Find connection paths between two actors using Neo4j graph traversal.
     * Uses Cypher query to find shortest paths through shared movies.
     * Returns multiple paths if they exist at the same degree.
     *
     * @param actor1Id the ID of the first actor
     * @param actor2Id the ID of the second actor
     * @param maxDegrees the maximum degrees of separation to search (default 6)
     * @return list of connection paths, or empty list if no connection found
     */
    List<ConnectionPath> findConnections(Integer actor1Id, Integer actor2Id, Integer maxDegrees);
}
