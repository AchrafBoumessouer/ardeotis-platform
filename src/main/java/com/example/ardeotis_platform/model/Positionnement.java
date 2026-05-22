package com.example.ardeotis_platform.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "positionnement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Positionnement {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "consultant_id", nullable = false)
     private  Consultant consultant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private  Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PositionnementStatus status;

    @Column(length = 1000)
    private String commentaire;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
