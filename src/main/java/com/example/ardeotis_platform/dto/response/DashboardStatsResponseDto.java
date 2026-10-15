package com.example.ardeotis_platform.dto.response;

import java.util.Map;

public record DashboardStatsResponseDto(
        Map<String, Long> consultants,
        Map<String, Long> missions,
        Map<String, Long> positionnements
) {
}
