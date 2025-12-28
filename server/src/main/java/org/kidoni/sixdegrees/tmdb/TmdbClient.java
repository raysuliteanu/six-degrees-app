package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;

public interface TmdbClient {
    PersonSearchResult searchPersonByName(String name);

    PersonDetails findPersonById(Integer id);

    PersonCombinedCredits getPersonCombinedCredits(Integer id);

    MovieSearchResult searchMovieByName(String name);

    MovieDetails findMovieById(Integer id);
}
