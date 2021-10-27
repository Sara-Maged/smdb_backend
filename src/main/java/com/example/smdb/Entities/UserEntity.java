package com.example.smdb.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user") // table name = class name // no need to add name
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String password;
    //private String role;
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.MERGE)
    private Collection<RatingEntity> ratings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<MovieEntity> flags = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(int id, String email, String password, boolean active, Collection<RoleEntity> roles, Collection<RatingEntity> ratings) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.ratings = ratings;
    }

    public UserEntity(String email, String password, boolean active, Collection<RoleEntity> roles, Collection<RatingEntity> ratings) {
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userName) {
        this.email = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getRoles() {
//        return roles;
//    }
//
//    public void setRoles(String roles) {
//        this.roles = roles;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    public Collection<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(Collection<RatingEntity> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                ", ratings=" + ratings +
                '}';
    }
}
