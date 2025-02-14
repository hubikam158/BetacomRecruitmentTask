package com.betacom.rekrutacja.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    private final String secret;
    private static final int HOURS = 2;

    public TokenService(@Value("${secret.key}") String secret) {
        this.secret = secret;
    }

    public String generateToken(String login) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(login)
                .withIssuedAt(now)
                .withExpiresAt(Date.from(now.plus(HOURS, ChronoUnit.HOURS)))
                .sign(Algorithm.HMAC256(secret));
    }

    public String getUsernameFromToken(String token) {
        return JWT.decode(token).getSubject();
    }

    public String cutTokenPrefix(String token) {
        return token.startsWith("Bearer") ? token.substring(7) : token;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token).equals(userDetails.getUsername());
    }
}
