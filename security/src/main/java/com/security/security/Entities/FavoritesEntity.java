package com.security.security.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
public class FavoritesEntity {
    @Id
    @Column(name = "id",nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JsonIgnore
    @Column(name = "userId")
    private Integer userId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity user;

    @JsonIgnore
    public Integer getUserId() {
        return userId;
    }
    @JsonProperty
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
