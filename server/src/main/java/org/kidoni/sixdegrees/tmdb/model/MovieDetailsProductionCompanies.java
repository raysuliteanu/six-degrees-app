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
        MovieDetailsProductionCompanies.JSON_PROPERTY_ID,
        MovieDetailsProductionCompanies.JSON_PROPERTY_LOGO_PATH,
        MovieDetailsProductionCompanies.JSON_PROPERTY_NAME,
        MovieDetailsProductionCompanies.JSON_PROPERTY_ORIGIN_COUNTRY
})
@Node
public class MovieDetailsProductionCompanies {
    public static final String JSON_PROPERTY_ID = "id";
    @jakarta.annotation.Nullable
    @Id
    private Integer id = 0;

    public static final String JSON_PROPERTY_LOGO_PATH = "logo_path";
    @jakarta.annotation.Nullable
    private String logoPath;

    public static final String JSON_PROPERTY_NAME = "name";
    @jakarta.annotation.Nullable
    private String name;

    public static final String JSON_PROPERTY_ORIGIN_COUNTRY = "origin_country";
    @jakarta.annotation.Nullable
    private String originCountry;

    public MovieDetailsProductionCompanies() {
    }

    public MovieDetailsProductionCompanies id(@jakarta.annotation.Nullable Integer id) {
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

    public MovieDetailsProductionCompanies logoPath(@jakarta.annotation.Nullable String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    /**
     * Get logoPath
     *
     * @return logoPath
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_LOGO_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getLogoPath() {
        return logoPath;
    }

    @JsonProperty(JSON_PROPERTY_LOGO_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLogoPath(@jakarta.annotation.Nullable String logoPath) {
        this.logoPath = logoPath;
    }

    public MovieDetailsProductionCompanies name(@jakarta.annotation.Nullable String name) {
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

    public MovieDetailsProductionCompanies originCountry(@jakarta.annotation.Nullable String originCountry) {
        this.originCountry = originCountry;
        return this;
    }

    /**
     * Get originCountry
     *
     * @return originCountry
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ORIGIN_COUNTRY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getOriginCountry() {
        return originCountry;
    }

    @JsonProperty(JSON_PROPERTY_ORIGIN_COUNTRY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOriginCountry(@jakarta.annotation.Nullable String originCountry) {
        this.originCountry = originCountry;
    }

    /**
     * Return true if this movie_details_200_response_production_companies_inner
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
        MovieDetailsProductionCompanies movieDetails200ResponseProductionCompaniesInner = (MovieDetailsProductionCompanies) o;
        return Objects.equals(this.id, movieDetails200ResponseProductionCompaniesInner.id) &&
                Objects.equals(this.logoPath, movieDetails200ResponseProductionCompaniesInner.logoPath) &&
                Objects.equals(this.name, movieDetails200ResponseProductionCompaniesInner.name) &&
                Objects.equals(this.originCountry, movieDetails200ResponseProductionCompaniesInner.originCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logoPath, name, originCountry);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MovieDetails200ResponseProductionCompaniesInner {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    logoPath: ").append(toIndentedString(logoPath)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    originCountry: ").append(toIndentedString(originCountry)).append("\n");
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

        // add `id` to the URL query string
        if (getId() != null) {
            joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `logo_path` to the URL query string
        if (getLogoPath() != null) {
            joiner.add(String.format("%slogo_path%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getLogoPath()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `name` to the URL query string
        if (getName() != null) {
            joiner.add(String.format("%sname%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getName()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `origin_country` to the URL query string
        if (getOriginCountry() != null) {
            joiner.add(String.format("%sorigin_country%s=%s", prefix, suffix,
                    URLEncoder.encode(ModelUtil.valueToString(getOriginCountry()), StandardCharsets.UTF_8)
                            .replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}
