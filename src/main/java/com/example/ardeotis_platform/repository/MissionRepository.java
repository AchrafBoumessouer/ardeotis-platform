package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MissionRepository extends JpaRepository<Mission, UUID> {

}
