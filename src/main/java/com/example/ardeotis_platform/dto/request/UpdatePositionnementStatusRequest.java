package com.example.ardeotis_platform.dto.request;

import com.example.ardeotis_platform.model.PositionnementStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdatePositionnementStatusRequest {
    @NotNull(message = "status est obligatoire")
    private PositionnementStatus status;
}
