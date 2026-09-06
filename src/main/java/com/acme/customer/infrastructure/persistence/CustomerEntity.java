package com.acme.customer.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 320, unique = true)
    private String email;

    protected CustomerEntity() {
    }

    CustomerEntity(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }
}