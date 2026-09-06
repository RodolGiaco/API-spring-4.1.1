package com.acme.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    CustomerPage search(String name, int page, int size);

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    void deleteById(Long id);
}