package com.example.ardeotis_platform.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(String subject, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                   .subject(subject)
                   .claim("role", role)
                   .issuedAt(now)
                   .expiration(expiryDate)
                   .signWith(getSigningKey())
                   .compact();
    }

    public String extractUsename(String token){
        return extractAllClaims(token).getSubject();
    }
    public boolean isTokenValid(String token, String expectedEmail) {
        String email = extractUsename( token);
        return email.equals(expectedEmail) && !isTokenExpired(token);
    }

    private boolean isTokenExpired( String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
