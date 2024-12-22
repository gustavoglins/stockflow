package com.stockflow.domain.services.interfaces;

import com.stockflow.api.requests.user.UserSignupRequestDTO;
import com.stockflow.api.requests.user.UserUpdateRequestDTO;
import com.stockflow.api.responses.user.UserDetailsResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDetailsResponseDTO create(UserSignupRequestDTO userSignupRequestDTO);

    UserDetailsResponseDTO update(UserUpdateRequestDTO userUpdateRequestDTO);

    UserDetailsResponseDTO findById(UUID id);

    List<UserDetailsResponseDTO> findAll();

    void delete(UUID id);
}
