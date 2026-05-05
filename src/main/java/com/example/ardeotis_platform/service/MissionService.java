package com.example.ardeotis_platform.service;


import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.mapper.MissionMapper;
import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;

    public MissionResponseDto createMission(MissionRequestDto requestDto) {
        Mission Mission = missionMapper.toEntity( requestDto );
        Mission savedMission = missionRepository.save(Mission);
        return missionMapper.toResponseDto( savedMission );
    }

    public List<MissionResponseDto> getAllMissions(){
        return missionRepository.findAll()
                .stream()
                .map(missionMapper::toResponseDto)
                .toList();
    }

    public MissionResponseDto getMissionById(UUID id) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new RuntimeException("Mission not found"));
        return missionMapper.toResponseDto(mission);
    }

    public MissionResponseDto updateMission(UUID id, MissionRequestDto request) {
        Mission mission = missionRepository.findById(id).orElseThrow(()-> new RuntimeException("Mission not found"));
        missionMapper.updateEntityFromDto(request,mission);
        Mission updatedMission = missionRepository.save(mission);
        return missionMapper.toResponseDto(updatedMission);
    }

    public void deleteMission(UUID id) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new RuntimeException("Mission not found "));
        missionRepository.deleteById(id);
    }


}
