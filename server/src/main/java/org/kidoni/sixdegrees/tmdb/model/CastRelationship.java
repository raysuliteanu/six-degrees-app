package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * Represents the CAST relationship between a Credit (Movie/TvShow) and an Actor.
 * Captures additional properties about the role, such as character name.
 */
@RelationshipProperties
public class CastRelationship {

    @Id
    @GeneratedValue
    private Long relationshipId;

    @TargetNode
    private Actor actor;

    @JsonProperty("character")
    private String character;

    @JsonProperty("credit_id")
    private String creditId;

    public CastRelationship() {
    }

    public CastRelationship(Actor actor, String character, String creditId) {
        this.actor = actor;
        this.character = character;
        this.creditId = creditId;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
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
