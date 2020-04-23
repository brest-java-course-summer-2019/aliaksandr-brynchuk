package com.test.hp.shop_rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.test.hp.shop_rest.*")
@EntityScan(basePackages = "com.test.hp.shop_rest.*")
@EnableJpaRepositories(basePackages = "com.test.hp.shop_rest.*")
public class BroomstickShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(BroomstickShopApplication.class, args);
    }
}
