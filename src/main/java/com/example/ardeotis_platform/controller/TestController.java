package com.example.ardeotis_platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping
    public String health() {
        return "Backend is running";
    }
}