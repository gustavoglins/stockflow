package com.stockflow.repositories;

import com.stockflow.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLogin(String login);

    Boolean existsByName(String name);
    Boolean existsByLogin(String login);
}
