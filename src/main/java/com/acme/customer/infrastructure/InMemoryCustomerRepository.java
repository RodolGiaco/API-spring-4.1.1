package com.acme.customer.infrastructure;

import com.acme.customer.domain.Customer;
import com.acme.customer.domain.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Long, Customer> customers = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public List<Customer> findAll() {
        return List.copyOf(customers.values());
    }

    @Override
    public Customer save(Customer customer) {
        long id = sequence.incrementAndGet();

        Customer savedCustomer = new Customer(
                id,
                customer.name(),
                customer.email()
        );

        customers.put(id, savedCustomer);

        return savedCustomer;
    }
}