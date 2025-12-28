package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@JsonPropertyOrder({
    MovieDetails.JSON_PROPERTY_ADULT,
    MovieDetails.JSON_PROPERTY_BACKDROP_PATH,
    MovieDetails.JSON_PROPERTY_BELONGS_TO_COLLECTION,
    MovieDetails.JSON_PROPERTY_BUDGET,
    MovieDetails.JSON_PROPERTY_GENRES,
    MovieDetails.JSON_PROPERTY_HOMEPAGE,
    MovieDetails.JSON_PROPERTY_ID,
    MovieDetails.JSON_PROPERTY_IMDB_ID,
    MovieDetails.JSON_PROPERTY_ORIGINAL_LANGUAGE,
    MovieDetails.JSON_PROPERTY_ORIGINAL_TITLE,
    MovieDetails.JSON_PROPERTY_OVERVIEW,
    MovieDetails.JSON_PROPERTY_POPULARITY,
    MovieDetails.JSON_PROPERTY_POSTER_PATH,
    MovieDetails.JSON_PROPERTY_PRODUCTION_COMPANIES,
    MovieDetails.JSON_PROPERTY_PRODUCTION_COUNTRIES,
    MovieDetails.JSON_PROPERTY_RELEASE_DATE,
    MovieDetails.JSON_PROPERTY_REVENUE,
    MovieDetails.JSON_PROPERTY_RUNTIME,
    MovieDetails.JSON_PROPERTY_SPOKEN_LANGUAGES,
    MovieDetails.JSON_PROPERTY_STATUS,
    MovieDetails.JSON_PROPERTY_TAGLINE,
    MovieDetails.JSON_PROPERTY_TITLE,
    MovieDetails.JSON_PROPERTY_VIDEO,
    MovieDetails.JSON_PROPERTY_VOTE_AVERAGE,
    MovieDetails.JSON_PROPERTY_VOTE_COUNT
})
@Node
public class MovieDetails {
    public static final String JSON_PROPERTY_ADULT = "adult";
    @jakarta.annotation.Nullable
    private Boolean adult = true;

    public static final String JSON_PROPERTY_BACKDROP_PATH = "backdrop_path";
    @jakarta.annotation.Nullable
    private String backdropPath;

    public static final String JSON_PROPERTY_BELONGS_TO_COLLECTION = "belongs_to_collection";
    @jakarta.annotation.Nullable
    private Object belongsToCollection = null;

    public static final String JSON_PROPERTY_BUDGET = "budget";
    @jakarta.annotation.Nullable
    private Integer budget = 0;

    public static final String JSON_PROPERTY_GENRES = "genres";
    @jakarta.annotation.Nullable
    private List<MovieDetailsGenres> genres = new ArrayList<>();

    public static final String JSON_PROPERTY_HOMEPAGE = "homepage";
    @jakarta.annotation.Nullable
    private String homepage;

    public static final String JSON_PROPERTY_ID = "id";
    @Id
    private Integer id = 0;

    public static final String JSON_PROPERTY_IMDB_ID = "imdb_id";
    @jakarta.annotation.Nullable
    private String imdbId;

    public static final String JSON_PROPERTY_ORIGINAL_LANGUAGE = "original_language";
    @jakarta.annotation.Nullable
    private String originalLanguage;

    public static final String JSON_PROPERTY_ORIGINAL_TITLE = "original_title";
    @jakarta.annotation.Nullable
    private String originalTitle;

    public static final String JSON_PROPERTY_OVERVIEW = "overview";
    @jakarta.annotation.Nullable
    private String overview;

    public static final String JSON_PROPERTY_POPULARITY = "popularity";
    @jakarta.annotation.Nullable
    private BigDecimal popularity = new BigDecimal("0");

    public static final String JSON_PROPERTY_POSTER_PATH = "poster_path";
    @jakarta.annotation.Nullable
    private String posterPath;

    public static final String JSON_PROPERTY_PRODUCTION_COMPANIES = "production_companies";
    @jakarta.annotation.Nullable
    private List<MovieDetailsProductionCompanies> productionCompanies = new ArrayList<>();

    public static final String JSON_PROPERTY_PRODUCTION_COUNTRIES = "production_countries";
    @jakarta.annotation.Nullable
    private List<MovieDetailsProductionCountries> productionCountries = new ArrayList<>();

    public static final String JSON_PROPERTY_RELEASE_DATE = "release_date";
    @jakarta.annotation.Nullable
    private String releaseDate;

