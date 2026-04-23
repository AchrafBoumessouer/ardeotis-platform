package com.example.ardeotis_platform.controller;

import com.example.ardeotis_platform.dto.request.ConsultantRequestDto;
import com.example.ardeotis_platform.dto.response.ConsultantResponseDto;
import com.example.ardeotis_platform.service.ConsultantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/consultants")
@RequiredArgsConstructor
public class ConsultantController {

    private final ConsultantService consultantService;

    @PostMapping
    public ResponseEntity<ConsultantResponseDto> createConsultant(@RequestBody ConsultantRequestDto request) {
        ConsultantResponseDto response = consultantService.createConsultant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ConsultantResponseDto>> getAllConsultants() {
        return ResponseEntity.ok(consultantService.getAllConsultants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultantResponseDto> getConsultantById(@PathVariable UUID id) {
        return ResponseEntity.ok(consultantService.getConsultantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultantResponseDto> updateConsultant(@PathVariable UUID id,@RequestBody ConsultantRequestDto request) {
        return ResponseEntity.ok(consultantService.updateConsultant(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultant(@PathVariable UUID id ) {
        consultantService.deleteConsultant(id);
        return ResponseEntity.noContent().build();
    }
}
