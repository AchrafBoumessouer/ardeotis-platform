package com.example.ardeotis_platform;


import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.request.UpdatePositionnementStatusRequest;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.exception.BusinessException;
import com.example.ardeotis_platform.mapper.MissionMapper;
import com.example.ardeotis_platform.model.*;
import com.example.ardeotis_platform.repository.HistoriquePositionRepository;
import com.example.ardeotis_platform.repository.MissionRepository;
import com.example.ardeotis_platform.repository.PositionnementRepository;
import com.example.ardeotis_platform.service.MissionService;
import com.example.ardeotis_platform.service.PositionnementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MissionServiceTest {

 @Mock
    private MissionRepository missionRepository;
 @Mock
    private MissionMapper missionMapper;
 @InjectMocks
    private MissionService missionService;

 @Test
    void createMission_shouldSaveMissionAndReturnDto() {
     MissionRequestDto req = new MissionRequestDto();
     Mission mission = new Mission();
     Mission savedMission = new Mission();
     MissionResponseDto res = new MissionResponseDto();
     when(missionMapper.toEntity(req)).thenReturn(mission);
     when(missionRepository.save(mission)).thenReturn(savedMission);
     when(missionMapper.toResponseDto(savedMission)).thenReturn(res);

     MissionResponseDto resu = missionService.createMission(req);
     assertSame(res,resu);
     verify(missionMapper).toEntity(req);
     verify(missionRepository).save(mission);
     verify(missionMapper).toResponseDto(savedMission);

 }

 @Test
 void getAllActiveMissions_shouldReturnPageOfDtos() {
     Pageable page = PageRequest.of(0,10);
     Mission mission = new Mission();
     MissionResponseDto dto = new MissionResponseDto();
     Page<Mission> pages = new PageImpl<>(List.of(mission));
     when(missionRepository.findByStatus(MissionStatus.ACTIVE,page)).thenReturn(pages);;
     when(missionMapper.toResponseDto(mission)).thenReturn(dto);
     Page<MissionResponseDto> result = missionService.getAllActiveMissions(0,10);
     assertEquals(1,result.getTotalElements());
     assertSame(dto,result.getContent().get(0));
     verify(missionRepository).findByStatus(MissionStatus.ACTIVE,page);

 }

    @Test
    void getAllArchivedMissions_shouldReturnPageOfDtos() {
        Pageable page = PageRequest.of(0,10);
        Mission mission = new Mission();
        MissionResponseDto dto = new MissionResponseDto();
        Page<Mission> pages = new PageImpl<>(List.of(mission));
        when(missionRepository.findByStatus(MissionStatus.ARCHIVED,page)).thenReturn(pages);
        when(missionMapper.toResponseDto(mission)).thenReturn(dto);
        Page<MissionResponseDto> result = missionService.getArchivedMissions(0,10);
        assertEquals(1,result.getTotalElements());
        assertSame(dto,result.getContent().get(0));
        verify(missionRepository).findByStatus(MissionStatus.ARCHIVED,page);

    }
}
