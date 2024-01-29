package com.abelyaev.turnoverbalance.model.dto.authdto;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VerificationResult {
    private Claims claims;
    private String token;
}
