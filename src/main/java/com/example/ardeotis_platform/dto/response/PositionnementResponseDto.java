package com.example.ardeotis_platform.dto.response;

import com.example.ardeotis_platform.model.PositionnementStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PositionnementResponseDto {
    private Long id;
    private UUID consultantId;
    private UUID missionId;
    private PositionnementStatus status;
    private String commentaire;
    private LocalDateTime createdAt;
}
