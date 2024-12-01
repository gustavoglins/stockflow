package com.stockflow.services;

import com.stockflow.dto.user.UserRequestDTO;
import com.stockflow.dto.user.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDTO create(UserRequestDTO userRequestDTO);

    UserResponseDTO update(UserRequestDTO userRequestDTO);

    UserResponseDTO findById(UUID id);

    List<UserResponseDTO> findAll();

    void delete(UUID id);
}
