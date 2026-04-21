package com.example.ardeotis_platform.dto.response;

import com.example.ardeotis_platform.model.ConsultantStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultantResponseDto {
    private UUID id;
    private String firstName;
    private Boolean available;
    private String email;
    private ConsultantStatus status;
    private String lastName;
    private List<String> role;


}