    public static final String JSON_PROPERTY_REVENUE = "revenue";
    @jakarta.annotation.Nullable
    private Integer revenue = 0;

    public static final String JSON_PROPERTY_RUNTIME = "runtime";
    @jakarta.annotation.Nullable
    private Integer runtime = 0;

    public static final String JSON_PROPERTY_SPOKEN_LANGUAGES = "spoken_languages";
    @jakarta.annotation.Nullable
    private List<MovieDetailsSpokenLanguages> spokenLanguages = new ArrayList<>();

    public static final String JSON_PROPERTY_STATUS = "status";
    @jakarta.annotation.Nullable
    private String status;

    public static final String JSON_PROPERTY_TAGLINE = "tagline";
    @jakarta.annotation.Nullable
    private String tagline;

    public static final String JSON_PROPERTY_TITLE = "title";
    @jakarta.annotation.Nullable
    private String title;

    public static final String JSON_PROPERTY_VIDEO = "video";
    @jakarta.annotation.Nullable
    private Boolean video = true;

    public static final String JSON_PROPERTY_VOTE_AVERAGE = "vote_average";
    @jakarta.annotation.Nullable
    private BigDecimal voteAverage = new BigDecimal("0");

    public static final String JSON_PROPERTY_VOTE_COUNT = "vote_count";
    @jakarta.annotation.Nullable
    private Integer voteCount = 0;

    public MovieDetails() {
    }

    public MovieDetails adult(@jakarta.annotation.Nullable Boolean adult) {
        this.adult = adult;
        return this;
    }

