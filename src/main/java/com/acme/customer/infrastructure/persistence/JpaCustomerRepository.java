package com.acme.customer.infrastructure.persistence;

import com.acme.customer.domain.Customer;
import com.acme.customer.domain.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    private final SpringDataCustomerRepository repository;

    public JpaCustomerRepository(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll()
                .stream()
                .map(JpaCustomerRepository::toDomain)
                .toList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id)
                .map(JpaCustomerRepository::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = new CustomerEntity(
                customer.id(),
                customer.name(),
                customer.email()
        );

        return toDomain(repository.save(entity));
    }

    private static Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }
}