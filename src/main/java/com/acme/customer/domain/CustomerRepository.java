package com.acme.customer.domain;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer save(Customer customer);
}