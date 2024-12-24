package com.stockflow.api.controllers;

import com.stockflow.api.requests.user.UserUpdateRequestDTO;
import com.stockflow.api.responses.user.UserResponseDTO;
import com.stockflow.domain.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(
            summary = "Update user information",
            description = "Update the details of an existing user with the provided information",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request body, missing fields, or invalid data format"),
            @ApiResponse(responseCode = "404", description = "User not found with the provided information"),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity, validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @PutMapping
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) {
        logger.info("Received request to update user information");
        return ResponseEntity.ok(service.update(userUpdateRequestDTO));
    }

    @Operation(
            summary = "Find user by ID",
            description = "Retrieve the details of a user with the specified ID",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid user ID format"),
            @ApiResponse(responseCode = "404", description = "User not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        logger.info("Received request to find user by ID");
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Get all users",
            description = "Retrieve a list of all users",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "List of users retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        logger.info("Received request to find all users");
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Delete user by ID",
            description = "Delete a user based on the provided ID",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID format"),
            @ApiResponse(responseCode = "404", description = "User not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error, something went wrong"),
            @ApiResponse(responseCode = "503", description = "Service unavailable, try again later")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Received request to delete user by ID");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
