package org.kidoni.sixdegrees.tmdb;

import java.util.List;
import org.kidoni.sixdegrees.tmdb.model.Credit;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;

public interface TmdbClient {
    PersonSearchResult searchPersonByName(String name);

    Person findPersonById(Integer id);

    List<Credit> getPersonCombinedCredits(Integer id);

    MovieSearchResult searchMovieByName(String name);

    Movie findMovieById(Integer id);
}
