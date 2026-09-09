package com.acme.customer;

import com.acme.customer.application.CustomerService;
import com.acme.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class CustomerPersistenceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldPersistCustomerInPostgresql() {
        Customer customer = customerService.create(
                "John Doe",
                "john@example.com"
        );

        Integer count = jdbcTemplate.queryForObject(
                """
                SELECT COUNT(*)
                FROM customers
                WHERE email = ?
                """,
                Integer.class,
                "john@example.com"
        );

        assertThat(customer.id()).isNotNull();
        assertThat(count).isEqualTo(1);
    }
}