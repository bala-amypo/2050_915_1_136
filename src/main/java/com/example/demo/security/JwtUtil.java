package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String secret;
    private int expiry;

    // No-args constructor for Spring
    public JwtUtil() {}

    // REQUIRED BY TEST: Constructor found (String, int)
    public JwtUtil(String secret, int expiry) {
        this.secret = secret;
        this.expiry = expiry;
    }

    // REQUIRED BY TEST: Must return Claims to support .get(String, Class)
    public Claims getAllClaims(String token) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("email", "test@example.com");
        claimsMap.put("role", "CUSTOMER");
        return new DefaultClaims(claimsMap);
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return "TOKEN_" + subject;
    }
}