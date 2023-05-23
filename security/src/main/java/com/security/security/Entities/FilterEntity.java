package com.security.security.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;


@Entity
@Table(name = "filter",schema = "public")
public class FilterEntity {
    @Id
    @Column(name = "id",nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "top")
    private boolean top;

    @Column(name = "pants")
    private boolean pants;

    @Column(name = "colors")
    @NotNull
    private String colors;

    @Column(name = "size")
    private Integer size;

    @Column(name = "headdress")
    private boolean headdress;

    @Column(name = "bottom")
    private boolean bottom;

    @Column(name = "shoes")
    private boolean shoes;

    @Column(name = "gender")
    private boolean gender;

    @JsonIgnore
    @Column(name = "userId")
    private Integer userId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity user;

    public FilterEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isPants() {
        return pants;
    }

    public void setPants(boolean pants) {
        this.pants = pants;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isHeaddress() {
        return headdress;
    }

    public void setHeaddress(boolean headdress) {
        this.headdress = headdress;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public boolean isShoes() {
        return shoes;
    }

    public void setShoes(boolean shoes) {
        this.shoes = shoes;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
