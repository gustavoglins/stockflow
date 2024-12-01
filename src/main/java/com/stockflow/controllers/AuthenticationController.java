package com.stockflow.controllers;

import com.stockflow.dto.user.UserLoginRequestDTO;
import com.stockflow.dto.user.UserLoginResponseDTO;
import com.stockflow.dto.user.UserSignupRequestDTO;
import com.stockflow.dto.user.UserSignupResponseDTO;
import com.stockflow.infra.security.TokenService;
import com.stockflow.model.user.User;
import com.stockflow.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> userLogin(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.login(), userLoginRequestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserLoginResponseDTO(token));
    }
}
