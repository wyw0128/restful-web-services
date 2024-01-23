package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 characters")
    // used for validation purpose: minimum two characters
    // The default value is empty which indicates that the field name is used as the property name without any modifications,
    // but it can be specified to non-empty value to specify different name.
    // @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth date should be in the past") // for validation: birthdate should be in the past
    // @JsonProperty("birth_date")
    private LocalDate birthDate;
    // the mappedBy element must be used to specify the relationship field or property of the entity that is the owner of the relationship
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    // because we don't want posts to be part of the JSON responses for user bean, so we do JsonIgnore on this field.
    private List<Post> posts;

    public User() {

    }

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
