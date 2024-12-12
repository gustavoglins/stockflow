package com.stockflow.domain.repositories;

import com.stockflow.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByLogin(String login);

    Boolean existsByLogin(String login);
}
