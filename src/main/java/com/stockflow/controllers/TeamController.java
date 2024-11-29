package com.stockflow.controllers;

import com.stockflow.dto.teamDtos.TeamRequestDTO;
import com.stockflow.dto.teamDtos.TeamResponseDTO;
import com.stockflow.exceptions.EntityValidationException;
import com.stockflow.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@ModelAttribute @Valid TeamRequestDTO teamRequestDTO, BindingResult result, Model model) {
        try {
            service.create(teamRequestDTO);
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
            model.addAttribute("teamRequestDTO", teamRequestDTO);  // Passando o objeto ao modelo
            return "signup-team";
        }

        return "redirect:/dashboard";
    }


    @PutMapping
    public ResponseEntity<TeamResponseDTO> update(@ModelAttribute @Valid TeamRequestDTO teamRequestDTO) {
        return ResponseEntity.ok(service.update(teamRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
