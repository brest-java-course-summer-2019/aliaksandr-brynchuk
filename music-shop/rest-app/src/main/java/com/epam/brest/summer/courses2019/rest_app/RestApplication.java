package com.epam.brest.summer.courses2019.rest_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.epam.brest.summer.courses2019.*")
@EntityScan(basePackages = "com.epam.brest.summer.courses2019.*")
@EnableJpaRepositories(basePackages = "com.epam.brest.summer.courses2019.dao")
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}

