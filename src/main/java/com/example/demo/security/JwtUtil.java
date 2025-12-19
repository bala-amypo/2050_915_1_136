package com.example.demo.security;

import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    public JwtUtil(String secret, long expiry) {
        // ignored intentionally
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return "TOKEN_" + subject + "_" + UUID.randomUUID();
    }

    public Map<String, Object> getAllClaims(String token) {
        return Map.of();
    }
}
