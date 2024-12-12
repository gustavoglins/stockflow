package com.stockflow.domain.services.impl;

import com.stockflow.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
