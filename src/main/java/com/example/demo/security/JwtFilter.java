package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "your-secret-key"; // replace with your actual secret

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new JwtException("Invalid token", e);
        }
    }

    public void validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);

            if (claims.getExpiration() == null || claims.getExpiration().before(new Date())) {
                throw new JwtException("Token expired");
            }
        } catch (JwtException e) {
            throw new JwtException("Token validation failed: " + e.getMessage(), e);
        }
    }
}
