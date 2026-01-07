package org.kidoni.sixdegrees.tmdb;

import org.jspecify.annotations.NonNull;
import org.kidoni.sixdegrees.tmdb.api.model.MovieDetails200Response;
import org.kidoni.sixdegrees.tmdb.api.model.MovieDetails200ResponseGenresInner;
import org.kidoni.sixdegrees.tmdb.api.model.MovieDetails200ResponseProductionCompaniesInner;
import org.kidoni.sixdegrees.tmdb.api.model.MovieDetails200ResponseProductionCountriesInner;
import org.kidoni.sixdegrees.tmdb.api.model.MovieDetails200ResponseSpokenLanguagesInner;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200Response;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200ResponseCastInner;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200ResponseCrewInner;
import org.kidoni.sixdegrees.tmdb.api.model.PersonDetails200Response;
import org.kidoni.sixdegrees.tmdb.api.model.SearchMovie200Response;
import org.kidoni.sixdegrees.tmdb.api.model.SearchMovie200ResponseResultsInner;
import org.kidoni.sixdegrees.tmdb.api.model.SearchPerson200Response;
import org.kidoni.sixdegrees.tmdb.api.model.SearchPerson200ResponseResultsInner;
import org.kidoni.sixdegrees.tmdb.api.model.SearchPerson200ResponseResultsInnerKnownForInner;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * Registers OpenAPI-generated model classes for reflection in GraalVM native images.
 * This is required for Jackson to deserialize JSON responses from the TMDB API.
 */
@Configuration
@ImportRuntimeHints(TmdbNativeHints.TmdbRuntimeHints.class)
public class TmdbNativeHints {

    static class TmdbRuntimeHints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(@NonNull RuntimeHints hints, ClassLoader classLoader) {
            // Register all OpenAPI-generated model classes for full reflection access
            // This includes constructors, fields, and methods needed by Jackson

            // SearchPerson endpoint models
            registerModelClass(hints, SearchPerson200Response.class);
            registerModelClass(hints, SearchPerson200ResponseResultsInner.class);
            registerModelClass(hints, SearchPerson200ResponseResultsInnerKnownForInner.class);

            // PersonDetails endpoint models
            registerModelClass(hints, PersonDetails200Response.class);

            // PersonCombinedCredits endpoint models
            registerModelClass(hints, PersonCombinedCredits200Response.class);
            registerModelClass(hints, PersonCombinedCredits200ResponseCastInner.class);
            registerModelClass(hints, PersonCombinedCredits200ResponseCrewInner.class);

            // SearchMovie endpoint models
            registerModelClass(hints, SearchMovie200Response.class);
            registerModelClass(hints, SearchMovie200ResponseResultsInner.class);

            // MovieDetails endpoint models
            registerModelClass(hints, MovieDetails200Response.class);
            registerModelClass(hints, MovieDetails200ResponseGenresInner.class);
            registerModelClass(hints, MovieDetails200ResponseProductionCompaniesInner.class);
            registerModelClass(hints, MovieDetails200ResponseProductionCountriesInner.class);
            registerModelClass(hints, MovieDetails200ResponseSpokenLanguagesInner.class);
        }

        private void registerModelClass(RuntimeHints hints, Class<?> clazz) {
            hints.reflection().registerType(clazz,
                    MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                    MemberCategory.INVOKE_DECLARED_METHODS,
                    MemberCategory.ACCESS_DECLARED_FIELDS);
        }
    }
}
