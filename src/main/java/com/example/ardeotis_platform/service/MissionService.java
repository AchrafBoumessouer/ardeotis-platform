package com.example.ardeotis_platform.service;


import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.mapper.MissionMapper;
import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.model.MissionStatus;
import com.example.ardeotis_platform.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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

    public Page<MissionResponseDto> getAllMissions(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return missionRepository.findByStatus(MissionStatus.ACTIVE,pageable)
                .map(missionMapper::toResponseDto);
    }

    public Page<MissionResponseDto> getArchivedMissions(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return missionRepository.findByStatus(MissionStatus.ARCHIVED,pageable)
                .map(missionMapper::toResponseDto);
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

    public void archiveMission(UUID id) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new RuntimeException("Mission not found "));
        mission.setStatus(MissionStatus.ARCHIVED);
        missionRepository.save(mission);
    }


}