    /**
     * Get adult
     *
     * @return adult
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ADULT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getAdult() {
        return adult;
    }


    @JsonProperty(JSON_PROPERTY_ADULT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setAdult(@jakarta.annotation.Nullable Boolean adult) {
        this.adult = adult;
    }


    public MovieDetails backdropPath(@jakarta.annotation.Nullable String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    /**
     * Get backdropPath
     *
     * @return backdropPath
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_BACKDROP_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getBackdropPath() {
        return backdropPath;
    }


    @JsonProperty(JSON_PROPERTY_BACKDROP_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBackdropPath(@jakarta.annotation.Nullable String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public MovieDetails belongsToCollection(@jakarta.annotation.Nullable Object belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
        return this;
    }

    /**
     * Get belongsToCollection
     *
     * @return belongsToCollection
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_BELONGS_TO_COLLECTION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Object getBelongsToCollection() {
        return belongsToCollection;
    }


    @JsonProperty(JSON_PROPERTY_BELONGS_TO_COLLECTION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBelongsToCollection(@jakarta.annotation.Nullable Object belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }


    public MovieDetails budget(@jakarta.annotation.Nullable Integer budget) {
        this.budget = budget;
        return this;
    }

    /**
     * Get budget
     *
     * @return budget
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_BUDGET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getBudget() {
        return budget;
    }


    @JsonProperty(JSON_PROPERTY_BUDGET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBudget(@jakarta.annotation.Nullable Integer budget) {
        this.budget = budget;
    }


    public MovieDetails genres(@jakarta.annotation.Nullable List<MovieDetailsGenres> genres) {
        this.genres = genres;
        return this;
    }

    public MovieDetails addGenresItem(MovieDetailsGenres genresItem) {
        if (this.genres == null) {
            this.genres = new ArrayList<>();
        }
        this.genres.add(genresItem);
        return this;
    }

    /**
     * Get genres
     *
     * @return genres
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_GENRES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<MovieDetailsGenres> getGenres() {
        return genres;
    }


    @JsonProperty(JSON_PROPERTY_GENRES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setGenres(@jakarta.annotation.Nullable List<MovieDetailsGenres> genres) {
        this.genres = genres;
    }


    public MovieDetails homepage(@jakarta.annotation.Nullable String homepage) {
        this.homepage = homepage;
        return this;
    }

    /**
     * Get homepage
     *
     * @return homepage
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_HOMEPAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getHomepage() {
        return homepage;
    }


    @JsonProperty(JSON_PROPERTY_HOMEPAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setHomepage(@jakarta.annotation.Nullable String homepage) {
        this.homepage = homepage;
    }


    public MovieDetails id(@jakarta.annotation.Nullable Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getId() {
        return id;
    }


    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setId(@jakarta.annotation.Nullable Integer id) {
        this.id = id;
    }


    public MovieDetails imdbId(@jakarta.annotation.Nullable String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    /**
     * Get imdbId
     *
     * @return imdbId
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_IMDB_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getImdbId() {
        return imdbId;
    }


    @JsonProperty(JSON_PROPERTY_IMDB_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setImdbId(@jakarta.annotation.Nullable String imdbId) {
        this.imdbId = imdbId;
    }


    public MovieDetails originalLanguage(@jakarta.annotation.Nullable String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    /**
     * Get originalLanguage
     *
     * @return originalLanguage
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ORIGINAL_LANGUAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getOriginalLanguage() {
        return originalLanguage;
    }


    @JsonProperty(JSON_PROPERTY_ORIGINAL_LANGUAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOriginalLanguage(@jakarta.annotation.Nullable String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }


    public MovieDetails originalTitle(@jakarta.annotation.Nullable String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    /**
     * Get originalTitle
     *
     * @return originalTitle
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ORIGINAL_TITLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getOriginalTitle() {
        return originalTitle;
    }


    @JsonProperty(JSON_PROPERTY_ORIGINAL_TITLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOriginalTitle(@jakarta.annotation.Nullable String originalTitle) {
        this.originalTitle = originalTitle;
    }


    public MovieDetails overview(@jakarta.annotation.Nullable String overview) {
        this.overview = overview;
        return this;
    }

    /**
     * Get overview
     *
     * @return overview
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_OVERVIEW)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getOverview() {
        return overview;
    }


    @JsonProperty(JSON_PROPERTY_OVERVIEW)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOverview(@jakarta.annotation.Nullable String overview) {
        this.overview = overview;
    }


    public MovieDetails popularity(@jakarta.annotation.Nullable BigDecimal popularity) {
        this.popularity = popularity;
        return this;
    }

    /**
     * Get popularity
     *
     * @return popularity
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_POPULARITY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public BigDecimal getPopularity() {
        return popularity;
    }


    @JsonProperty(JSON_PROPERTY_POPULARITY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPopularity(@jakarta.annotation.Nullable BigDecimal popularity) {
        this.popularity = popularity;
    }


    public MovieDetails posterPath(@jakarta.annotation.Nullable String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    /**
     * Get posterPath
     *
     * @return posterPath
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_POSTER_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getPosterPath() {
        return posterPath;
    }


    @JsonProperty(JSON_PROPERTY_POSTER_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPosterPath(@jakarta.annotation.Nullable String posterPath) {
        this.posterPath = posterPath;
    }


    public MovieDetails productionCompanies(@jakarta.annotation.Nullable List<MovieDetailsProductionCompanies> productionCompanies) {
        this.productionCompanies = productionCompanies;
        return this;
    }

    public MovieDetails addProductionCompaniesItem(MovieDetailsProductionCompanies productionCompaniesItem) {
        if (this.productionCompanies == null) {
            this.productionCompanies = new ArrayList<>();
        }
        this.productionCompanies.add(productionCompaniesItem);
        return this;
    }

    /**
     * Get productionCompanies
     *
     * @return productionCompanies
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_PRODUCTION_COMPANIES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<MovieDetailsProductionCompanies> getProductionCompanies() {
        return productionCompanies;
    }


    @JsonProperty(JSON_PROPERTY_PRODUCTION_COMPANIES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setProductionCompanies(@jakarta.annotation.Nullable List<MovieDetailsProductionCompanies> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }


    public MovieDetails productionCountries(@jakarta.annotation.Nullable List<MovieDetailsProductionCountries> productionCountries) {
        this.productionCountries = productionCountries;
        return this;
    }

    public MovieDetails addProductionCountriesItem(MovieDetailsProductionCountries productionCountriesItem) {
        if (this.productionCountries == null) {
            this.productionCountries = new ArrayList<>();
        }
        this.productionCountries.add(productionCountriesItem);
        return this;
    }

    /**
     * Get productionCountries
     *
     * @return productionCountries
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_PRODUCTION_COUNTRIES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<MovieDetailsProductionCountries> getProductionCountries() {
        return productionCountries;
    }


    @JsonProperty(JSON_PROPERTY_PRODUCTION_COUNTRIES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setProductionCountries(@jakarta.annotation.Nullable List<MovieDetailsProductionCountries> productionCountries) {
        this.productionCountries = productionCountries;
    }


    public MovieDetails releaseDate(@jakarta.annotation.Nullable String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    /**
     * Get releaseDate
     *
     * @return releaseDate
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_RELEASE_DATE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getReleaseDate() {
        return releaseDate;
    }


    @JsonProperty(JSON_PROPERTY_RELEASE_DATE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setReleaseDate(@jakarta.annotation.Nullable String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public MovieDetails revenue(@jakarta.annotation.Nullable Integer revenue) {
        this.revenue = revenue;
        return this;
    }

    /**
     * Get revenue
     *
     * @return revenue
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_REVENUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getRevenue() {
        return revenue;
    }


    @JsonProperty(JSON_PROPERTY_REVENUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRevenue(@jakarta.annotation.Nullable Integer revenue) {
        this.revenue = revenue;
    }


    public MovieDetails runtime(@jakarta.annotation.Nullable Integer runtime) {
        this.runtime = runtime;
        return this;
    }

    /**
     * Get runtime
     *
     * @return runtime
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_RUNTIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getRuntime() {
        return runtime;
    }


    @JsonProperty(JSON_PROPERTY_RUNTIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRuntime(@jakarta.annotation.Nullable Integer runtime) {
        this.runtime = runtime;
    }


    public MovieDetails spokenLanguages(@jakarta.annotation.Nullable List<MovieDetailsSpokenLanguages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
        return this;
    }

    public MovieDetails addSpokenLanguagesItem(MovieDetailsSpokenLanguages spokenLanguagesItem) {
        if (this.spokenLanguages == null) {
            this.spokenLanguages = new ArrayList<>();
        }
        this.spokenLanguages.add(spokenLanguagesItem);
        return this;
    }

    /**
     * Get spokenLanguages
     *
     * @return spokenLanguages
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_SPOKEN_LANGUAGES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<MovieDetailsSpokenLanguages> getSpokenLanguages() {
        return spokenLanguages;
    }


    @JsonProperty(JSON_PROPERTY_SPOKEN_LANGUAGES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSpokenLanguages(@jakarta.annotation.Nullable List<MovieDetailsSpokenLanguages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }


    public MovieDetails status(@jakarta.annotation.Nullable String status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_STATUS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getStatus() {
        return status;
    }


    @JsonProperty(JSON_PROPERTY_STATUS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setStatus(@jakarta.annotation.Nullable String status) {
        this.status = status;
    }


    public MovieDetails tagline(@jakarta.annotation.Nullable String tagline) {
        this.tagline = tagline;
        return this;
    }

    /**
     * Get tagline
     *
     * @return tagline
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TAGLINE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getTagline() {
        return tagline;
    }


    @JsonProperty(JSON_PROPERTY_TAGLINE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTagline(@jakarta.annotation.Nullable String tagline) {
        this.tagline = tagline;
    }


    public MovieDetails title(@jakarta.annotation.Nullable String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     *
     * @return title
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TITLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getTitle() {
        return title;
    }


    @JsonProperty(JSON_PROPERTY_TITLE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTitle(@jakarta.annotation.Nullable String title) {
        this.title = title;
    }


    public MovieDetails video(@jakarta.annotation.Nullable Boolean video) {
        this.video = video;
        return this;
    }

    /**
     * Get video
     *
     * @return video
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_VIDEO)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getVideo() {
        return video;
    }


    @JsonProperty(JSON_PROPERTY_VIDEO)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setVideo(@jakarta.annotation.Nullable Boolean video) {
        this.video = video;
    }


    public MovieDetails voteAverage(@jakarta.annotation.Nullable BigDecimal voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    /**
     * Get voteAverage
     *
     * @return voteAverage
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_VOTE_AVERAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public BigDecimal getVoteAverage() {
        return voteAverage;
    }


    @JsonProperty(JSON_PROPERTY_VOTE_AVERAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setVoteAverage(@jakarta.annotation.Nullable BigDecimal voteAverage) {
        this.voteAverage = voteAverage;
    }


    public MovieDetails voteCount(@jakarta.annotation.Nullable Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    /**
     * Get voteCount
     *
     * @return voteCount
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_VOTE_COUNT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getVoteCount() {
        return voteCount;
    }


    @JsonProperty(JSON_PROPERTY_VOTE_COUNT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setVoteCount(@jakarta.annotation.Nullable Integer voteCount) {
        this.voteCount = voteCount;
    }


    /**
     * Return true if this movie_details_200_response object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDetails movieDetails200Response = (MovieDetails) o;
        return Objects.equals(this.adult, movieDetails200Response.adult) &&
            Objects.equals(this.backdropPath, movieDetails200Response.backdropPath) &&
            Objects.equals(this.belongsToCollection, movieDetails200Response.belongsToCollection) &&
            Objects.equals(this.budget, movieDetails200Response.budget) &&
            Objects.equals(this.genres, movieDetails200Response.genres) &&
            Objects.equals(this.homepage, movieDetails200Response.homepage) &&
            Objects.equals(this.id, movieDetails200Response.id) &&
            Objects.equals(this.imdbId, movieDetails200Response.imdbId) &&
            Objects.equals(this.originalLanguage, movieDetails200Response.originalLanguage) &&
            Objects.equals(this.originalTitle, movieDetails200Response.originalTitle) &&
            Objects.equals(this.overview, movieDetails200Response.overview) &&
            Objects.equals(this.popularity, movieDetails200Response.popularity) &&
            Objects.equals(this.posterPath, movieDetails200Response.posterPath) &&
            Objects.equals(this.productionCompanies, movieDetails200Response.productionCompanies) &&
            Objects.equals(this.productionCountries, movieDetails200Response.productionCountries) &&
            Objects.equals(this.releaseDate, movieDetails200Response.releaseDate) &&
            Objects.equals(this.revenue, movieDetails200Response.revenue) &&
            Objects.equals(this.runtime, movieDetails200Response.runtime) &&
            Objects.equals(this.spokenLanguages, movieDetails200Response.spokenLanguages) &&
            Objects.equals(this.status, movieDetails200Response.status) &&
            Objects.equals(this.tagline, movieDetails200Response.tagline) &&
            Objects.equals(this.title, movieDetails200Response.title) &&
            Objects.equals(this.video, movieDetails200Response.video) &&
            Objects.equals(this.voteAverage, movieDetails200Response.voteAverage) &&
            Objects.equals(this.voteCount, movieDetails200Response.voteCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adult, backdropPath, belongsToCollection, budget, genres, homepage, id, imdbId, originalLanguage, originalTitle, overview, popularity, posterPath, productionCompanies, productionCountries, releaseDate, revenue, runtime, spokenLanguages, status, tagline, title, video, voteAverage, voteCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MovieDetails200Response {\n");
        sb.append("    adult: ").append(toIndentedString(adult)).append("\n");
        sb.append("    backdropPath: ").append(toIndentedString(backdropPath)).append("\n");
        sb.append("    belongsToCollection: ").append(toIndentedString(belongsToCollection)).append("\n");
        sb.append("    budget: ").append(toIndentedString(budget)).append("\n");
        sb.append("    genres: ").append(toIndentedString(genres)).append("\n");
        sb.append("    homepage: ").append(toIndentedString(homepage)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    imdbId: ").append(toIndentedString(imdbId)).append("\n");
        sb.append("    originalLanguage: ").append(toIndentedString(originalLanguage)).append("\n");
        sb.append("    originalTitle: ").append(toIndentedString(originalTitle)).append("\n");
        sb.append("    overview: ").append(toIndentedString(overview)).append("\n");
        sb.append("    popularity: ").append(toIndentedString(popularity)).append("\n");
        sb.append("    posterPath: ").append(toIndentedString(posterPath)).append("\n");
        sb.append("    productionCompanies: ").append(toIndentedString(productionCompanies)).append("\n");
        sb.append("    productionCountries: ").append(toIndentedString(productionCountries)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
        sb.append("    revenue: ").append(toIndentedString(revenue)).append("\n");
        sb.append("    runtime: ").append(toIndentedString(runtime)).append("\n");
        sb.append("    spokenLanguages: ").append(toIndentedString(spokenLanguages)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    tagline: ").append(toIndentedString(tagline)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    video: ").append(toIndentedString(video)).append("\n");
        sb.append("    voteAverage: ").append(toIndentedString(voteAverage)).append("\n");
        sb.append("    voteCount: ").append(toIndentedString(voteCount)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * Convert the instance into URL query string.
     *
     * @return URL query string
     */
    public String toUrlQueryString() {
        return toUrlQueryString(null);
    }

