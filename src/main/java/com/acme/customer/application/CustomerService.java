package com.acme.customer.application;

import com.acme.customer.domain.Customer;
import com.acme.customer.domain.CustomerPage;
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

    public CustomerPage search(String name, int page, int size) {
        return customerRepository.search(name, page, size);
    }
    @Transactional
    public Customer create(String name, String email) {
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);

        if (customerRepository.existsByEmail(normalizedEmail)) {
            throw new CustomerEmailAlreadyExistsException(normalizedEmail);
        }

        return customerRepository.save(new Customer(null, name.trim(), normalizedEmail));
    }
    @Transactional
    public Customer update(Long id, String name, String email) {
        Customer existing = findById(id);
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);

        if (customerRepository.existsByEmailAndIdNot(normalizedEmail, id)) {
            throw new CustomerEmailAlreadyExistsException(normalizedEmail);
        }
        return customerRepository.save(new Customer(existing.id(), name.trim(), normalizedEmail));
    }

    @Transactional
    public void delete(Long id) {
        Customer customer = findById(id);
        customerRepository.deleteById(customer.id());
    }
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}