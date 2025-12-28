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
    KnownFor.JSON_PROPERTY_ADULT,
    KnownFor.JSON_PROPERTY_BACKDROP_PATH,
    KnownFor.JSON_PROPERTY_ID,
    KnownFor.JSON_PROPERTY_TITLE,
    KnownFor.JSON_PROPERTY_ORIGINAL_LANGUAGE,
    KnownFor.JSON_PROPERTY_ORIGINAL_TITLE,
    KnownFor.JSON_PROPERTY_OVERVIEW,
    KnownFor.JSON_PROPERTY_POSTER_PATH,
    KnownFor.JSON_PROPERTY_MEDIA_TYPE,
    KnownFor.JSON_PROPERTY_GENRE_IDS,
    KnownFor.JSON_PROPERTY_POPULARITY,
    KnownFor.JSON_PROPERTY_RELEASE_DATE,
    KnownFor.JSON_PROPERTY_VIDEO,
    KnownFor.JSON_PROPERTY_VOTE_AVERAGE,
    KnownFor.JSON_PROPERTY_VOTE_COUNT
})
public class KnownFor {
    public static final String JSON_PROPERTY_ADULT = "adult";
    @jakarta.annotation.Nullable
    private Boolean adult = true;

    public static final String JSON_PROPERTY_BACKDROP_PATH = "backdrop_path";
    @jakarta.annotation.Nullable
    private String backdropPath;

    public static final String JSON_PROPERTY_ID = "id";
    @jakarta.annotation.Nullable
    private Integer id = 0;

    public static final String JSON_PROPERTY_TITLE = "title";
    @jakarta.annotation.Nullable
    private String title;

    public static final String JSON_PROPERTY_ORIGINAL_LANGUAGE = "original_language";
    @jakarta.annotation.Nullable
    private String originalLanguage;

    public static final String JSON_PROPERTY_ORIGINAL_TITLE = "original_title";
    @jakarta.annotation.Nullable
    private String originalTitle;

    public static final String JSON_PROPERTY_OVERVIEW = "overview";
    @jakarta.annotation.Nullable
    private String overview;

    public static final String JSON_PROPERTY_POSTER_PATH = "poster_path";
    @jakarta.annotation.Nullable
    private String posterPath;

    public static final String JSON_PROPERTY_MEDIA_TYPE = "media_type";
    @jakarta.annotation.Nullable
    private String mediaType;

    public static final String JSON_PROPERTY_GENRE_IDS = "genre_ids";
    @jakarta.annotation.Nullable
    private List<Integer> genreIds = new ArrayList<>();

    public static final String JSON_PROPERTY_POPULARITY = "popularity";
    @jakarta.annotation.Nullable
    private BigDecimal popularity = new BigDecimal("0");

    public static final String JSON_PROPERTY_RELEASE_DATE = "release_date";
    @jakarta.annotation.Nullable
    private String releaseDate;

    public static final String JSON_PROPERTY_VIDEO = "video";
    @jakarta.annotation.Nullable
    private Boolean video = true;

    public static final String JSON_PROPERTY_VOTE_AVERAGE = "vote_average";
    @jakarta.annotation.Nullable
    private BigDecimal voteAverage = new BigDecimal("0");

    public static final String JSON_PROPERTY_VOTE_COUNT = "vote_count";
    @jakarta.annotation.Nullable
    private Integer voteCount = 0;

    public KnownFor() {
    }

