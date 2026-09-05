package com.acme.customer.api;

import com.acme.customer.application.CustomerService;
import com.acme.customer.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll()
                .stream()
                .map(CustomerController::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        return toResponse(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerCreateRequest request) {

        Customer customer = customerService.create(
                request.name(),
                request.email()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(customer));
    }

    private static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.id(),
                customer.name(),
                customer.email()
        );
    }
}