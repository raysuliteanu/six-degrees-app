package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;

public interface SixDegreesService {
    PersonSearchResult searchPerson(String name);

    PersonDetails findPerson(final int id);

    PersonCombinedCredits getPersonCredits(final int id);

    MovieSearchResult movieSearch(final String name);

    MovieDetails findMovie(final int id);
}
