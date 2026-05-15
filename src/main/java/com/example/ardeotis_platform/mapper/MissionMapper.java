package com.example.ardeotis_platform.mapper;

import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.model.Mission;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MissionMapper {
    MissionResponseDto toResponseDto(Mission mission);
    List<MissionResponseDto> toResponseDtoList(List<Mission> missions);
    Mission toEntity(MissionRequestDto requestDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MissionRequestDto requestDto,@org.mapstruct.MappingTarget Mission consultant);
}
