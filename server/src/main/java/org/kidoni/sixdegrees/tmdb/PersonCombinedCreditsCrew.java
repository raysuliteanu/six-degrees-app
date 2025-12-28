package org.kidoni.sixdegrees.tmdb;

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
import org.kidoni.sixdegrees.tmdb.client.ApiClientUtil;

@JsonPropertyOrder({
    PersonCombinedCreditsCrew.JSON_PROPERTY_ADULT,
    PersonCombinedCreditsCrew.JSON_PROPERTY_BACKDROP_PATH,
    PersonCombinedCreditsCrew.JSON_PROPERTY_GENRE_IDS,
    PersonCombinedCreditsCrew.JSON_PROPERTY_ID,
    PersonCombinedCreditsCrew.JSON_PROPERTY_ORIGINAL_LANGUAGE,
    PersonCombinedCreditsCrew.JSON_PROPERTY_ORIGINAL_TITLE,
    PersonCombinedCreditsCrew.JSON_PROPERTY_OVERVIEW,
    PersonCombinedCreditsCrew.JSON_PROPERTY_POPULARITY,
    PersonCombinedCreditsCrew.JSON_PROPERTY_POSTER_PATH,
    PersonCombinedCreditsCrew.JSON_PROPERTY_RELEASE_DATE,
    PersonCombinedCreditsCrew.JSON_PROPERTY_TITLE,
    PersonCombinedCreditsCrew.JSON_PROPERTY_VIDEO,
    PersonCombinedCreditsCrew.JSON_PROPERTY_VOTE_AVERAGE,
    PersonCombinedCreditsCrew.JSON_PROPERTY_VOTE_COUNT,
    PersonCombinedCreditsCrew.JSON_PROPERTY_CREDIT_ID,
    PersonCombinedCreditsCrew.JSON_PROPERTY_DEPARTMENT,
    PersonCombinedCreditsCrew.JSON_PROPERTY_JOB,
    PersonCombinedCreditsCrew.JSON_PROPERTY_MEDIA_TYPE
})
public class PersonCombinedCreditsCrew {
    public static final String JSON_PROPERTY_ADULT = "adult";
    @jakarta.annotation.Nullable
    private Boolean adult = true;

    public static final String JSON_PROPERTY_BACKDROP_PATH = "backdrop_path";
    @jakarta.annotation.Nullable
    private String backdropPath;

    public static final String JSON_PROPERTY_GENRE_IDS = "genre_ids";
    @jakarta.annotation.Nullable
    private List<Integer> genreIds = new ArrayList<>();

    public static final String JSON_PROPERTY_ID = "id";
    @jakarta.annotation.Nullable
    private Integer id = 0;

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

    public static final String JSON_PROPERTY_RELEASE_DATE = "release_date";
    @jakarta.annotation.Nullable
    private String releaseDate;

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

    public static final String JSON_PROPERTY_CREDIT_ID = "credit_id";
    @jakarta.annotation.Nullable
    private String creditId;

    public static final String JSON_PROPERTY_DEPARTMENT = "department";
    @jakarta.annotation.Nullable
    private String department;

    public static final String JSON_PROPERTY_JOB = "job";
    @jakarta.annotation.Nullable
    private String job;

    public static final String JSON_PROPERTY_MEDIA_TYPE = "media_type";
    @jakarta.annotation.Nullable
    private String mediaType;

    public PersonCombinedCreditsCrew() {
    }

