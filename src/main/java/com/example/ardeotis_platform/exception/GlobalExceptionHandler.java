package com.example.ardeotis_platform.exception;

import com.example.ardeotis_platform.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonError(HttpMessageNotReadableException ex) {
        return  ResponseEntity.badRequest().body(Map.of(
                "status", 400,
                "error", "Bad Request",
                "message", "Invalid request body"
        ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
       List<String> errors = ex.getConstraintViolations()
                               .stream()
                               .map(error -> error.getPropertyPath() + ": " + error.getMessage()).toList();

       ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),"Constraint violation",errors, LocalDateTime.now());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),List.of(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
