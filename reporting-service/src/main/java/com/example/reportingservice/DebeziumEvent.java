package com.example.reportingservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// A single file with nested static classes to represent the Debezium message structure
public class DebeziumEvent {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        @JsonProperty("after")
        private ProductData after;

        public ProductData getAfter() {
            return after;
        }

        public void setAfter(ProductData after) {
            this.after = after;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductData {
        private Long id;
        private String name;
        private Double price;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private Payload payload;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
