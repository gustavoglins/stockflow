package com.stockflow.config;

import com.stockflow.model.roles.Role;
import java.util.Set;
import com.stockflow.model.user.User;
import com.stockflow.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        var roleAdmin = Role.ADMIN;

        var userAdmin = userRepository.findByLogin("adminLogin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("Admin already exist!"),
                () -> {
                    var user = new User();
                    user.setName("example");
                    user.setLogin("example@example.com");
                    user.setPassword(bCryptPasswordEncoder.encode("example123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );
    }
}