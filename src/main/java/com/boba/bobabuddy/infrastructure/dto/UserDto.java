package com.boba.bobabuddy.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.hateoas.server.core.Relation;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Objects matching the corresponding entities in the domain layer
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "email", scope = UserDto.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(collectionRelation = "users", itemRelation = "user")
public class UserDto {

    private String email;
    private String name;
    private String password;
    @JsonIdentityReference(alwaysAsId = true)
    private Set<SimpleRatingDto> ratings;


    public UserDto(final String name, final String email, final String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.ratings = new HashSet<>();
    }

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<SimpleRatingDto> getRatings() {
        return ratings;
    }

    public void setRatings(Set<SimpleRatingDto> ratings) {
        this.ratings = ratings;
    }
}
