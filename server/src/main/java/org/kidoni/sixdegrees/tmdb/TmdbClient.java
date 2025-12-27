package org.kidoni.sixdegrees.tmdb;

public interface TmdbClient {
    PersonSearchResult findPerson(String name);

    Person findPersonById(int id);
}
