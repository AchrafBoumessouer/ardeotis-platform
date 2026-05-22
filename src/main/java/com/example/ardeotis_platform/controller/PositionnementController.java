package com.example.ardeotis_platform.controller;

import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.request.PositionnementRequestDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.dto.response.PositionnementResponseDto;
import com.example.ardeotis_platform.service.MissionService;
import com.example.ardeotis_platform.service.PositionnementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/positionnements")
@RequiredArgsConstructor
public class PositionnementController {

    private final PositionnementService positionnementService;

    @PostMapping
    public ResponseEntity<PositionnementResponseDto> create(@RequestBody PositionnementRequestDto request) {
        PositionnementResponseDto response = positionnementService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PositionnementResponseDto>> findAll() {
        return ResponseEntity.ok(positionnementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionnementResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(positionnementService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionnementResponseDto> update(@PathVariable Long id,@RequestBody PositionnementRequestDto request) {
        return ResponseEntity.ok(positionnementService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id ) {
        positionnementService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
