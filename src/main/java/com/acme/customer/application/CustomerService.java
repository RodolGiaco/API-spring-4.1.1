package com.acme.customer.application;

import com.acme.customer.domain.Customer;
import com.acme.customer.domain.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer create(String name, String email) {
        Customer customer = new Customer(
                null,
                name.trim(),
                email.trim().toLowerCase(Locale.ROOT)
        );

        return customerRepository.save(customer);
    }
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}