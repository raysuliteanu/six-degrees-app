package org.kidoni.sixdegrees.tmdb;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kidoni.sixdegrees.tmdb.api.model.MovieDetails200Response;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200Response;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200ResponseCastInner;
import org.kidoni.sixdegrees.tmdb.api.model.PersonDetails200Response;
import org.kidoni.sixdegrees.tmdb.api.model.SearchMovie200ResponseResultsInner;
import org.kidoni.sixdegrees.tmdb.api.model.SearchPerson200ResponseResultsInner;
import org.kidoni.sixdegrees.tmdb.model.ActedInRelationship;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.Credit;
import org.kidoni.sixdegrees.tmdb.model.Director;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.TvShow;

public class TmdbApiMapper {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Person mapToPerson(PersonDetails200Response apiPerson) {
        if (apiPerson == null) {
            return null;
        }

        String department = apiPerson.getKnownForDepartment();
        if ("Acting".equalsIgnoreCase(department)) {
            return mapToActor(apiPerson);
        } else if ("Directing".equalsIgnoreCase(department)) {
            return mapToDirector(apiPerson);
        } else {
            return mapToActor(apiPerson);
        }
    }

    private static Actor mapToActor(PersonDetails200Response apiPerson) {
        Actor actor = new Actor();
        actor.setId(apiPerson.getId());
        actor.setName(apiPerson.getName());
        actor.setBiography(apiPerson.getBiography());
        actor.setPlaceOfBirth(apiPerson.getPlaceOfBirth());
        actor.setHomepage(objectToString(apiPerson.getHomepage()));
        actor.setPopularity(convertBigDecimalToFloat(apiPerson.getPopularity()));
        actor.setGender(apiPerson.getGender());
        actor.setBirthday(parseDate(apiPerson.getBirthday()));
        actor.setDeathday(parseDate(objectToString(apiPerson.getDeathday())));
        return actor;
    }

    private static Director mapToDirector(PersonDetails200Response apiPerson) {
        Director director = new Director();
        director.setId(apiPerson.getId());
        director.setName(apiPerson.getName());
        director.setBiography(apiPerson.getBiography());
        director.setPlaceOfBirth(apiPerson.getPlaceOfBirth());
        director.setHomepage(objectToString(apiPerson.getHomepage()));
        director.setPopularity(convertBigDecimalToFloat(apiPerson.getPopularity()));
        director.setGender(apiPerson.getGender());
        director.setBirthday(parseDate(apiPerson.getBirthday()));
        director.setDeathday(parseDate(objectToString(apiPerson.getDeathday())));
        return director;
    }

    public static Person mapSearchResultToPerson(SearchPerson200ResponseResultsInner apiPerson) {
        if (apiPerson == null) {
            return null;
        }

        String department = apiPerson.getKnownForDepartment();
        if ("Acting".equalsIgnoreCase(department)) {
            return mapSearchResultToActor(apiPerson);
        } else if ("Directing".equalsIgnoreCase(department)) {
            return mapSearchResultToDirector(apiPerson);
        } else {
            return mapSearchResultToActor(apiPerson);
        }
    }

    private static Actor mapSearchResultToActor(SearchPerson200ResponseResultsInner apiPerson) {
        Actor actor = new Actor();
        actor.setId(apiPerson.getId());
        actor.setName(apiPerson.getName());
        actor.setPopularity(convertBigDecimalToFloat(apiPerson.getPopularity()));
        actor.setGender(apiPerson.getGender());
        return actor;
    }

    private static Director mapSearchResultToDirector(SearchPerson200ResponseResultsInner apiPerson) {
        Director director = new Director();
        director.setId(apiPerson.getId());
        director.setName(apiPerson.getName());
        director.setPopularity(convertBigDecimalToFloat(apiPerson.getPopularity()));
        director.setGender(apiPerson.getGender());
        return director;
    }

    public static List<Credit> mapToCreditsList(PersonCombinedCredits200Response apiCredits) {
        List<Credit> credits = new ArrayList<>();
        if (apiCredits == null) {
            return credits;
        }

        if (apiCredits.getCast() != null) {
            for (var castItem : apiCredits.getCast()) {
                Credit credit = mapCastItemToCredit(castItem);
                if (credit != null) {
                    credits.add(credit);
                }
            }
        }

        return credits;
    }

    public static List<ActedInRelationship> mapToActedInRelationships(PersonCombinedCredits200Response apiCredits) {
        List<ActedInRelationship> relationships = new ArrayList<>();
        if (apiCredits == null) {
            return relationships;
        }

        if (apiCredits.getCast() != null) {
            for (var castItem : apiCredits.getCast()) {
                Credit credit = mapCastItemToCredit(castItem);
                if (credit != null) {
                    String character = castItem.getCharacter();
                    String creditId = castItem.getCreditId();
                    ActedInRelationship relationship = new ActedInRelationship(credit, character, creditId);
                    relationships.add(relationship);
                }
            }
        }

        return relationships;
    }

    private static Credit mapCastItemToCredit(PersonCombinedCredits200ResponseCastInner castItem) {
        if (castItem == null) {
            return null;
        }

        String mediaType = castItem.getMediaType();
        if ("movie".equalsIgnoreCase(mediaType)) {
            return mapCastItemToMovie(castItem);
        } else if ("tv".equalsIgnoreCase(mediaType)) {
            return mapCastItemToTvShow(castItem);
        }
        return mapCastItemToMovie(castItem);
    }

    private static Movie mapCastItemToMovie(PersonCombinedCredits200ResponseCastInner castItem) {
        Movie movie = new Movie();
        movie.setId(castItem.getId());
        movie.setTitle(castItem.getTitle());
        movie.setOverview(castItem.getOverview());
        movie.setPosterPath(castItem.getPosterPath());
        movie.setPopularity(convertBigDecimalToFloat(castItem.getPopularity()));
        movie.setReleaseDate(parseDate(castItem.getReleaseDate()));
        return movie;
    }

    private static TvShow mapCastItemToTvShow(PersonCombinedCredits200ResponseCastInner castItem) {
        TvShow tvShow = new TvShow();
        tvShow.setId(castItem.getId());
        tvShow.setTitle(castItem.getTitle());
        tvShow.setOverview(castItem.getOverview());
        tvShow.setPosterPath(castItem.getPosterPath());
        tvShow.setPopularity(convertBigDecimalToFloat(castItem.getPopularity()));
        tvShow.setFirstAirDate(parseDate(castItem.getReleaseDate()));
        return tvShow;
    }

    public static Movie mapToMovie(MovieDetails200Response apiMovie) {
        if (apiMovie == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setId(apiMovie.getId());
        movie.setTitle(apiMovie.getTitle());
        movie.setOverview(apiMovie.getOverview());
        movie.setPosterPath(apiMovie.getPosterPath());
        movie.setPopularity(convertBigDecimalToFloat(apiMovie.getPopularity()));
        movie.setReleaseDate(parseDate(apiMovie.getReleaseDate()));
        return movie;
    }

    public static Movie mapSearchResultToMovie(SearchMovie200ResponseResultsInner apiMovie) {
        if (apiMovie == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setId(apiMovie.getId());
        movie.setTitle(apiMovie.getTitle());
        movie.setOverview(apiMovie.getOverview());
        movie.setPosterPath(apiMovie.getPosterPath());
        movie.setPopularity(convertBigDecimalToFloat(apiMovie.getPopularity()));
        movie.setReleaseDate(parseDate(apiMovie.getReleaseDate()));
        return movie;
    }

    private static Date parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    private static Float convertBigDecimalToFloat(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.floatValue();
    }

    private static String objectToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
