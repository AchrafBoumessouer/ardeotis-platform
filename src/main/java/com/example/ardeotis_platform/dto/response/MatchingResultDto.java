package com.example.ardeotis_platform.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
public class MatchingResultDto {
    private UUID consultantId;
    private String consultantName;
    private Integer matchScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private Boolean available;
}
