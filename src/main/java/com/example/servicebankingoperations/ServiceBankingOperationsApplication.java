package com.example.servicebankingoperations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiceBankingOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBankingOperationsApplication.class, args);
    }

}
