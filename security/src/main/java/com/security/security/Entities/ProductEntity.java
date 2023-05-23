package com.security.security.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity(name = "Product")
@Table(name = "products",schema = "public")
public class ProductEntity {
    @Id
    @Column(name = "id",nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NotNull
    private Integer price;

    @Column(name = "image")
    private String image;

    @Column(name = "filter")
    private String filter;

    @Column(name = "active")
    @NotNull
    private boolean active;

    @Column(name = "data")
    private String data;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ProductEntity(){

    }
    public ProductEntity(Integer id, String name, Integer userId) {
        this.id = id;
        this.name = name;
        this.userId=userId;

    }

    public ProductEntity(Integer id, String name, String description, Integer price, String image, String filter, boolean active, String data, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.filter = filter;
        this.active = active;
        this.data = data;
        this.userId = userId;
    }
}
