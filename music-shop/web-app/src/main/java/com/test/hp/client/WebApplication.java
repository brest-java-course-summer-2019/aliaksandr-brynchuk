package com.test.hp.client;

import com.test.hp.client.consumers.ItemRestConsumer;
import com.test.hp.client.consumers.OrderRestConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.test.hp.client.*")
@EnableJpaRepositories(basePackages = "com.test.hp.client.*")
@EntityScan(basePackages = "com.test.hp.client.*")
public class WebApplication {

    @Value("${rest.url}")
    private String restUrl;

    @Value("${rest.items}")
    private String restItems;

    @Value("${rest.orders}")
    private String restOrders;

    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public ItemRestConsumer itemService() {
        return new ItemRestConsumer(restUrl + restItems, restTemplate());
    }

    @Bean
    public OrderRestConsumer orderService() {
        return new OrderRestConsumer(restUrl + restOrders, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        messageConverters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
