package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.PersonDetails200Response;
import org.kidoni.sixdegrees.tmdb.model.SearchPerson200Response;

public interface TmdbClient {
    SearchPerson200Response personSearch(String name);
    PersonDetails200Response findPersonById(Integer id);
}