    /**
     * Convert the instance into URL query string.
     *
     * @param prefix prefix of the query string
     * @return URL query string
     */
    public String toUrlQueryString(String prefix) {
        String suffix = "";
        String containerSuffix = "";
        String containerPrefix = "";
        if (prefix == null) {
            // style=form, explode=true, e.g. /pet?name=cat&type=manx
            prefix = "";
        }
        else {
            // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
            prefix = prefix + "[";
            suffix = "]";
            containerSuffix = "]";
            containerPrefix = "[";
        }

        StringJoiner joiner = new StringJoiner("&");

        // add `adult` to the URL query string
        if (getAdult() != null) {
            joiner.add(String.format("%sadult%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getAdult()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `backdrop_path` to the URL query string
        if (getBackdropPath() != null) {
            joiner.add(String.format("%sbackdrop_path%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getBackdropPath()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `belongs_to_collection` to the URL query string
        if (getBelongsToCollection() != null) {
            joiner.add(String.format("%sbelongs_to_collection%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getBelongsToCollection()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `budget` to the URL query string
        if (getBudget() != null) {
            joiner.add(String.format("%sbudget%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getBudget()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `genres` to the URL query string
        if (getGenres() != null) {
            for (int i = 0; i < getGenres().size(); i++) {
                if (getGenres().get(i) != null) {
                    joiner.add(getGenres().get(i).toUrlQueryString(String.format("%sgenres%s%s", prefix, suffix,
                        "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        // add `homepage` to the URL query string
        if (getHomepage() != null) {
            joiner.add(String.format("%shomepage%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getHomepage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `id` to the URL query string
        if (getId() != null) {
            joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `imdb_id` to the URL query string
        if (getImdbId() != null) {
            joiner.add(String.format("%simdb_id%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getImdbId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `original_language` to the URL query string
        if (getOriginalLanguage() != null) {
            joiner.add(String.format("%soriginal_language%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getOriginalLanguage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `original_title` to the URL query string
        if (getOriginalTitle() != null) {
            joiner.add(String.format("%soriginal_title%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getOriginalTitle()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `overview` to the URL query string
        if (getOverview() != null) {
            joiner.add(String.format("%soverview%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getOverview()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `popularity` to the URL query string
        if (getPopularity() != null) {
            joiner.add(String.format("%spopularity%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getPopularity()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `poster_path` to the URL query string
        if (getPosterPath() != null) {
            joiner.add(String.format("%sposter_path%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getPosterPath()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `production_companies` to the URL query string
        if (getProductionCompanies() != null) {
            for (int i = 0; i < getProductionCompanies().size(); i++) {
                if (getProductionCompanies().get(i) != null) {
                    joiner.add(getProductionCompanies().get(i).toUrlQueryString(String.format("%sproduction_companies%s%s", prefix, suffix,
                        "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        // add `production_countries` to the URL query string
        if (getProductionCountries() != null) {
            for (int i = 0; i < getProductionCountries().size(); i++) {
                if (getProductionCountries().get(i) != null) {
                    joiner.add(getProductionCountries().get(i).toUrlQueryString(String.format("%sproduction_countries%s%s", prefix, suffix,
                        "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        // add `release_date` to the URL query string
        if (getReleaseDate() != null) {
            joiner.add(String.format("%srelease_date%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getReleaseDate()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `revenue` to the URL query string
        if (getRevenue() != null) {
            joiner.add(String.format("%srevenue%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getRevenue()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `runtime` to the URL query string
        if (getRuntime() != null) {
            joiner.add(String.format("%sruntime%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getRuntime()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `spoken_languages` to the URL query string
        if (getSpokenLanguages() != null) {
            for (int i = 0; i < getSpokenLanguages().size(); i++) {
                if (getSpokenLanguages().get(i) != null) {
                    joiner.add(getSpokenLanguages().get(i).toUrlQueryString(String.format("%sspoken_languages%s%s", prefix, suffix,
                        "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        // add `status` to the URL query string
        if (getStatus() != null) {
            joiner.add(String.format("%sstatus%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getStatus()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `tagline` to the URL query string
        if (getTagline() != null) {
            joiner.add(String.format("%stagline%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getTagline()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `title` to the URL query string
        if (getTitle() != null) {
            joiner.add(String.format("%stitle%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getTitle()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `video` to the URL query string
        if (getVideo() != null) {
            joiner.add(String.format("%svideo%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getVideo()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `vote_average` to the URL query string
        if (getVoteAverage() != null) {
            joiner.add(String.format("%svote_average%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getVoteAverage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `vote_count` to the URL query string
        if (getVoteCount() != null) {
            joiner.add(String.format("%svote_count%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getVoteCount()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}
