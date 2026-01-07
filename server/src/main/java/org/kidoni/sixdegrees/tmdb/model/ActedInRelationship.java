package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * Represents the ACTED_IN relationship between an Actor and a Credit (Movie/TvShow).
 * Captures additional properties about the role, such as character name.
 */
@RelationshipProperties
public class ActedInRelationship {

    @Id
    @GeneratedValue
    private Long relationshipId;

    @TargetNode
    private Credit credit;

    @JsonProperty("character")
    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    public ActedInRelationship() {
    }

    public ActedInRelationship(Credit credit, String character, String creditId) {
        this.credit = credit;
        this.character = character;
        this.creditId = creditId;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }
}
