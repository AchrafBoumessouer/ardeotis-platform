package com.example.ardeotis_platform.controller;

import com.example.ardeotis_platform.dto.request.MissionRequestDto;
import com.example.ardeotis_platform.dto.request.UpdatePositionnementStatusRequest;
import com.example.ardeotis_platform.dto.response.HistoriquePositionnementResponseDto;
import com.example.ardeotis_platform.dto.response.MissionResponseDto;
import com.example.ardeotis_platform.model.Positionnement;
import com.example.ardeotis_platform.service.PositionnementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/positionnements")
@RequiredArgsConstructor
public class PositionnementController {

    private final PositionnementService positionnementService;

    @GetMapping()
    public ResponseEntity<List<Positionnement>> getAllPosition() {
        return ResponseEntity.ok(positionnementService.getHistorique());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Positionnement> updateStatus(@PathVariable Long id, @RequestBody UpdatePositionnementStatusRequest request) {
        return ResponseEntity.ok(positionnementService.updateStatus(id,request));
    }

    @GetMapping("/{id}/historique")
    public ResponseEntity<List<HistoriquePositionnementResponseDto>> getHistorique(@PathVariable Long id) {
        return ResponseEntity.ok(positionnementService.getHistorique(id));
    }


}
