package com.abelyaev.balance.contoller;

import com.abelyaev.balance.mapper.UserMapper;
import com.abelyaev.balance.model.dto.authdto.AuthRequest;
import com.abelyaev.balance.model.dto.authdto.AuthResponse;
import com.abelyaev.balance.model.dto.authdto.UserDto;
import com.abelyaev.balance.model.entity.UserEntity;
import com.abelyaev.balance.service.authservices.SecurityService;
import com.abelyaev.balance.service.authservices.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final SecurityService securityService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public Mono<UserDto> register(@RequestBody UserDto dto) {
        UserEntity entity = userMapper.map(dto);
        return userService.registerUser(entity)
                .map(userMapper::map);
    }

    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody AuthRequest rq) {
        return securityService.authenticate(rq.getUsername(), rq.getPassword())
                .flatMap(tokenDetails -> Mono.just(
                        AuthResponse.builder()
                                .userId(tokenDetails.getUserId())
                                .token(tokenDetails.getToken())
                                .issuedAt(tokenDetails.getIssuedAt())
                                .expiresAt(tokenDetails.getExpiresAt())
                                .build()
                ));
    }
}
