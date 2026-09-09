package com.acme.customer.api;

import com.acme.customer.application.CustomerNotFoundException;
import com.acme.customer.application.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Test
    void shouldReturnProblemDetailWhenCustomerDoesNotExist()
            throws Exception {

        when(customerService.findById(999L))
                .thenThrow(new CustomerNotFoundException(999L));

        mockMvc.perform(get("/api/v1/customers/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(
                        "application/problem+json"
                ))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title")
                        .value("Customer not found"));
    }
}