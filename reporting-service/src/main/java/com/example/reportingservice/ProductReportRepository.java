package com.example.reportingservice;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ProductReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        // Create the table in ClickHouse on startup
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS product_report (" +
                "  id UInt64," +
                "  name String," +
                "  price Float64" +
                ") ENGINE = MergeTree() ORDER BY id");
    }

    public void saveProduct(Long id, String name, Double price) {
        // Simple insert statement
        jdbcTemplate.update("INSERT INTO product_report (id, name, price) VALUES (?, ?, ?)", id, name, price);
    }
}
