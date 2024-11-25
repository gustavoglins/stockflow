package com.stockflow.controllers;

import com.stockflow.dto.userDtos.UserRequestDTO;
import com.stockflow.dto.userDtos.UserResponseDTO;
import com.stockflow.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(service.create(userRequestDTO));
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(service.update(userRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
