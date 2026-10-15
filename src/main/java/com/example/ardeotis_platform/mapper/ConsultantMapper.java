package com.example.ardeotis_platform.mapper;

import com.example.ardeotis_platform.dto.request.ConsultantRequestDto;
import com.example.ardeotis_platform.dto.response.ConsultantResponseDto;
import com.example.ardeotis_platform.model.Consultant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultantMapper {
    ConsultantResponseDto toResponseDto(Consultant consultant);
    List<ConsultantResponseDto> toResponseDtoList(List<Consultant> consultants);
    Consultant toEntity(ConsultantRequestDto requestDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ConsultantRequestDto requestDto,@org.mapstruct.MappingTarget Consultant consultant);
}
