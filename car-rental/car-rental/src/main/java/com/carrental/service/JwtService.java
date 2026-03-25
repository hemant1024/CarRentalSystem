package com.carrental.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final SecretKey SECRET_KEY =
            Keys.secretKeyFor(
                    SignatureAlgorithm.HS256
            );

    public String generateToken(
            Long userId,
            String role) {

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {

            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            System.out.println(
                    "TOKEN ERROR: "
                            + e.getMessage()
            );

            return false;
        }
    }

    public Long extractUserId(String token) {

        return Long.parseLong(

                Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()

        );
    }

    public String extractRole(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}