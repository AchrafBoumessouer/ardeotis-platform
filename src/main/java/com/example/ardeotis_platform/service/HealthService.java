package com.example.ardeotis_platform.service;

import org.springframework.stereotype.Service;

@Service
public class HealthService {
    public String getStatus(){
        return "ok";
    }
}
