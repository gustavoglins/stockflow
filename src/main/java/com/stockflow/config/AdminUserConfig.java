package com.stockflow.config;

import com.stockflow.model.roles.Role;
import com.stockflow.model.user.User;
import com.stockflow.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserConfig.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        var userAdmin = userRepository.findByLogin("example@example.com");

        userAdmin.ifPresentOrElse(
                user -> {
                    logger.info("[StockFlow] Admin already registered!");
                },
                () -> {
                    var user = new User();
                    user.setName("Example");
                    user.setLogin("example@example.com");
                    user.setPassword(bCryptPasswordEncoder.encode("example123"));
                    user.setRoles(Set.of(Role.ADMIN));
                    userRepository.save(user);
                }
        );
    }
}
