package com.stockflow.api.controllers;

import com.stockflow.api.requests.UserLoginRequestDTO;
import com.stockflow.api.requests.UserSignupRequestDTO;
import com.stockflow.api.responses.UserAuthResponseDTO;
import com.stockflow.domain.services.impl.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup/user")
    public ResponseEntity<UserAuthResponseDTO> userSignup(@RequestBody @Valid UserSignupRequestDTO userSignupRequestDTO) {
        UserAuthResponseDTO response = authenticationService.userSignup(userSignupRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/user")
    public ResponseEntity<UserAuthResponseDTO> userLogin(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        UserAuthResponseDTO response = authenticationService.userLogin(userLoginRequestDTO);
        return ResponseEntity.ok(response);
    }
}
