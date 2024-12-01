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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.login(), userLoginRequestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserLoginResponseDTO(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDTO> signup(@RequestBody @Valid UserSignupRequestDTO userSignupRequestDTO){
        if(userRepository.findByLogin(userSignupRequestDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userSignupRequestDTO.password());
        User newUser = new User(userSignupRequestDTO);

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
