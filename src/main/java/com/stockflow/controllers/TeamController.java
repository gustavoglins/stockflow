package com.stockflow.controllers;

import com.stockflow.dto.teamDtos.TeamRequestDTO;
import com.stockflow.dto.teamDtos.TeamResponseDTO;
import com.stockflow.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TeamResponseDTO> create(@RequestBody @Valid TeamRequestDTO teamRequestDTO) {
        return ResponseEntity.ok(service.create(teamRequestDTO));
    }

    @PutMapping
    public ResponseEntity<TeamResponseDTO> update(@RequestBody @Valid TeamRequestDTO teamRequestDTO) {
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
