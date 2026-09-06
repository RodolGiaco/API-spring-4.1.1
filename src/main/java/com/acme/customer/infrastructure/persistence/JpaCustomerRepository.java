package com.acme.customer.infrastructure.persistence;

import com.acme.customer.domain.Customer;
import com.acme.customer.domain.CustomerPage;
import com.acme.customer.domain.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
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
    public CustomerPage search(String name, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").ascending()
        );

        Page<CustomerEntity> result =
                name == null || name.isBlank()
                        ? repository.findAll(pageable)
                        : repository.findByNameContainingIgnoreCase(
                        name.trim(),
                        pageable
                );

        return new CustomerPage(
                result.getContent()
                        .stream()
                        .map(JpaCustomerRepository::toDomain)
                        .toList(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmailIgnoreCase(email);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return repository.existsByEmailIgnoreCaseAndIdNot(email, id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
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