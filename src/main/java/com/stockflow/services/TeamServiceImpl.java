package com.stockflow.services;

import com.stockflow.dto.teamDtos.TeamRequestDTO;
import com.stockflow.dto.teamDtos.TeamResponseDTO;
import com.stockflow.model.team.Team;
import com.stockflow.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;

    public TeamServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }

    private Boolean dataValidation(TeamRequestDTO teamRequestDTO) {
        if (repository.existsByName(teamRequestDTO.name())) {
            throw new RuntimeException("Name " + teamRequestDTO.name() + " already registered");
        }
        return true;
    }

    @Override
    public TeamResponseDTO create(TeamRequestDTO teamRequestDTO) {
        if (dataValidation(teamRequestDTO)) {
            return new TeamResponseDTO(repository.save(new Team(teamRequestDTO)));
        } else throw new RuntimeException("Unexpected error. Team not created");
    }

    @Override
    public TeamResponseDTO update(TeamRequestDTO teamRequestDTO) {
        Team teamToUpdate = repository.findById(teamRequestDTO.id())
                .orElseThrow(() -> new RuntimeException("Team with ID: " + teamRequestDTO.id() + " not found"));

        if (!teamToUpdate.getName().equals(teamRequestDTO.name())) {
            if (repository.existsByName(teamRequestDTO.name())) {
                throw new RuntimeException("Name " + teamRequestDTO.name() + " already registered");
            }
        }

        teamToUpdate.setName(teamRequestDTO.name());

        return new TeamResponseDTO(repository.save(teamToUpdate));
    }

    @Override
    public TeamResponseDTO findById(UUID id) {
        Team teamToUpdate = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team with ID: " + id + " not found"));

        return new TeamResponseDTO(teamToUpdate);
    }

    @Override
    public List<TeamResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(TeamResponseDTO::new)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (repository.findById(id).isPresent()) repository.deleteById(id);
        else throw new RuntimeException("Team with ID: " + id + " not found");
    }
}
