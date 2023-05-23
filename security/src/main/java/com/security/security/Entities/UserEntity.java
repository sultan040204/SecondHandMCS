package com.security.security.Entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user",schema = "public")
public class UserEntity {
    @Id
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "phone")
    private String phone;

    @OneToMany
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ProductEntity> products =new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private List<FavoritesEntity> favorite =new ArrayList<>();



    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return firstname;
    }

    public void setName(String name) {
        this.firstname = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> cities) {
        this.products = cities;
    }


    public List<FavoritesEntity> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<FavoritesEntity> favorite) {
        this.favorite = favorite;
    }


    public UserEntity(){

    }
    public UserEntity(String name){
        this.firstname=name;
    }

    public UserEntity(Long id, String name) {
        this.id = id;
        this.firstname = name;
    }
}
