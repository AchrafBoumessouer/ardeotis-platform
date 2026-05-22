package com.example.ardeotis_platform.mapper;

import com.example.ardeotis_platform.dto.response.PositionnementResponseDto;
import com.example.ardeotis_platform.model.Positionnement;

public class PositionnementMapper {

    public PositionnementResponseDto toDto(Positionnement p){
        return PositionnementResponseDto.builder()
                .id(p.getId())
                .consultantId(p.getConsultant().getID())
                .missionId(p.getMission().getID())
                .status(p.getStatus())
                .commentaire(p.getCommentaire())
                .createdAt(p.getCreatedAt()).build();
    }
}
