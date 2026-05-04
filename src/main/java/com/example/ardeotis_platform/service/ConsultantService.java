package com.example.ardeotis_platform.service;

import com.example.ardeotis_platform.dto.request.ConsultantRequestDto;
import com.example.ardeotis_platform.dto.response.ConsultantResponseDto;
import com.example.ardeotis_platform.mapper.ConsultantMapper;
import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultantService {
    private final ConsultantRepository consultantRepository;
    private final ConsultantMapper consultantMapper;

    public ConsultantResponseDto createConsultant(ConsultantRequestDto requestDto) {
        Consultant consultant = consultantMapper.toEntity( requestDto );
        Consultant savedConsultant = consultantRepository.save(consultant);
        return consultantMapper.toResponseDto( savedConsultant );
    }

    public List<ConsultantResponseDto> getAllConsultants(){
        return consultantRepository.findAll()
                                   .stream()
                                   .map(consultantMapper::toResponseDto)
                                   .toList();
    }

    public ConsultantResponseDto getConsultantById(UUID id) {
        Consultant consultant = consultantRepository.findById(id).orElseThrow(() -> new RuntimeException("Consultant not found"));
        return consultantMapper.toResponseDto(consultant);
    }

    public ConsultantResponseDto updateConsultant(UUID id, ConsultantRequestDto request) {
        Consultant consultant = consultantRepository.findById(id).orElseThrow(()-> new RuntimeException("Consultant not found"));
        consultantMapper.updateEntityFromDto(request,consultant);
        Consultant updatedConsultant = consultantRepository.save(consultant);
        return consultantMapper.toResponseDto(updatedConsultant);
    }

    public void deleteConsultant(UUID id) {
        Consultant consultant = consultantRepository.findById(id).orElseThrow(() -> new RuntimeException("Consultant not found "));
        consultantRepository.deleteById(id);
    }
}