    public KnownFor adult(@jakarta.annotation.Nullable Boolean adult) {
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


    public KnownFor backdropPath(@jakarta.annotation.Nullable String backdropPath) {
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


    public KnownFor id(@jakarta.annotation.Nullable Integer id) {
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


    public KnownFor title(@jakarta.annotation.Nullable String title) {
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


    public KnownFor originalLanguage(@jakarta.annotation.Nullable String originalLanguage) {
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


    public KnownFor originalTitle(@jakarta.annotation.Nullable String originalTitle) {
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


    public KnownFor overview(@jakarta.annotation.Nullable String overview) {
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


    public KnownFor posterPath(@jakarta.annotation.Nullable String posterPath) {
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


    public KnownFor mediaType(@jakarta.annotation.Nullable String mediaType) {
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


    public KnownFor genreIds(@jakarta.annotation.Nullable List<Integer> genreIds) {
        this.genreIds = genreIds;
        return this;
    }

    public KnownFor addGenreIdsItem(Integer genreIdsItem) {
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


    public KnownFor popularity(@jakarta.annotation.Nullable BigDecimal popularity) {
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


    public KnownFor releaseDate(@jakarta.annotation.Nullable String releaseDate) {
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


    public KnownFor video(@jakarta.annotation.Nullable Boolean video) {
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


    public KnownFor voteAverage(@jakarta.annotation.Nullable BigDecimal voteAverage) {
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


    public KnownFor voteCount(@jakarta.annotation.Nullable Integer voteCount) {
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
     * Return true if this search_person_200_response_results_inner_known_for_inner object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KnownFor searchPerson200ResponseResultsInnerKnownForInner = (KnownFor) o;
        return Objects.equals(this.adult, searchPerson200ResponseResultsInnerKnownForInner.adult) &&
            Objects.equals(this.backdropPath, searchPerson200ResponseResultsInnerKnownForInner.backdropPath) &&
            Objects.equals(this.id, searchPerson200ResponseResultsInnerKnownForInner.id) &&
            Objects.equals(this.title, searchPerson200ResponseResultsInnerKnownForInner.title) &&
            Objects.equals(this.originalLanguage, searchPerson200ResponseResultsInnerKnownForInner.originalLanguage) &&
            Objects.equals(this.originalTitle, searchPerson200ResponseResultsInnerKnownForInner.originalTitle) &&
            Objects.equals(this.overview, searchPerson200ResponseResultsInnerKnownForInner.overview) &&
            Objects.equals(this.posterPath, searchPerson200ResponseResultsInnerKnownForInner.posterPath) &&
            Objects.equals(this.mediaType, searchPerson200ResponseResultsInnerKnownForInner.mediaType) &&
            Objects.equals(this.genreIds, searchPerson200ResponseResultsInnerKnownForInner.genreIds) &&
            Objects.equals(this.popularity, searchPerson200ResponseResultsInnerKnownForInner.popularity) &&
            Objects.equals(this.releaseDate, searchPerson200ResponseResultsInnerKnownForInner.releaseDate) &&
            Objects.equals(this.video, searchPerson200ResponseResultsInnerKnownForInner.video) &&
            Objects.equals(this.voteAverage, searchPerson200ResponseResultsInnerKnownForInner.voteAverage) &&
            Objects.equals(this.voteCount, searchPerson200ResponseResultsInnerKnownForInner.voteCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adult, backdropPath, id, title, originalLanguage, originalTitle, overview, posterPath, mediaType, genreIds, popularity, releaseDate, video, voteAverage, voteCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchPerson200ResponseResultsInnerKnownForInner {\n");
        sb.append("    adult: ").append(toIndentedString(adult)).append("\n");
        sb.append("    backdropPath: ").append(toIndentedString(backdropPath)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    originalLanguage: ").append(toIndentedString(originalLanguage)).append("\n");
        sb.append("    originalTitle: ").append(toIndentedString(originalTitle)).append("\n");
        sb.append("    overview: ").append(toIndentedString(overview)).append("\n");
        sb.append("    posterPath: ").append(toIndentedString(posterPath)).append("\n");
        sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
        sb.append("    genreIds: ").append(toIndentedString(genreIds)).append("\n");
        sb.append("    popularity: ").append(toIndentedString(popularity)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
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
            joiner.add(String.format("%sadult%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getAdult()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `backdrop_path` to the URL query string
        if (getBackdropPath() != null) {
            joiner.add(String.format("%sbackdrop_path%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getBackdropPath()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `id` to the URL query string
        if (getId() != null) {
            joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `title` to the URL query string
        if (getTitle() != null) {
            joiner.add(String.format("%stitle%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getTitle()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
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

        // add `poster_path` to the URL query string
        if (getPosterPath() != null) {
            joiner.add(String.format("%sposter_path%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getPosterPath()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `media_type` to the URL query string
        if (getMediaType() != null) {
            joiner.add(String.format("%smedia_type%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getMediaType()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `genre_ids` to the URL query string
        if (getGenreIds() != null) {
            for (int i = 0; i < getGenreIds().size(); i++) {
                joiner.add(String.format("%sgenre_ids%s%s=%s", prefix, suffix,
                    "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix),
                    URLEncoder.encode(ApiClientUtil.valueToString(getGenreIds().get(i)), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
            }
        }

        // add `popularity` to the URL query string
        if (getPopularity() != null) {
            joiner.add(String.format("%spopularity%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getPopularity()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `release_date` to the URL query string
        if (getReleaseDate() != null) {
            joiner.add(String.format("%srelease_date%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getReleaseDate()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
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

        return joiner.toString();
    }
}
