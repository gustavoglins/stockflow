package com.stockflow.services;

import com.stockflow.dto.userDtos.UserRequestDTO;
import com.stockflow.dto.userDtos.UserResponseDTO;
import com.stockflow.exceptions.EntityValidationException;
import com.stockflow.model.user.User;
import com.stockflow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    private Boolean dataValidation(UserRequestDTO userRequestDTO) {
        if (repository.existsByName(userRequestDTO.name())) {
            throw new EntityValidationException("Name " + userRequestDTO.name() + " already exists");
        }
        if (repository.existsByLogin(userRequestDTO.login())) {
            throw new EntityValidationException("Email " + userRequestDTO.login() + " already exists");
        }
        if (userRequestDTO.password().length() < 8) {
            throw new EntityValidationException("Password must be at least 8 characters long");
        }
        return true;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        if (dataValidation(userRequestDTO)) {
            return new UserResponseDTO(repository.save(new User(userRequestDTO)));
        } else throw new RuntimeException("Unexpected error. User not created");
    }

    @Override
    public UserResponseDTO update(UserRequestDTO userRequestDTO) {
        User userToUpdate = repository.findById(userRequestDTO.id())
                .orElseThrow(() -> new RuntimeException("User with ID: " + userRequestDTO.id() + " not found."));

        if (!userToUpdate.getName().equals(userRequestDTO.name())) {
            if (repository.existsByName(userRequestDTO.name())) {
                throw new RuntimeException("Name " + userRequestDTO.name() + " already registered");
            }
        }
        if (!userToUpdate.getLogin().equals(userRequestDTO.login())) {
            if (repository.existsByLogin(userRequestDTO.login()))
                throw new RuntimeException("Email " + userRequestDTO.login() + " already registered");
        }

        userToUpdate.setName(userRequestDTO.name());
        userToUpdate.setLogin(userRequestDTO.login());
        userToUpdate.setPassword(userRequestDTO.password());

        return new UserResponseDTO(repository.save(userToUpdate));
    }

    @Override
    public UserResponseDTO findById(UUID id) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found"));

        return new UserResponseDTO(repository.save(existingUser));
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("User with ID: " + id + " not found");
        }
    }
}
