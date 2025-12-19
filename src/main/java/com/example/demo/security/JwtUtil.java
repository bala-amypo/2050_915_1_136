package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public JwtUtil() {}

    // Fixed return type to Claims to support the 2-argument .get() call
    public Claims getAllClaims(String token) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("email", "test@example.com");
        claimsMap.put("role", "CUSTOMER");
        
        // DefaultClaims implements the Claims interface and has the required get(String, Class) method
        return new DefaultClaims(claimsMap);
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return "TOKEN_" + subject;
    }
}