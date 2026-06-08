package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.model.Positionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PositionnementRepository extends JpaRepository<Positionnement, Long> {
    Optional<Positionnement> findByMissionIDAndConsultantID(UUID missionId, UUID consultantId);

    @Query("select p.status, count(p) from Positionnement p group by p.status")
    List<Object[]> countByStats();
}
