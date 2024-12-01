package com.stockflow.controllers;

import com.stockflow.dto.user.login.UserLoginRequestDTO;
import com.stockflow.dto.user.login.UserLoginResponseDTO;
import com.stockflow.infra.security.TokenService;
import com.stockflow.model.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> userLogin(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.login(), userLoginRequestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserLoginResponseDTO(token));
    }
}
