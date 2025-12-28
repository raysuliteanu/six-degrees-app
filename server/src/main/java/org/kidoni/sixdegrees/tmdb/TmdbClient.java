package org.kidoni.sixdegrees.tmdb;

public interface TmdbClient {
    PersonSearchResult searchPersonByName(String name);

    PersonDetails findPersonById(Integer id);

    PersonCombinedCredits getPersonCombinedCredits(Integer id);

    MovieSearchResult searchMovieByName(String name);

    MovieDetails findMovieById(Integer id);
}
