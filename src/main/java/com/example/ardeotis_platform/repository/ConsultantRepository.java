package com.example.ardeotis_platform.repository;

import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsultantRepository extends JpaRepository<Consultant, UUID> {

    Optional<Consultant> findByEmail(String email);
    boolean existsByEmail(String email);
}
