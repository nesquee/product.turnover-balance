package com.abelyaev.turnoverbalance.security;

import com.abelyaev.turnoverbalance.model.dto.authdto.VerificationResult;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.List;

public class UserAuthenticationBearer {

    public static Mono<Authentication> create(VerificationResult result) {
        Claims claims = result.getClaims();
        String subject = claims.getSubject();
        String role = claims.get("role", String.class);
        String username = claims.get("username", String.class);
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
        CustomPrincipal principal = new CustomPrincipal(Long.parseLong(subject), username);
        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(principal, null, authorities));
    }
}
