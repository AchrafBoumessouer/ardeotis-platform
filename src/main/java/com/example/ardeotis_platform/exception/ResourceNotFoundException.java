package com.example.ardeotis_platform.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, Object id) {
        super(message + " not founf with id: " + id);
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
