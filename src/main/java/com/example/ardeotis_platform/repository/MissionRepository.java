package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.model.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MissionRepository extends JpaRepository<Mission, UUID> {
    Page<Mission> findByStatus(MissionStatus status, Pageable pageable);

    @Query("select m.status, count(m) from mission m group by m.status")
    List<Object[]> countByStats();

}
