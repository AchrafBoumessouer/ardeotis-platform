package com.example.ardeotis_platform.controller;

import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping
    public ResponseEntity<MissionResponseDto> createConsultant(@RequestBody MissionRequestDto request) {
        MissionResponseDto response = missionService.createMission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MissionResponseDto>> getAllConsultants() {
        return ResponseEntity.ok(missionService.getAllMissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionResponseDto> getConsultantById(@PathVariable UUID id) {
        return ResponseEntity.ok(missionService.getMissionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionResponseDto> updateConsultant(@PathVariable UUID id,@RequestBody MissionRequestDto request) {
        return ResponseEntity.ok(missionService.updateMission(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultant(@PathVariable UUID id ) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }
}
