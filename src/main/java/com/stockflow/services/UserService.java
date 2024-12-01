package com.stockflow.services;

import com.stockflow.dto.user.UserDetailsResponseDTO;
import com.stockflow.dto.user.update.UserUpdateRequestDTO;
import com.stockflow.dto.user.update.UserUpdateResponseDTO;
import com.stockflow.dto.user.signup.UserSignupRequestDTO;
import com.stockflow.dto.user.signup.UserSignupResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserSignupResponseDTO signup(UserSignupRequestDTO userSignupRequestDTO);

    UserUpdateResponseDTO update(UserUpdateRequestDTO userUpdateRequestDTO);

    UserDetailsResponseDTO findById(UUID id);

    List<UserDetailsResponseDTO> findAll();

    void delete(UUID id);
}