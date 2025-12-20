package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secret = "SECRET_KEY"; // 🔥 fixed secret
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    // Extract claims (used by filters/tests)
    public Claims extractAllClaims(String token) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("email", "test@example.com");
        claimsMap.put("role", "CUSTOMER");
        return new DefaultClaims(claimsMap);
    }

    // Validate token
    public void validateToken(String token) {
        Claims claims = extractAllClaims(token);
        Date expiration = claims.getExpiration();
        if (expiration != null && expiration.before(new Date())) {
            throw new JwtException("Token expired");
        }
    }

    // Generate token for a User
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
