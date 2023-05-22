package com.auth.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @Column(name = "last_chang_password")
    private LocalDate lastChangePassword;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
