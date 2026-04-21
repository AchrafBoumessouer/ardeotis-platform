package com.example.ardeotis_platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 120)
    private String password;
    @Column(nullable = false, length = 120)
    private String firstName;
    @Column(nullable = false, length = 120)
    private String lastName;
    @Column(nullable = false, length = 30)
    private String role;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }



}
