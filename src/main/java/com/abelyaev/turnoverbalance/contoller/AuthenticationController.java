package com.abelyaev.turnoverbalance.contoller;

import com.abelyaev.turnoverbalance.model.dto.authdto.AuthenticationRequest;
import com.abelyaev.turnoverbalance.model.dto.authdto.AuthenticationResponse;
import com.abelyaev.turnoverbalance.model.dto.authdto.RegisterRequest;
import com.abelyaev.turnoverbalance.service.authservices.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
