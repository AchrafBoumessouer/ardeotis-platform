package com.example.ardeotis_platform.dto.request;

import com.example.ardeotis_platform.model.PositionnementStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PositionnementRequestDto {
    private UUID consultantId;
    private UUID missionId;
    private PositionnementStatus status;
    private String commentaire;
}
