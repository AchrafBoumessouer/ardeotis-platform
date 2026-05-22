package com.example.ardeotis_platform.dto.request;

import com.example.ardeotis_platform.model.PositionnementStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionnementRequestDto {
    private Long consultantId;
    private Long missionId;
    private PositionnementStatus status;
    private String commentaire;
}
