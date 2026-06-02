package com.example.ardeotis_platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Positionnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Consultant consultant;

    @ManyToOne
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private PositionnementStatus status;

    private LocalDateTime lastStatusUpdateAt;

}
