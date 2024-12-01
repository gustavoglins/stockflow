package com.stockflow.model.user;

import com.stockflow.dto.user.signup.UserSignupRequestDTO;
import com.stockflow.model.product.Product;
import com.stockflow.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_users")
public class User implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList;

    public User() {
    }

    public User(UserSignupRequestDTO userSignupRequestDTO){
        this.name = userSignupRequestDTO.name();
        this.login = userSignupRequestDTO.login();
        this.password = userSignupRequestDTO.password();
        this.role = Role.COMMON_USER;
        this.productList = new ArrayList<>();
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

    public List<Product> getProductList() {
        return productList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Always add the role "USER", as all users have this role
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Add the specific roles depending on the user's role
        switch (this.role) {
            case ADMIN:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case COMMON_USER:
                authorities.add(new SimpleGrantedAuthority("ROLE_COMMON_USER"));
                break;
            case COMPANY_OWNER:
                authorities.add(new SimpleGrantedAuthority("ROLE_COMPANY_OWNER"));
                break;
            case COMPANY_EMPLOYEE:
                authorities.add(new SimpleGrantedAuthority("ROLE_COMPANY_EMPLOYEE"));
                break;
            case TEAM_LEADER:
                authorities.add(new SimpleGrantedAuthority("ROLE_TEAM_LEADER"));
                break;
            case TEAM_COLLABORATOR:
                authorities.add(new SimpleGrantedAuthority("ROLE_TEAM_COLLABORATOR"));
                break;
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
