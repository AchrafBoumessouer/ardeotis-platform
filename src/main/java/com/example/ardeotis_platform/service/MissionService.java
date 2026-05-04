package com.example.ardeotis_platform.service;


import com.example.ardeotis_platform.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;


}
