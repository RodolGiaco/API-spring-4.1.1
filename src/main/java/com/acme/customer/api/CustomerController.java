package com.acme.customer.api;

import com.acme.customer.application.CustomerService;
import com.acme.customer.domain.Customer;
import com.acme.customer.domain.CustomerPage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(
        name = "Customers",
        description = "Customer management operations"
)
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public CustomerPageResponse findAll(
            @RequestParam(required = false)
            @Size(max = 120)
            String name,

            @RequestParam(defaultValue = "0")
            @Min(0)
            int page,

            @RequestParam(defaultValue = "20")
            @Min(1)
            @Max(100)
            int size) {

        CustomerPage result = customerService.search(name, page, size);

        return new CustomerPageResponse(
                result.content()
                        .stream()
                        .map(CustomerController::toResponse)
                        .toList(),
                result.page(),
                result.size(),
                result.totalElements(),
                result.totalPages()
        );
    }
    @Operation(
            summary = "Get customer by id",
            description = "Returns a customer identified by its id"
    )
    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        return toResponse(customerService.findById(id));
    }

    @Operation(
            summary = "Create customer",
            description = "Creates a new customer"
    )
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerCreateRequest request) {

        Customer customer = customerService.create(request.name(), request.email());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.id())
                .toUri();

        return ResponseEntity.created(location).body(toResponse(customer));
    }

    @Operation(
            summary = "Update customer",
            description = "Updates an existing customer"
    )
    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequest request) {
        return toResponse(customerService.update(id, request.name(), request.email()));
    }

    @Operation(
            summary = "Delete customer",
            description = "Deletes an existing customer"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    private static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(customer.id(), customer.name(), customer.email());
    }
}