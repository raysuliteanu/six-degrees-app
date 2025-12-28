package org.kidoni.sixdegrees.tmdb;

public interface TmdbClient {
    PersonSearchResult personSearch(String name);

    Person findPersonById(int id);
}
