package com.abelyaev.turnoverbalance.model.dto.authdto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
