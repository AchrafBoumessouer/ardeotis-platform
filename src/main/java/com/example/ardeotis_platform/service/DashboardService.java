package com.example.ardeotis_platform.service;


import com.example.ardeotis_platform.dto.response.DashboardStatsResponseDto;
import com.example.ardeotis_platform.model.ConsultantStatus;
import com.example.ardeotis_platform.model.MissionStatus;
import com.example.ardeotis_platform.model.PositionnementStatus;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import com.example.ardeotis_platform.repository.MissionRepository;
import com.example.ardeotis_platform.repository.PositionnementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final ConsultantRepository consultantRepository;
    private final MissionRepository missionRepository;
    private final PositionnementRepository positionnementRepository;

    public DashboardStatsResponseDto getStats(){
        return new DashboardStatsResponseDto(
                buildConsultantsstats(),
                buildMissionsstats(),
                buildPositionnementsstats()
        );
    }
    private Map<String, Long> buildConsultantsstats(){
    Map<String,Long> result = convertToMap(consultantRepository.countByStats());
    for(ConsultantStatus status : ConsultantStatus.values()){
        result.putIfAbsent(status.name(),0L);
    }
    return result;
    }

    private Map<String, Long> buildMissionsstats(){
        Map<String,Long> result = convertToMap(missionRepository.countByStats());
        for(MissionStatus status : MissionStatus.values()){
            result.putIfAbsent(status.name(),0L);
        }
        return result;
    }

    private Map<String, Long> buildPositionnementsstats(){
        Map<String,Long> result = convertToMap(positionnementRepository.countByStats());
        for(PositionnementStatus status : PositionnementStatus.values()){
            result.putIfAbsent(status.name(),0L);
        }
        return result;
    }

    private Map<String, Long> convertToMap(List<Object[]> rows) {
        Map<String, Long> result = new HashMap<>();

        for (Object[] row : rows){
            result.put(row[0].toString(),((Number) row[1]).longValue());
        }
        return result;
    }
}
