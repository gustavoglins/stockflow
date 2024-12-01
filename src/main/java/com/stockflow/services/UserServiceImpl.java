package com.stockflow.services;

import com.stockflow.dto.user.UserDetailsResponseDTO;
import com.stockflow.dto.user.update.UserUpdateRequestDTO;
import com.stockflow.dto.user.update.UserUpdateResponseDTO;
import com.stockflow.dto.user.signup.UserSignupRequestDTO;
import com.stockflow.dto.user.signup.UserSignupResponseDTO;
import com.stockflow.exceptions.EntityValidationException;
import com.stockflow.model.user.User;
import com.stockflow.repositories.UserRepository;
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

    private Boolean dataValidation(UserSignupRequestDTO userSignupRequestDTO) {
        if (repository.existsByName(userSignupRequestDTO.name())) {
            throw new EntityValidationException("Name " + userSignupRequestDTO.name() + " already exists");
        }
        if (repository.existsByLogin(userSignupRequestDTO.login())) {
            throw new EntityValidationException("Email " + userSignupRequestDTO.login() + " already exists");
        }
        if (userSignupRequestDTO.password().length() < 8) {
            throw new EntityValidationException("Password must be at least 8 characters long");
        }
        return true;
    }

    @Override
    public UserSignupResponseDTO signup(UserSignupRequestDTO userSignupRequestDTO) {
        if(dataValidation(userSignupRequestDTO)) {
            User newUser = new User(userSignupRequestDTO);
            newUser.setPassword(passwordEncoder.encode(userSignupRequestDTO.password()));
            return new UserSignupResponseDTO(repository.save(newUser));
        } else {
            throw new RuntimeException("Unexpected error! User not created");
        }
    }

    @Override
    public UserUpdateResponseDTO update(UserUpdateRequestDTO userRequestDTO) {
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

        return new UserUpdateResponseDTO(repository.save(userToUpdate));
    }

    @Override
    public UserDetailsResponseDTO findById(UUID id) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found"));

        return new UserDetailsResponseDTO(repository.save(existingUser));
    }

    @Override
    public List<UserDetailsResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(UserDetailsResponseDTO::new)
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