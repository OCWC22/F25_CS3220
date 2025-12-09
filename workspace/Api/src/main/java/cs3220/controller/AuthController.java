package cs3220.controller;

import cs3220.dto.AuthRequestDto;
import cs3220.dto.AuthResponseDto;
import cs3220.dto.RegisterRequestDto;
import cs3220.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody @Valid RegisterRequestDto request) {
        return new AuthResponseDto(authService.register(request));
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody @Valid AuthRequestDto request) {
        return new AuthResponseDto(authService.login(request));
    }
}
