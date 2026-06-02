package com.example.ardeotis_platform.service;

import com.example.ardeotis_platform.dto.response.MatchingResultDto;
import com.example.ardeotis_platform.model.*;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import com.example.ardeotis_platform.repository.MissionRepository;
import com.example.ardeotis_platform.repository.PositionnementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final ConsultantRepository consultantRepository;
    private final MissionRepository missionRepository;
    private final PositionnementRepository positionnementRepository;

    public List<MatchingResultDto> matchConsultantsToMission (UUID missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new RuntimeException("Mission not found"));
        List<Consultant> consultants = consultantRepository.findByStatus(ConsultantStatus.AVAILABLE);
        return consultants.stream().map(cons -> calculateMatch(mission,cons))
                .sorted(Comparator.comparing(MatchingResultDto::getMatchScore).reversed())
                .toList();
    }

    private MatchingResultDto calculateMatch(Mission mission, Consultant cons) {
        int score = 0;
        if(cons.getStatus() == ConsultantStatus.AVAILABLE){
            score += 20;
        }
        score += calculateSkilsScore(mission.getSkills(),cons.getSkills());
        List<String> matchSkills = mission.getSkills().stream().filter(ms -> cons.getSkills().stream().anyMatch((cs -> cs.equalsIgnoreCase(ms)))).toList();
        List<String> missingSkills = mission.getSkills().stream().filter(ms -> cons.getSkills().stream().noneMatch((cs -> cs.equalsIgnoreCase(ms)))).toList();
        Positionnement posi = positionnementRepository.findByMissionAndConsultant(mission,cons).orElse(null);
        return MatchingResultDto.builder().consultantId(cons.getID())
                                          .consultantName(cons.getFirstName() + " "+ cons.getLastName())
                                          .positionnementId(posi != null ? posi.getId() : null)
                                          .status(posi != null ? posi.getStatus() : PositionnementStatus.INTERET_EXPRIME )
                                          .matchScore(score).matchedSkills(matchSkills).missingSkills(missingSkills).available(cons.getStatus() == ConsultantStatus.AVAILABLE).build();
    }

    private int calculateSkilsScore(Set<String> missionSkills, Set<String> consultantSkills){
        if(missionSkills == null || missionSkills.isEmpty() || consultantSkills == null || consultantSkills.isEmpty()){
            return 0;
        }

        long matchedskill = missionSkills.stream().filter(ms -> consultantSkills.stream().anyMatch((cs -> cs.equalsIgnoreCase(ms)))).count();
        return (int) ((matchedskill * 50)/missionSkills.size());
    }
}
