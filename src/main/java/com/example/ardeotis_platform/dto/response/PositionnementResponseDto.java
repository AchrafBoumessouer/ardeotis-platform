package com.example.ardeotis_platform.dto.response;

import com.example.ardeotis_platform.model.PositionnementStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PositionnementResponseDto {
    private Long id;
    private Long consultantId;
    private Long missionId;
    private PositionnementStatus status;
    private String commentaire;
    private LocalDateTime createdAt;
}
