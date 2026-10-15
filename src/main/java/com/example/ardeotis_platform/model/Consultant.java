package com.example.ardeotis_platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "consultants")
@Getter
@Setter
public class Consultant {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID ID;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private Set<String> skills = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ConsultantStatus status;

    @Column( nullable = false)
    private Boolean available;
}
