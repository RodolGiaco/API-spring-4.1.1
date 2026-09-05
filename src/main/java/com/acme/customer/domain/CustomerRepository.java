package com.acme.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);
}