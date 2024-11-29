package com.stockflow.model.company;

import com.stockflow.dto.companyDtos.CompanyRequestDTO;
import com.stockflow.model.employee.Employee;
import com.stockflow.model.product.Product;
import com.stockflow.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_companies")
public class Company implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String legalName;

    @Column(unique = true, nullable = false)
    private String taxId;

    @Column(unique = true, nullable = false)
    private String contactPhone;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Company() {
    }

    public Company(CompanyRequestDTO companyRequestDTO) {
        this.name = companyRequestDTO.name();
        this.legalName = companyRequestDTO.legalName();
        this.taxId = companyRequestDTO.taxId();
        this.contactPhone = companyRequestDTO.contactPhone();
        this.login = companyRequestDTO.login();
        this.password = companyRequestDTO.password();
        this.employees = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", legalName='" + legalName + '\'' +
                ", taxId='" + taxId + '\'' +
                ", employees=" + employees +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
