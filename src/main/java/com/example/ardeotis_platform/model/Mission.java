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

    @ElementCollection
    @CollectionTable(name = "mission_skills", joinColumns = @JoinColumn(name = "mission_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status = MissionStatus.ACTIVE;


    private LocalDate startDate;
    private LocalDate endDate;
}
