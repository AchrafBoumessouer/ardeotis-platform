package com.example.ardeotis_platform.dto.response;

import com.example.ardeotis_platform.model.PositionnementStatus;

import java.time.LocalDateTime;

public record HistoriquePositionnementResponseDto(
        Long id,
        PositionnementStatus ancienStatus,
        PositionnementStatus newStatus,
        LocalDateTime dateChagement,
        String commentaire
) {
}
