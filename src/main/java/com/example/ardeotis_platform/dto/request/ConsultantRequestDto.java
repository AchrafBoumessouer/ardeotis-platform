package com.example.ardeotis_platform.dto.request;

import com.example.ardeotis_platform.model.ConsultantStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ConsultantRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotEmpty
    private List<String> skills;

    @NotNull
    private ConsultantStatus status;

    @NotNull
    private Boolean available;


}
