package com.example.ardeotis_platform;


import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.response.MatchingResultDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.mapper.MissionMapper;
import com.example.ardeotis_platform.model.*;
import com.example.ardeotis_platform.repository.ConsultantRepository;
import com.example.ardeotis_platform.repository.MissionRepository;
import com.example.ardeotis_platform.repository.PositionnementRepository;
import com.example.ardeotis_platform.service.MatchingService;
import com.example.ardeotis_platform.service.MissionService;
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
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatchingServiceTest {

 @Mock
    private ConsultantRepository consultantRepository;
 @Mock
    private MissionRepository missionRepository;

 @Mock
    private PositionnementRepository positionnementRepository;
 @InjectMocks
    private MatchingService matchingService;

 @Test
    void matchConsultantsToMission_shouldReturnSortedResults() {
     UUID missionId = UUID.randomUUID();
     Mission mission = new Mission();
     mission.setID(missionId);
     mission.setSkills(Set.of("java","spring"));
     Consultant c1 = new Consultant();
     c1.setID(UUID.randomUUID());
     c1.setFirstName("test");
     c1.setStatus(ConsultantStatus.AVAILABLE);
     c1.setSkills(Set.of("java","spring","react"));

     Consultant c2 = new Consultant();
     c2.setID(UUID.randomUUID());
     c2.setFirstName("test1");
     c2.setStatus(ConsultantStatus.AVAILABLE);
     c2.setSkills(Set.of("java","spring","react"));

     Positionnement p1 = Positionnement.builder().id(1L)
                                                 .mission(mission)
                                                 .consultant(c1)
                                                 .status(PositionnementStatus.INTERET_EXPRIME)
                                                 .build();

     Positionnement p2 = Positionnement.builder().id(2L)
             .mission(mission)
             .consultant(c2)
             .status(PositionnementStatus.INTERET_EXPRIME)
             .build();

     when(missionRepository.findById(missionId)).thenReturn(Optional.of(mission));
     when(consultantRepository.findByStatus(ConsultantStatus.AVAILABLE)).thenReturn(List.of(c2,c1));

     when(positionnementRepository.findByMissionIDAndConsultantID(missionId,c1.getID())).thenReturn(Optional.of(p1));
     when(positionnementRepository.findByMissionIDAndConsultantID(missionId,c2.getID())).thenReturn(Optional.of(p2));

     List<MatchingResultDto> res = matchingService.matchConsultantsToMission(missionId);
     assertEquals(2,res.size());
     assertEquals(c1.getID(),res.get(1).getConsultantId());
     assertEquals(c2.getID(),res.get(0).getConsultantId());

     assertEquals(70,res.get(0).getMatchScore());
     assertEquals(70,res.get(1).getMatchScore());



 }

}
