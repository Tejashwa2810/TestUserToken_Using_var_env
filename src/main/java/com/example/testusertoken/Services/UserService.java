package com.example.testusertoken.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private final JwtParser jwtParser;

    public UserService(JwtParser jwtParser) {
        this.jwtParser = jwtParser;
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();

            Long expiry = claims.get("exp", Long.class);
            Long now = System.currentTimeMillis();

            return now < expiry;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.get("email", String.class);
    }
}
