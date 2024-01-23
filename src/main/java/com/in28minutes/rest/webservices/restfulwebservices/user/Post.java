package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;
    // We can choose different attribute, fetch decides whether the association should be lazily loaded or must be eagerly fetched.
    // If you choose eager fetch, along with the post details, the user details will also be fetched.
    // A lazy type means if we fetch the post, we don't want the user details that are associated with the post to be fetched as well.
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
