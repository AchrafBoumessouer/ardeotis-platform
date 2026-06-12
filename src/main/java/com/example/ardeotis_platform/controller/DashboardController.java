package com.example.ardeotis_platform.controller;

import com.example.ardeotis_platform.dto.request.UpdatePositionnementStatusRequest;
import com.example.ardeotis_platform.dto.response.DashboardStatsResponseDto;
import com.example.ardeotis_platform.dto.response.HistoriquePositionnementResponseDto;
import com.example.ardeotis_platform.model.Positionnement;
import com.example.ardeotis_platform.service.DashboardService;
import com.example.ardeotis_platform.service.PositionnementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponseDto> getStats() {
        return ResponseEntity.ok(dashboardService.getStats());
    }


}
