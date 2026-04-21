package com.example.ardeotis_platform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String token;
    private String type;
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String role;


}
