package com.example.ardeotis_platform.service;

import com.example.ardeotis_platform.dto.response.MatchingResultDto;
import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.model.ConsultantStatus;
import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import com.example.ardeotis_platform.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final ConsultantRepository consultantRepository;
    private final MissionRepository missionRepository;

    public List<MatchingResultDto> matchConsultantsToMission (UUID missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("Mission not found"));
        List<Consultant> consultants = consultantRepository.findByStatus(ConsultantStatus.AVAILABLE);
        return consultants.stream().map(cons -> calculateMatch(mission,cons))
                .sorted(Comparator.comparing(MatchingResultDto::getMatchScore).reversed())
                .toList();
    }

    private MatchingResultDto calculateMatch(Mission mission, Consultant cons) {
        return null;
    }
}
