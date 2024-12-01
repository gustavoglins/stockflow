package com.stockflow.controllers;

import com.stockflow.dto.user.UserRequestDTO;
import com.stockflow.dto.user.UserResponseDTO;
import com.stockflow.exceptions.EntityValidationException;
import com.stockflow.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@ModelAttribute @Valid UserRequestDTO userRequestDTO, BindingResult result) {
        try {
            service.create(userRequestDTO);
        } catch (EntityValidationException exception) {
            if (exception.getMessage().contains("Name")) {
                result.rejectValue("name", null, exception.getMessage());
            } else if (exception.getMessage().contains("Email")) {
                result.rejectValue("login", null, exception.getMessage());
            } else if (exception.getMessage().contains("Password")) {
                result.rejectValue("password", null, exception.getMessage());
            }
        }

        if (result.hasErrors()) {
            return "signup-personal";
        }

        return "redirect:/dashboard";
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(service.update(userRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
