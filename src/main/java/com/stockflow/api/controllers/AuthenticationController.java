package com.stockflow.api.controllers;

import com.stockflow.api.requests.user.UserLoginRequestDTO;
import com.stockflow.api.requests.user.UserSignupRequestDTO;
import com.stockflow.api.responses.user.UserAuthResponseDTO;
import com.stockflow.domain.services.impl.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Endpoints related to user authentication")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(
            summary = "Signup a new user",
            description = "Signup a new user with the provided information",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "User created and signed up successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserAuthResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request body, missing fields, or invalid data format"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request, invalid credentials"),
            @ApiResponse(responseCode = "409", description = "User already exists with the provided email or username"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity, validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @PostMapping("/signup/user")
    public ResponseEntity<UserAuthResponseDTO> userSignup(@RequestBody @Valid UserSignupRequestDTO userSignupRequestDTO) {
        logger.info("Received request to signup a new user");
        UserAuthResponseDTO response = authenticationService.userSignup(userSignupRequestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Login a user",
            description = "Login a user with the provided information",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User logged in successfully, token issued",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserAuthResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request body, missing fields, or invalid data format"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials, incorrect email or password"),
            @ApiResponse(responseCode = "404", description = "User not found with the provided credentials"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity, validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @PostMapping("/login/user")
    public ResponseEntity<UserAuthResponseDTO> userLogin(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        logger.info("Received request to login an user");
        UserAuthResponseDTO response = authenticationService.userLogin(userLoginRequestDTO);
        return ResponseEntity.ok(response);
    }
}
