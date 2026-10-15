package com.example.ardeotis_platform;


import com.example.ardeotis_platform.dto.request.ConsultantRequestDto;
import com.example.ardeotis_platform.dto.request.UpdatePositionnementStatusRequest;
import com.example.ardeotis_platform.dto.response.ConsultantResponseDto;
import com.example.ardeotis_platform.exception.BusinessException;
import com.example.ardeotis_platform.mapper.ConsultantMapper;
import com.example.ardeotis_platform.model.Consultant;
import com.example.ardeotis_platform.model.HistoriquePositionnement;
import com.example.ardeotis_platform.model.Positionnement;
import com.example.ardeotis_platform.model.PositionnementStatus;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import com.example.ardeotis_platform.repository.HistoriquePositionRepository;
import com.example.ardeotis_platform.repository.PositionnementRepository;
import com.example.ardeotis_platform.service.ConsultantService;
import com.example.ardeotis_platform.service.PositionnementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PositionnementServiceTest {

 @Mock
    private PositionnementRepository positionnementRepository;
 @Mock
    private HistoriquePositionRepository historiquePositionRepository;
 @InjectMocks
    private PositionnementService positionnementService;

 @Test
    void updateStatus_shouldUpdateStatusAndSaveHistory_whenTransitionAllowed() {
     Positionnement pos = new Positionnement();
     pos.setId(1L);
     pos.setStatus(PositionnementStatus.INTERET_EXPRIME);
     UpdatePositionnementStatusRequest req = new UpdatePositionnementStatusRequest(PositionnementStatus.PRESENTE_AU_CLIENT);

     when(positionnementRepository.findById(1L)).thenReturn(Optional.of(pos));
     when(positionnementRepository.save(any(Positionnement.class))).thenAnswer(inv -> inv.getArgument(0));

     Positionnement resu = positionnementService.updateStatus(1L,req);
     assertEquals(PositionnementStatus.PRESENTE_AU_CLIENT,resu.getStatus());
     assertNotNull(resu.getLastStatusUpdateAt());

     verify(positionnementRepository).save(pos);
     verify(historiquePositionRepository).save(any(HistoriquePositionnement.class));
 }

 @Test
 void updateStatus_shouldThrowException_whenTransitionNotAllowed() {
     Positionnement pos = new Positionnement();
     pos.setId(1L);
     pos.setStatus(PositionnementStatus.VALIDE);
     UpdatePositionnementStatusRequest req = new UpdatePositionnementStatusRequest(PositionnementStatus.PRESENTE_AU_CLIENT);
     when(positionnementRepository.findById(1L)).thenReturn(Optional.of(pos));
     assertThrows(BusinessException.class,() -> positionnementService.updateStatus(1L,req));
     verify(positionnementRepository, never()).save(any());
     verify(historiquePositionRepository,never()).save(any());

 }

 @Test
    void updateStatus_shouldThrowException_whenPositionnementNotFound() {
     when(positionnementRepository.findById(1L)).thenReturn(Optional.empty());
     UpdatePositionnementStatusRequest req = new UpdatePositionnementStatusRequest(PositionnementStatus.PRESENTE_AU_CLIENT);
     assertThrows(BusinessException.class, () -> positionnementService.updateStatus(1L,req));
 }
}
