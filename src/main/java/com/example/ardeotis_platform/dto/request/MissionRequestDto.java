package com.example.ardeotis_platform.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class MissionRequestDto {


    @NotBlank
    private String title;

    @NotBlank
    private String client;

    @NotEmpty
    private List<String> skills;
    @NotBlank
    private String status;


    private LocalDate startDate;
    private LocalDate endDate;


}
