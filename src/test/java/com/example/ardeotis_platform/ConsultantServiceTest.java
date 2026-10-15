package com.example.ardeotis_platform;


import com.example.ardeotis_platform.dto.request.ConsultantRequestDto;
import com.example.ardeotis_platform.dto.response.ConsultantResponseDto;
import com.example.ardeotis_platform.mapper.ConsultantMapper;
import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import com.example.ardeotis_platform.service.ConsultantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultantServiceTest {

 @Mock
    private ConsultantRepository consultantRepository;
 @Mock
    private ConsultantMapper consultantMapper;
 @InjectMocks
    private ConsultantService consultantService;

 @Test
    void shouldCreateConsultant() {
     ConsultantRequestDto req = new ConsultantRequestDto();
     Consultant cons = new Consultant();
     Consultant saved = new Consultant();
     ConsultantResponseDto resp = new ConsultantResponseDto();
     when(consultantMapper.toEntity(req)).thenReturn(cons);
     when(consultantRepository.save(cons)).thenReturn(saved);
     when(consultantMapper.toResponseDto(saved)).thenReturn(resp);

     ConsultantResponseDto res = consultantService.createConsultant(req);
     assertThat(res).isEqualTo(resp);
 }

 @Test
 void shouldThrowExceptionWhenConsultantNotFound() {
     UUID id = UUID.randomUUID();
     when(consultantRepository.findById(id));
     assertThatThrownBy(() -> consultantService.getConsultantById(id)).isInstanceOf(RuntimeException.class).hasMessageContaining("Consultant");
 }

 @Test
    void getAllConsultant_shouldReturnPage() {
     Consultant cons = new Consultant();
     ConsultantResponseDto respp = new ConsultantResponseDto();

     Page<Consultant> page = new PageImpl<>(List.of(cons));
     when(consultantRepository.findAll(any(Pageable.class))).thenReturn(page);
     when(consultantMapper.toResponseDto(cons)).thenReturn(respp);
     Page<ConsultantResponseDto> res = consultantService.getAllConsultants(0,10);
     assertThat(res.getContent()).hasSize(1);
     assertThat(res.getContent().get(0)).isEqualTo(respp);
     verify(consultantRepository).findAll(any(Pageable.class));
 }
}
