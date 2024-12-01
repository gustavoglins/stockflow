package com.stockflow.model.product;

import com.stockflow.dto.product.ProductRequestDTO;
import com.stockflow.model.company.Company;
import com.stockflow.model.team.Team;
import com.stockflow.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_products")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Min(value = 0)
    private Double buyPrice;

    @Column(nullable = false)
    @Min(value = 0)
    private Double sellPrice;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product() {
    }

    public Product(ProductRequestDTO productRequestDTO) {
        this.name = productRequestDTO.name();
        this.image = productRequestDTO.image();
        this.description = productRequestDTO.description();
        this.buyPrice = productRequestDTO.buyPrice();
        this.sellPrice = productRequestDTO.sellPrice();
        this.quantity = productRequestDTO.quantity();
        this.company = productRequestDTO.company();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Company getCompany() {
        return company;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", quantity=" + quantity +
                ", company=" + company +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
