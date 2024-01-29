package com.abelyaev.balance.service.authservices;

import com.abelyaev.balance.exeption.AuthException;
import com.abelyaev.balance.model.entity.UserEntity;
import com.abelyaev.balance.security.TokenDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SecurityService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expirationInSeconds;
    @Value("${jwt.issuer}")
    private String issuer;

    public Mono<TokenDetails> authenticate(String username, String password) {
        return userService.getUserByUsername(username)
                .flatMap(user -> {
                    if (!user.isEnabled()) {
                        return Mono.error(new AuthException("Account disabled", "USER_ACCOUNT_DISABLED"));
                    }
                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.error(new AuthException("Invalid password", "USER_ACCOUNT_INVALID_PASSWORD"));
                    }

                    return Mono.just(generateToken(user).toBuilder()
                            .userId(user.getId())
                            .build());
                })
                .switchIfEmpty(Mono.error(new AuthException("Invalid username", "USER_ACCOUNT_INVALID_USERNAME")));

    }

    public TokenDetails generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>() {{
            put("role", user.getRole());
            put("username", user.getUsername());
        }};
        return generateToken(claims, user.getId().toString());
    }

    public TokenDetails generateToken(Map<String, Object> extraClaims, String subject) {
        return generateToken(new Date(System.currentTimeMillis() + expirationInSeconds), extraClaims, subject);
    }

    public TokenDetails generateToken(Date expirationDate, Map<String, Object> extraClaims, String subject) {
        Date createdate = new Date();
        String token =  Jwts.builder()
                .setClaims(extraClaims)
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(createdate)
                .setId(UUID.randomUUID().toString())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes()))
                .compact();
        return TokenDetails.builder()
                .token(token)
                .issuedAt(createdate)
                .expiresAt(expirationDate)
                .build();
    }
}
