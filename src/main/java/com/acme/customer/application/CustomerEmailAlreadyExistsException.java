package com.acme.customer.application;

public class CustomerEmailAlreadyExistsException
        extends RuntimeException {

    public CustomerEmailAlreadyExistsException(String email) {
        super("Customer with email " + email + " already exists");
    }
}