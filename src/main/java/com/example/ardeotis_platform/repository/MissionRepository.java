package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.model.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MissionRepository extends JpaRepository<Mission, UUID> {
    Page<Mission> findByStatus(MissionStatus status, Pageable pageable);


    @Query("""
select distinct m from Mission m
where
(:status is null or m.status = :status)
and (:client is null or lower(m.client) like lower(concat('%', :client, '%')))
""")
    Page<Mission> searchMissions(
            @Param("status") MissionStatus status,
            @Param("client") String client,
            Pageable pageable
    );

}
