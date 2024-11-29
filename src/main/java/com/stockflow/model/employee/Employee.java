package com.stockflow.model.employee;

import com.stockflow.model.company.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
