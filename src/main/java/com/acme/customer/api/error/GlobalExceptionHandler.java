package com.acme.customer.api.error;

import com.acme.customer.application.CustomerEmailAlreadyExistsException;
import com.acme.customer.application.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleCustomerNotFound(
            CustomerNotFoundException exception) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        problem.setTitle("Customer not found");
        problem.setType(URI.create("urn:problem:customer-not-found"));

        return problem;
    }

    @ExceptionHandler(CustomerEmailAlreadyExistsException.class)
    public ProblemDetail handleCustomerEmailAlreadyExists(
            CustomerEmailAlreadyExistsException exception) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );

        problem.setTitle("Customer already exists");
        problem.setType(
                URI.create("urn:problem:customer-email-conflict")
        );

        return problem;
    }
}