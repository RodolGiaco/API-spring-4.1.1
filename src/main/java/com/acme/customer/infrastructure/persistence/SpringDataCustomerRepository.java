package com.acme.customer.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository
        extends JpaRepository<CustomerEntity, Long> {

    Page<CustomerEntity> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(
            String email,
            Long id
    );
}