package com.example.ardeotis_platform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoriquePositionnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Positionnement positionnement;

    @Enumerated(EnumType.STRING)
    private PositionnementStatus ancienStatus;

    @Enumerated(EnumType.STRING)
    private PositionnementStatus newStatus;

    private LocalDateTime lastStatusUpdateAt;

    private String commentaire;
}
