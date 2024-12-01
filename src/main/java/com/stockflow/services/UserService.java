package com.stockflow.services;

import com.stockflow.dto.user.UserUpdateRequestDTO;
import com.stockflow.dto.user.UserResponseDTO;
import com.stockflow.dto.user.UserSignupRequestDTO;
import com.stockflow.dto.user.UserSignupResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserSignupResponseDTO signup(UserSignupRequestDTO userSignupRequestDTO);

    UserResponseDTO update(UserUpdateRequestDTO userRequestDTO);

    UserResponseDTO findById(UUID id);

    List<UserResponseDTO> findAll();

    void delete(UUID id);
}