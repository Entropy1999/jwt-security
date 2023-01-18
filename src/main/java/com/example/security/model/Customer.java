package com.example.security.model;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "customer",
        uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "unique_customer_email")
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotBlank(message = "name cannot be blank")
    private String name;
    @Column(name = "email")
    @NotBlank(message = "email cannot be blank")
    private String email;
    @Column(name = "password")
    @NotBlank(message = "password cannot be blank")
    private String password;
    @Column(name = "roles")
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
