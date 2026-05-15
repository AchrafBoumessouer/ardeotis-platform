package com.example.ardeotis_platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "missions")
@Getter
@Setter
public class Mission {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID ID;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String client;

    @Column(nullable = false)
    private Set<String> skills = new HashSet<>();


    @Column(nullable = false, length = 30)
    private String status;


    private LocalDate startDate;
    private LocalDate endDate;
}
