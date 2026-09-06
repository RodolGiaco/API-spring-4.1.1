package com.acme.customer.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository
        extends JpaRepository<CustomerEntity, Long> {
}