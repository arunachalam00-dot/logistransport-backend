package com.logistransport.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "LogiTransportSecretKeyForJwtAuthentication2026Secure".getBytes()
    );

    private final long expirationTime = 1000 * 60 * 60;

    // Generate JWT token
    public String generateToken(String email, String role) {

        Date now = new Date();

        Date expiration = new Date(
                now.getTime() + expirationTime
        );

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    // Extract email
    public String extractEmail(String token) {

        return getClaims(token).getSubject();
    }

    // Extract role
    public String extractRole(String token) {

        return getClaims(token)
                .get("role", String.class);
    }

    // Read JWT claims
    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}