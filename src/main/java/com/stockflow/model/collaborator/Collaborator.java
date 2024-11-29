package com.stockflow.model.collaborator;

import com.stockflow.model.team.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_collaborators")
public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
