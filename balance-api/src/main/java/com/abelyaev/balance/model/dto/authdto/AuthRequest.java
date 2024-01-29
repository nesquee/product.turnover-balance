package com.abelyaev.balance.model.dto.authdto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
