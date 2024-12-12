package com.stockflow.domain.services.impl;

import com.stockflow.api.requests.UserLoginRequestDTO;
import com.stockflow.api.requests.UserSignupRequestDTO;
import com.stockflow.api.responses.UserAuthResponseDTO;
import com.stockflow.domain.entities.User;
import com.stockflow.domain.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public UserAuthResponseDTO userSignup(UserSignupRequestDTO userSignupRequestDTO) {
        if (this.userRepository.findByLogin(userSignupRequestDTO.login()) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userSignupRequestDTO.password());
        User newUser = new User(userSignupRequestDTO);
        newUser.setPassword(encryptedPassword);
        userRepository.save(newUser);

        return new UserAuthResponseDTO(newUser.getLogin(), null); //TODO: Just return login for now
    }

    public UserAuthResponseDTO userLogin(UserLoginRequestDTO userLoginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.login(), userLoginRequestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var authenticatedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByLogin(authenticatedUser.getUsername());
        var token = tokenService.generateToken(user);
        return new UserAuthResponseDTO(user.getLogin(), token);
    }
}
