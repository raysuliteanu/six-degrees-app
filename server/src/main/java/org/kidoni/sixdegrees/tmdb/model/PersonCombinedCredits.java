package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.Transient;
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
    @Transient
    private List<PersonCombinedCreditsCast> cast = new ArrayList<>();

    public static final String JSON_PROPERTY_CREW = "crew";
    @jakarta.annotation.Nullable
    @Transient
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonCombinedCredits combinedCredits = (PersonCombinedCredits) o;
        return Objects.equals(this.cast, combinedCredits.cast) &&
                Objects.equals(this.crew, combinedCredits.crew) &&
                Objects.equals(this.id, combinedCredits.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cast, crew, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PersonCombinedCredits {\n");
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
}
