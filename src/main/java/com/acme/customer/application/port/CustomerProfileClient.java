package com.acme.customer.application.port;

import com.acme.customer.domain.CustomerProfile;

public interface CustomerProfileClient {

    CustomerProfile findByCustomerId(Long customerId);
}