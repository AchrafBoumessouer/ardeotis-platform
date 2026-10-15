package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.model.ConsultantStatus;
import com.example.ardeotis_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsultantRepository extends JpaRepository<Consultant, UUID> {

    List<Consultant> findByStatus(ConsultantStatus status);

    @Query("select c.status, count(c) from Consultant c group by c.status")
    List<Object[]> countByStats();
}
