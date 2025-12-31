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
        MovieDetailsProductionCountries.JSON_PROPERTY_ISO31661,
        MovieDetailsProductionCountries.JSON_PROPERTY_NAME
})
@Node
public class MovieDetailsProductionCountries {
    public static final String JSON_PROPERTY_ISO31661 = "iso_3166_1";
    @jakarta.annotation.Nullable
    private String iso31661;

    public static final String JSON_PROPERTY_NAME = "name";
    @jakarta.annotation.Nullable
    @Id
    private String name;

    public MovieDetailsProductionCountries() {
    }

    public MovieDetailsProductionCountries iso31661(@jakarta.annotation.Nullable String iso31661) {
        this.iso31661 = iso31661;
        return this;
    }

    /**
     * Get iso31661
     *
     * @return iso31661
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ISO31661)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getIso31661() {
        return iso31661;
    }

    @JsonProperty(JSON_PROPERTY_ISO31661)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIso31661(@jakarta.annotation.Nullable String iso31661) {
        this.iso31661 = iso31661;
    }

    public MovieDetailsProductionCountries name(@jakarta.annotation.Nullable String name) {
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
     * Return true if this movie_details_200_response_production_countries_inner
     * object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDetailsProductionCountries movieDetails200ResponseProductionCountriesInner = (MovieDetailsProductionCountries) o;
        return Objects.equals(this.iso31661, movieDetails200ResponseProductionCountriesInner.iso31661) &&
                Objects.equals(this.name, movieDetails200ResponseProductionCountriesInner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso31661, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MovieDetails200ResponseProductionCountriesInner {\n");
        sb.append("    iso31661: ").append(toIndentedString(iso31661)).append("\n");
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

        // add `iso_3166_1` to the URL query string
        if (getIso31661() != null) {
            joiner.add(String.format("%siso_3166_1%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getIso31661()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `name` to the URL query string
        if (getName() != null) {
            joiner.add(String.format("%sname%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getName()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}
