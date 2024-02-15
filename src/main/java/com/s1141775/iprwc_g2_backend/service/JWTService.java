package com.s1141775.iprwc_g2_backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;


@Service
public class JWTService {
    @Value("${jwt.secret}")
    String jwtSecretKey;

    @Value("${jwt.expiration-time}")
    Long jwtExpirationMs;


    public String generateFromUsername(){
        var jwt = Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    private Key getSigningKey() {
        byte[] keyBytes = jwtSecretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
