package org.kidoni.sixdegrees.tmdb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.kidoni.sixdegrees.tmdb.client.ApiClientUtil;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@JsonPropertyOrder({
    PersonCombinedCredits.JSON_PROPERTY_CAST,
    PersonCombinedCredits.JSON_PROPERTY_CREW,
    PersonCombinedCredits.JSON_PROPERTY_ID
})
@Node
public class PersonCombinedCredits {
    public static final String JSON_PROPERTY_CAST = "cast";
    @jakarta.annotation.Nullable
    private List<PersonCombinedCreditsCast> cast = new ArrayList<>();

    public static final String JSON_PROPERTY_CREW = "crew";
    @jakarta.annotation.Nullable
    private List<PersonCombinedCreditsCrew> crew = new ArrayList<>();

    public static final String JSON_PROPERTY_ID = "id";
    @Id
    private Integer id = 0;

    public PersonCombinedCredits() {
    }

    public PersonCombinedCredits cast(@jakarta.annotation.Nullable List<PersonCombinedCreditsCast> cast) {
        this.cast = cast;
        return this;
    }

    public PersonCombinedCredits addCastItem(PersonCombinedCreditsCast castItem) {
        if (this.cast == null) {
            this.cast = new ArrayList<>();
        }
        this.cast.add(castItem);
        return this;
    }

    /**
     * Get cast
     *
     * @return cast
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_CAST)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<PersonCombinedCreditsCast> getCast() {
        return cast;
    }


    @JsonProperty(JSON_PROPERTY_CAST)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCast(@jakarta.annotation.Nullable List<PersonCombinedCreditsCast> cast) {
        this.cast = cast;
    }


    public PersonCombinedCredits crew(@jakarta.annotation.Nullable List<PersonCombinedCreditsCrew> crew) {
        this.crew = crew;
        return this;
    }

    public PersonCombinedCredits addCrewItem(PersonCombinedCreditsCrew crewItem) {
        if (this.crew == null) {
            this.crew = new ArrayList<>();
        }
        this.crew.add(crewItem);
        return this;
    }

    /**
     * Get crew
     *
     * @return crew
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_CREW)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<PersonCombinedCreditsCrew> getCrew() {
        return crew;
    }


    @JsonProperty(JSON_PROPERTY_CREW)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCrew(@jakarta.annotation.Nullable List<PersonCombinedCreditsCrew> crew) {
        this.crew = crew;
    }


    public PersonCombinedCredits id(@jakarta.annotation.Nullable Integer id) {
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


    /**
     * Return true if this person_combined_credits_200_response object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonCombinedCredits personCombinedCredits200Response = (PersonCombinedCredits) o;
        return Objects.equals(this.cast, personCombinedCredits200Response.cast) &&
            Objects.equals(this.crew, personCombinedCredits200Response.crew) &&
            Objects.equals(this.id, personCombinedCredits200Response.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cast, crew, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PersonCombinedCredits200Response {\n");
        sb.append("    cast: ").append(toIndentedString(cast)).append("\n");
        sb.append("    crew: ").append(toIndentedString(crew)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

        // add `cast` to the URL query string
        if (getCast() != null) {
            for (int i = 0; i < getCast().size(); i++) {
                if (getCast().get(i) != null) {
                    joiner.add(getCast().get(i).toUrlQueryString(String.format("%scast%s%s", prefix, suffix,
                        "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        // add `crew` to the URL query string
        if (getCrew() != null) {
            for (int i = 0; i < getCrew().size(); i++) {
                if (getCrew().get(i) != null) {
                    joiner.add(getCrew().get(i).toUrlQueryString(String.format("%screw%s%s", prefix, suffix,
                        "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        // add `id` to the URL query string
        if (getId() != null) {
            joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder.encode(ApiClientUtil.valueToString(getId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}
