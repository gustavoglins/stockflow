package com.stockflow.api.controllers;

import com.stockflow.api.requests.user.UserUpdateRequestDTO;
import com.stockflow.api.responses.user.UserDetailsResponseDTO;
import com.stockflow.domain.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<UserDetailsResponseDTO> update(@RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) {
        return ResponseEntity.ok(userService.update(userUpdateRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
