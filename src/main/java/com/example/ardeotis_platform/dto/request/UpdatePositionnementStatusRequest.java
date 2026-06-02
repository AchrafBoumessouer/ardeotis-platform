package com.example.ardeotis_platform.dto.request;

import com.example.ardeotis_platform.model.PositionnementStatus;
import lombok.Getter;

@Getter
public class UpdatePositionnementStatusRequest {
    private PositionnementStatus status;
}
