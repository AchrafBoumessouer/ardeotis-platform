package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.HistoriquePositionnement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriquePositionRepository extends JpaRepository<HistoriquePositionnement,Long> {
List<HistoriquePositionnement> findByPositionnementIdOrderByLastStatusUpdatedAtDesc(Long positionnementId);
}
