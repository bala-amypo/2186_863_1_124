package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret:mySecretKeyForJWTTokenGenerationThatIsLongEnoughForHS256Algorithm}")
    private String secretKey;
    
    @Value("${jwt.expiration:86400000}")
    private long expirationMillis;
    
    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateToken(Long userId, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }
    
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
    
    public Long extractUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }
    
    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}