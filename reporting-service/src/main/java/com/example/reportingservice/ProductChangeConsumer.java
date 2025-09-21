package com.example.reportingservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductChangeConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProductChangeConsumer.class);

    private final ObjectMapper objectMapper;
    private final ProductReportRepository productReportRepository;

    public ProductChangeConsumer(ObjectMapper objectMapper, ProductReportRepository productReportRepository) {
        this.objectMapper = objectMapper;
        this.productReportRepository = productReportRepository;
    }

    @KafkaListener(topics = "postgres-server.public.product", groupId = "reporting-group-id")
    public void consume(String message) {
        log.info("Received raw CDC event: {}", message);
        try {
            DebeziumEvent event = objectMapper.readValue(message, DebeziumEvent.class);

            // We are interested in create ('c') and update ('u') events.
            // The 'after' field contains the state of the row after the change.
            if (event != null && event.getPayload() != null && event.getPayload().getAfter() != null) {
                DebeziumEvent.ProductData productData = event.getPayload().getAfter();
                log.info("Processed event for product ID: {}. Saving to ClickHouse.", productData.getId());
                productReportRepository.saveProduct(
                        productData.getId(),
                        productData.getName(),
                                                productData.getPrice()
                );
            }
        } catch (JsonProcessingException e) {
            log.error("Error parsing Debezium event", e);
        }
    }
}
