package com.stockflow.repositories;

import com.stockflow.model.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

    Boolean existsByName(String name);
    Boolean existsByLogin(String login);
}
