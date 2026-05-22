package com.example.ardeotis_platform.service;

import com.example.ardeotis_platform.dto.request.PositionnementRequestDto;
import com.example.ardeotis_platform.dto.response.PositionnementResponseDto;
import com.example.ardeotis_platform.mapper.PositionnementMapper;
import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.model.Mission;
import com.example.ardeotis_platform.model.Positionnement;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import com.example.ardeotis_platform.repository.MissionRepository;
import com.example.ardeotis_platform.repository.PositionnementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionnementService {
    private final PositionnementRepository positionnementRepository;
    private final ConsultantRepository consultantRepository;
    private final MissionRepository missionRepository;
    private final PositionnementMapper mapper;

    public PositionnementResponseDto create(PositionnementRequestDto dto){
        Consultant consultant = consultantRepository.findById(dto.getConsultantId()).orElseThrow(() -> new RuntimeException("Consultant introuvable"));
        Mission mission = missionRepository.findById(dto.getMissionId()).orElseThrow(() -> new RuntimeException("Mission introuvable"));
        Positionnement positionnement = Positionnement.builder()
                .consultant(consultant)
                .mission(mission)
                .status(dto.getStatus())
                .commentaire(dto.getCommentaire())
                .build();
        return mapper.toDto(positionnementRepository.save(positionnement));
    }
    public List<PositionnementResponseDto> findAll(){
        return positionnementRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public PositionnementResponseDto findById(Long id){
        return mapper.toDto(positionnementRepository.findById(id)).orElseThrow(() -> new RuntimeException(("Positionnement introuvable")));

    }

    public PositionnementResponseDto update(Long id, PositionnementRequestDto dto){
        Positionnement positionnement = positionnementRepository.findById(id).orElseThrow(() -> new RuntimeException("Positionnement introuvable "));
        Consultant consultant = consultantRepository.findById(dto.getConsultantId()).orElseThrow(() -> new RuntimeException("Consultant introuvable"));
        Mission mission = missionRepository.findById(dto.getMissionId()).orElseThrow(() -> new RuntimeException("Mission introuvable"));
        positionnement.setConsultant(consultant);
        positionnement.setMission(mission);
        positionnement.setStatus(dto.getStatus());
        positionnement.setCommentaire(dto.getCommentaire());

        return mapper.toDto(positionnementRepository.save(positionnement));
    }

    public void delete(Long id){
        if(!positionnementRepository.existsById(id)){
            throw new RuntimeException("Positionnement introuvable");
        }
        positionnementRepository.deleteById(id);
    }
}
