package com.stockflow.domain.services.impl;

import com.stockflow.api.requests.user.UserSignupRequestDTO;
import com.stockflow.api.requests.user.UserUpdateRequestDTO;
import com.stockflow.api.responses.user.UserDetailsResponseDTO;
import com.stockflow.domain.entities.User;
import com.stockflow.domain.repositories.UserRepository;
import com.stockflow.domain.services.interfaces.UserService;
import com.stockflow.exceptions.DataAlreadyInUseException;
import com.stockflow.exceptions.DataNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetailsResponseDTO create(UserSignupRequestDTO userSignupRequestDTO) {
        if (repository.existsByLogin(userSignupRequestDTO.login())) {
            User newUser = new User(userSignupRequestDTO);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return new UserDetailsResponseDTO(repository.save(newUser));
        } else {
            throw new DataAlreadyInUseException("Login already in use");
        }
    }

    @Override
    public UserDetailsResponseDTO update(UserUpdateRequestDTO userUpdateRequestDTO) {
        Optional<User> optionalUser = repository.findById(userUpdateRequestDTO.id());
        if (optionalUser.isPresent()) {
            User retrivedUser = optionalUser.get();
            if (!retrivedUser.getLogin().equals(userUpdateRequestDTO.login())) {
                if (repository.existsByLogin(userUpdateRequestDTO.login())) {
                    throw new DataAlreadyInUseException("Login already in use");
                }
            }

            retrivedUser.setName(userUpdateRequestDTO.name());
            retrivedUser.setLogin(userUpdateRequestDTO.login());
            retrivedUser.setPassword(passwordEncoder.encode(userUpdateRequestDTO.password()));

            return new UserDetailsResponseDTO(repository.save(retrivedUser));
        } else {
            throw new DataNotFoundException("User not found");
        }
    }

    @Override
    public UserDetailsResponseDTO findById(UUID id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            return new UserDetailsResponseDTO(optionalUser.get());
        } else {
            throw new DataNotFoundException("User not found");
        }
    }

    @Override
    public List<UserDetailsResponseDTO> findAll() {
        return repository.findAll().stream().map(UserDetailsResponseDTO::new).toList();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
