package com.example.ardeotis_platform.dto.response;

import com.example.ardeotis_platform.model.ConsultantStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MissionResponseDto {
    private UUID ID;
    private String title;
    private Boolean client;
    private Set<String> skills;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

}
