package com.abelyaev.turnoverbalance.security;

import com.abelyaev.turnoverbalance.exeption.AuthException;
import com.abelyaev.turnoverbalance.exeption.UnauthorizedException;
import com.abelyaev.turnoverbalance.model.dto.authdto.VerificationResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Date;

public class JwtHandler {
    private final String secret;

    public JwtHandler(String secret) {
        this.secret = secret;
    }

    public Mono<VerificationResult> check(String accessToken) {
        return Mono.just(verify(accessToken))
                .onErrorResume(e -> Mono.error(new UnauthorizedException(e.getMessage())));
    }

    private VerificationResult verify(String token) {
        Claims claims = getClaimsFromToke(token);
        final Date expirationDate = claims.getExpiration();

        if (expirationDate.before(new Date())) {
            throw new AuthException("Token expired", "TOKEN_EXPIRED");
        }
        return new VerificationResult(claims, token);
    }

    private Claims getClaimsFromToke(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
