package com.stockflow.controllers;

import com.stockflow.dto.loginDtos.LoginRequestDTO;
import com.stockflow.dto.loginDtos.LoginResponseDTO;
import com.stockflow.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        var user = userRepository.findByLogin(loginRequestDTO.login());
        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequestDTO, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Login or password is invalid!");
        }

        var now = Instant.now();
        var expiresIn = 3600L;

        var claims = JwtClaimsSet.builder()
                .issuer("stockflow") // Who is generating it
                .subject(user.get().getId().toString())
                .issuedAt(now) // When token was generated
                .expiresAt(now.plusSeconds(expiresIn)) // When token will expire (1h after generation)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }
}
