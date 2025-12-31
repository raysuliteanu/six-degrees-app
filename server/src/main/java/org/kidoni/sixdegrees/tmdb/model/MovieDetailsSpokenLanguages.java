package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.StringJoiner;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@JsonPropertyOrder({
        MovieDetailsSpokenLanguages.JSON_PROPERTY_ENGLISH_NAME,
        MovieDetailsSpokenLanguages.JSON_PROPERTY_ISO6391,
        MovieDetailsSpokenLanguages.JSON_PROPERTY_NAME
})
@Node
public class MovieDetailsSpokenLanguages {
    public static final String JSON_PROPERTY_ENGLISH_NAME = "english_name";
    @jakarta.annotation.Nullable
    private String englishName;

    public static final String JSON_PROPERTY_ISO6391 = "iso_639_1";
    @jakarta.annotation.Nullable
    private String iso6391;

    public static final String JSON_PROPERTY_NAME = "name";
    @jakarta.annotation.Nullable
    @Id
    private String name;

    public MovieDetailsSpokenLanguages() {
    }

    public MovieDetailsSpokenLanguages englishName(@jakarta.annotation.Nullable String englishName) {
        this.englishName = englishName;
        return this;
    }

    /**
     * Get englishName
     *
     * @return englishName
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ENGLISH_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getEnglishName() {
        return englishName;
    }

    @JsonProperty(JSON_PROPERTY_ENGLISH_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEnglishName(@jakarta.annotation.Nullable String englishName) {
        this.englishName = englishName;
    }

    public MovieDetailsSpokenLanguages iso6391(@jakarta.annotation.Nullable String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    /**
     * Get iso6391
     *
     * @return iso6391
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ISO6391)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getIso6391() {
        return iso6391;
    }

    @JsonProperty(JSON_PROPERTY_ISO6391)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIso6391(@jakarta.annotation.Nullable String iso6391) {
        this.iso6391 = iso6391;
    }

    public MovieDetailsSpokenLanguages name(@jakarta.annotation.Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getName() {
        return name;
    }

    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setName(@jakarta.annotation.Nullable String name) {
        this.name = name;
    }

    /**
     * Return true if this movie_details_200_response_spoken_languages_inner object
     * is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDetailsSpokenLanguages movieDetails200ResponseSpokenLanguagesInner = (MovieDetailsSpokenLanguages) o;
        return Objects.equals(this.englishName, movieDetails200ResponseSpokenLanguagesInner.englishName) &&
                Objects.equals(this.iso6391, movieDetails200ResponseSpokenLanguagesInner.iso6391) &&
                Objects.equals(this.name, movieDetails200ResponseSpokenLanguagesInner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishName, iso6391, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MovieDetails200ResponseSpokenLanguagesInner {\n");
        sb.append("    englishName: ").append(toIndentedString(englishName)).append("\n");
        sb.append("    iso6391: ").append(toIndentedString(iso6391)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
        } else {
            // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
            prefix = prefix + "[";
            suffix = "]";
            containerSuffix = "]";
            containerPrefix = "[";
        }

        StringJoiner joiner = new StringJoiner("&");

        // add `english_name` to the URL query string
        if (getEnglishName() != null) {
            joiner.add(String.format("%senglish_name%s=%s", prefix, suffix,
                    URLEncoder.encode(ModelUtil.valueToString(getEnglishName()), StandardCharsets.UTF_8)
                            .replaceAll("\\+", "%20")));
        }

        // add `iso_639_1` to the URL query string
        if (getIso6391() != null) {
            joiner.add(String.format("%siso_639_1%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getIso6391()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `name` to the URL query string
        if (getName() != null) {
            joiner.add(String.format("%sname%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getName()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}
