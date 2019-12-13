package com.epam.brest.summer.courses2019.rest_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.epam.brest.summer.courses2019.*")
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}

