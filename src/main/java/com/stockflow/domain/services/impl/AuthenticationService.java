package com.stockflow.domain.services.impl;

import com.stockflow.api.requests.user.UserLoginRequestDTO;
import com.stockflow.api.requests.user.UserSignupRequestDTO;
import com.stockflow.api.responses.user.UserAuthResponseDTO;
import com.stockflow.domain.entities.User;
import com.stockflow.domain.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public UserAuthResponseDTO userSignup(UserSignupRequestDTO userSignupRequestDTO) {
        logger.info("Signup attempt for login: {}", userSignupRequestDTO.login());

        if (this.userRepository.findByLogin(userSignupRequestDTO.login()) != null) {
            logger.error("Signup failed: user {} already exists.", userSignupRequestDTO.login());
            throw new IllegalArgumentException("User already exists");
        }

        logger.info("Starting password encryption process for user: {}", userSignupRequestDTO.login());
        String encryptedPassword = new BCryptPasswordEncoder().encode(userSignupRequestDTO.password());
        User newUser = new User(userSignupRequestDTO);
        newUser.setPassword(encryptedPassword);
        logger.info("Password encryption process completed for user: {}", userSignupRequestDTO.login());

        userRepository.save(newUser);

        logger.info("User {} successfully registered. Generating token.", userSignupRequestDTO.login());
        var token = tokenService.generateToken(newUser);

        logger.info("Token successfully generated for user: {}", userSignupRequestDTO.login());
        return new UserAuthResponseDTO(newUser.getLogin(), token);
    }

    public UserAuthResponseDTO userLogin(UserLoginRequestDTO userLoginRequestDTO) {
        logger.info("Login attempt for user: {}", userLoginRequestDTO.login());

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginRequestDTO.login(), userLoginRequestDTO.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var authenticatedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            logger.debug("Authentication successful for user: {}", authenticatedUser.getUsername());

            User user = userRepository.findByLogin(authenticatedUser.getUsername());
            if (user == null) {
                logger.error("Authenticated user not found in the database: {}", authenticatedUser.getUsername());
                throw new IllegalStateException("User not found in the database");
            }

            var token = tokenService.generateToken(user);
            logger.info("Login successful. Token generated for user: {}", user.getLogin());

            return new UserAuthResponseDTO(user.getLogin(), token);
        } catch (Exception exception) {
            logger.error("Error during login attempt for user: {}", userLoginRequestDTO.login(), exception);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
