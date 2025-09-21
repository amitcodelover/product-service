
package com.example.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateProduct() {
        // given
        Product productToCreate = new Product();
        productToCreate.setName("Test Product");
        productToCreate.setPrice(99.99);

        // when
        ResponseEntity<Product> response = restTemplate.postForEntity("/products", productToCreate, Product.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Test Product");

        // Verify it was saved by trying to fetch it
        ResponseEntity<Product> fetchedResponse = restTemplate.getForEntity("/products/" + response.getBody().getId(), Product.class);
        assertThat(fetchedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(fetchedResponse.getBody()).isNotNull();
        assertThat(fetchedResponse.getBody().getName()).isEqualTo("Test Product");
    }
}