    public PersonCombinedCreditsCrew adult(@jakarta.annotation.Nullable Boolean adult) {
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


    public PersonCombinedCreditsCrew backdropPath(@jakarta.annotation.Nullable String backdropPath) {
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


    public PersonCombinedCreditsCrew genreIds(@jakarta.annotation.Nullable List<Integer> genreIds) {
        this.genreIds = genreIds;
        return this;
    }

    public PersonCombinedCreditsCrew addGenreIdsItem(Integer genreIdsItem) {
        if (this.genreIds == null) {
            this.genreIds = new ArrayList<>();
        }
        this.genreIds.add(genreIdsItem);
        return this;
    }

    /**
     * Get genreIds
     *
     * @return genreIds
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_GENRE_IDS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Integer> getGenreIds() {
        return genreIds;
    }


    @JsonProperty(JSON_PROPERTY_GENRE_IDS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setGenreIds(@jakarta.annotation.Nullable List<Integer> genreIds) {
        this.genreIds = genreIds;
    }


    public PersonCombinedCreditsCrew id(@jakarta.annotation.Nullable Integer id) {
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


    public PersonCombinedCreditsCrew originalLanguage(@jakarta.annotation.Nullable String originalLanguage) {
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


    public PersonCombinedCreditsCrew originalTitle(@jakarta.annotation.Nullable String originalTitle) {
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


    public PersonCombinedCreditsCrew overview(@jakarta.annotation.Nullable String overview) {
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


    public PersonCombinedCreditsCrew popularity(@jakarta.annotation.Nullable BigDecimal popularity) {
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


    public PersonCombinedCreditsCrew posterPath(@jakarta.annotation.Nullable String posterPath) {
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


    public PersonCombinedCreditsCrew releaseDate(@jakarta.annotation.Nullable String releaseDate) {
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


    public PersonCombinedCreditsCrew title(@jakarta.annotation.Nullable String title) {
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


    public PersonCombinedCreditsCrew video(@jakarta.annotation.Nullable Boolean video) {
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


    public PersonCombinedCreditsCrew voteAverage(@jakarta.annotation.Nullable BigDecimal voteAverage) {
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


    public PersonCombinedCreditsCrew voteCount(@jakarta.annotation.Nullable Integer voteCount) {
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


    public PersonCombinedCreditsCrew creditId(@jakarta.annotation.Nullable String creditId) {
        this.creditId = creditId;
        return this;
    }

    /**
     * Get creditId
     *
     * @return creditId
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_CREDIT_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getCreditId() {
        return creditId;
    }


    @JsonProperty(JSON_PROPERTY_CREDIT_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCreditId(@jakarta.annotation.Nullable String creditId) {
        this.creditId = creditId;
    }


    public PersonCombinedCreditsCrew department(@jakarta.annotation.Nullable String department) {
        this.department = department;
        return this;
    }

    /**
     * Get department
     *
     * @return department
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DEPARTMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getDepartment() {
        return department;
    }


    @JsonProperty(JSON_PROPERTY_DEPARTMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDepartment(@jakarta.annotation.Nullable String department) {
        this.department = department;
    }


    public PersonCombinedCreditsCrew job(@jakarta.annotation.Nullable String job) {
        this.job = job;
        return this;
    }

    /**
     * Get job
     *
     * @return job
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_JOB)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getJob() {
        return job;
    }


    @JsonProperty(JSON_PROPERTY_JOB)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setJob(@jakarta.annotation.Nullable String job) {
        this.job = job;
    }


    public PersonCombinedCreditsCrew mediaType(@jakarta.annotation.Nullable String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Get mediaType
     *
     * @return mediaType
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_MEDIA_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getMediaType() {
        return mediaType;
    }


    @JsonProperty(JSON_PROPERTY_MEDIA_TYPE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMediaType(@jakarta.annotation.Nullable String mediaType) {
        this.mediaType = mediaType;
    }


    /**
     * Return true if this person_combined_credits_200_response_crew_inner object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonCombinedCreditsCrew personCombinedCredits200ResponseCrewInner = (PersonCombinedCreditsCrew) o;
        return Objects.equals(this.adult, personCombinedCredits200ResponseCrewInner.adult) &&
            Objects.equals(this.backdropPath, personCombinedCredits200ResponseCrewInner.backdropPath) &&
            Objects.equals(this.genreIds, personCombinedCredits200ResponseCrewInner.genreIds) &&
            Objects.equals(this.id, personCombinedCredits200ResponseCrewInner.id) &&
            Objects.equals(this.originalLanguage, personCombinedCredits200ResponseCrewInner.originalLanguage) &&
            Objects.equals(this.originalTitle, personCombinedCredits200ResponseCrewInner.originalTitle) &&
            Objects.equals(this.overview, personCombinedCredits200ResponseCrewInner.overview) &&
            Objects.equals(this.popularity, personCombinedCredits200ResponseCrewInner.popularity) &&
            Objects.equals(this.posterPath, personCombinedCredits200ResponseCrewInner.posterPath) &&
            Objects.equals(this.releaseDate, personCombinedCredits200ResponseCrewInner.releaseDate) &&
            Objects.equals(this.title, personCombinedCredits200ResponseCrewInner.title) &&
            Objects.equals(this.video, personCombinedCredits200ResponseCrewInner.video) &&
            Objects.equals(this.voteAverage, personCombinedCredits200ResponseCrewInner.voteAverage) &&
            Objects.equals(this.voteCount, personCombinedCredits200ResponseCrewInner.voteCount) &&
            Objects.equals(this.creditId, personCombinedCredits200ResponseCrewInner.creditId) &&
            Objects.equals(this.department, personCombinedCredits200ResponseCrewInner.department) &&
            Objects.equals(this.job, personCombinedCredits200ResponseCrewInner.job) &&
            Objects.equals(this.mediaType, personCombinedCredits200ResponseCrewInner.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount, creditId, department, job, mediaType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PersonCombinedCredits200ResponseCrewInner {\n");
        sb.append("    adult: ").append(toIndentedString(adult)).append("\n");
        sb.append("    backdropPath: ").append(toIndentedString(backdropPath)).append("\n");
        sb.append("    genreIds: ").append(toIndentedString(genreIds)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    originalLanguage: ").append(toIndentedString(originalLanguage)).append("\n");
        sb.append("    originalTitle: ").append(toIndentedString(originalTitle)).append("\n");
        sb.append("    overview: ").append(toIndentedString(overview)).append("\n");
        sb.append("    popularity: ").append(toIndentedString(popularity)).append("\n");
        sb.append("    posterPath: ").append(toIndentedString(posterPath)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    video: ").append(toIndentedString(video)).append("\n");
        sb.append("    voteAverage: ").append(toIndentedString(voteAverage)).append("\n");
        sb.append("    voteCount: ").append(toIndentedString(voteCount)).append("\n");
        sb.append("    creditId: ").append(toIndentedString(creditId)).append("\n");
        sb.append("    department: ").append(toIndentedString(department)).append("\n");
        sb.append("    job: ").append(toIndentedString(job)).append("\n");
        sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
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
            joiner.add(String.format("%sadult%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getAdult()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `backdrop_path` to the URL query string
        if (getBackdropPath() != null) {
            joiner.add(String.format("%sbackdrop_path%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getBackdropPath()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `genre_ids` to the URL query string
        if (getGenreIds() != null) {
            for (int i = 0; i < getGenreIds().size(); i++) {
                joiner.add(String.format("%sgenre_ids%s%s=%s", prefix, suffix,
                    "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix),
                    URLEncoder.encode(ApiClientUtil.valueToString(getGenreIds().get(i)), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
            }
        }

        // add `id` to the URL query string
        if (getId() != null) {
            joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `original_language` to the URL query string
        if (getOriginalLanguage() != null) {
            joiner.add(String.format("%soriginal_language%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getOriginalLanguage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `original_title` to the URL query string
        if (getOriginalTitle() != null) {
            joiner.add(String.format("%soriginal_title%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getOriginalTitle()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `overview` to the URL query string
        if (getOverview() != null) {
            joiner.add(String.format("%soverview%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getOverview()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `popularity` to the URL query string
        if (getPopularity() != null) {
            joiner.add(String.format("%spopularity%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getPopularity()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `poster_path` to the URL query string
        if (getPosterPath() != null) {
            joiner.add(String.format("%sposter_path%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getPosterPath()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `release_date` to the URL query string
        if (getReleaseDate() != null) {
            joiner.add(String.format("%srelease_date%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getReleaseDate()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `title` to the URL query string
        if (getTitle() != null) {
            joiner.add(String.format("%stitle%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getTitle()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `video` to the URL query string
        if (getVideo() != null) {
            joiner.add(String.format("%svideo%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getVideo()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `vote_average` to the URL query string
        if (getVoteAverage() != null) {
            joiner.add(String.format("%svote_average%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getVoteAverage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `vote_count` to the URL query string
        if (getVoteCount() != null) {
            joiner.add(String.format("%svote_count%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getVoteCount()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `credit_id` to the URL query string
        if (getCreditId() != null) {
            joiner.add(String.format("%scredit_id%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getCreditId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `department` to the URL query string
        if (getDepartment() != null) {
            joiner.add(String.format("%sdepartment%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getDepartment()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `job` to the URL query string
        if (getJob() != null) {
            joiner.add(String.format("%sjob%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getJob()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `media_type` to the URL query string
        if (getMediaType() != null) {
            joiner.add(String.format("%smedia_type%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getMediaType()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}
