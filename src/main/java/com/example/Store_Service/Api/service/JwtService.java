package com.example.Store_Service.Api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "mySuperSecretJwtKey123456789"; // แนะนำให้เก็บใน environment variable

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long expirationTimeMs = 1000 * 60 * 60 * 24; // 24 ชั่วโมง

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // username
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // ✅ ดึง username จาก token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ✅ ตรวจสอบ token ว่าหมดอายุหรือไม่
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ✅ ตรวจสอบ token ว่า valid หรือไม่ (ใช้คู่กับ userDetails)
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // คุณสามารถมี method generateToken(), createToken(), ฯลฯ อยู่ในไฟล์นี้ได้เช่นกัน
}