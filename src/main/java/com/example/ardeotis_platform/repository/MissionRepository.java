package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.model.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MissionRepository extends JpaRepository<Mission, UUID> {
    Page<Mission> findByStatus(MissionStatus status, Pageable pageable);

}
