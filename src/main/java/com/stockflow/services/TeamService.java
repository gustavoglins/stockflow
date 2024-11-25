package com.stockflow.services;

import com.stockflow.dto.teamDtos.TeamRequestDTO;
import com.stockflow.dto.teamDtos.TeamResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TeamService {

    TeamResponseDTO create(TeamRequestDTO teamRequestDTO);

    TeamResponseDTO update(TeamRequestDTO teamRequestDTO);

    TeamResponseDTO findById(UUID id);

    List<TeamResponseDTO> findAll();

    void delete(UUID id);
}
