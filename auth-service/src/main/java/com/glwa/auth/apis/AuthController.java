package com.glwa.auth.apis;

import com.glwa.auth.dto.RegisterDto;
import com.glwa.auth.dto.TokenDto;
import com.glwa.auth.request.LoginRequest;
import com.glwa.auth.request.RegisterRequest;
import com.glwa.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/create")
    public ResponseEntity<RegisterDto> create(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
