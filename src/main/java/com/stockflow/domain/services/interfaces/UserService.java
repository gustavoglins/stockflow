package com.stockflow.domain.services.interfaces;

import com.stockflow.api.requests.user.UserSignupRequestDTO;
import com.stockflow.api.requests.user.UserUpdateRequestDTO;
import com.stockflow.api.responses.user.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDTO create(UserSignupRequestDTO userSignupRequestDTO);

    UserResponseDTO update(UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO findById(UUID id);

    List<UserResponseDTO> findAll();

    void delete(UUID id);
}
