package com.example.ardeotis_platform.controller;

import com.example.ardeotis_platform.dto.response.MatchingResultDto;
import com.example.ardeotis_platform.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @GetMapping("/missions/{missionId}/matching")
    public List<MatchingResultDto> matchConsultantsToMission(@PathVariable UUID missionId){
        return matchingService.matchConsultantsToMission(missionId);
    }
}
