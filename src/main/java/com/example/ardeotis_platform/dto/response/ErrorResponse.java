package com.example.ardeotis_platform.dto.response;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;
}
