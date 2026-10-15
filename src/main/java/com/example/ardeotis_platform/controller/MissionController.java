package com.example.ardeotis_platform.controller;

import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<MissionResponseDto> createMission(@RequestBody MissionRequestDto request) {
        MissionResponseDto response = missionService.createMission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<MissionResponseDto>> getAllMissions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(missionService.getAllActiveMissions(page,size));
    }
    @GetMapping("/archived")
    public ResponseEntity<Page<MissionResponseDto>> getArchivedMissions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(missionService.getArchivedMissions(page,size));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MissionResponseDto> getMissionById(@PathVariable UUID id) {
        return ResponseEntity.ok(missionService.getMissionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionResponseDto> updateMission(@PathVariable UUID id,@RequestBody MissionRequestDto request) {
        return ResponseEntity.ok(missionService.updateMission(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable UUID id ) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Void> archiveMission(@PathVariable UUID id ) {
        missionService.archiveMission(id);
        return ResponseEntity.noContent().build();
    }
}
