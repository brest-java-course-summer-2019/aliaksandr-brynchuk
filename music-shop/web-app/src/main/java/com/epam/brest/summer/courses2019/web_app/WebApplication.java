package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.web_app.consumers.ItemRestConsumer;
import com.epam.brest.summer.courses2019.web_app.consumers.OrderRestConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.epam.brest.summer.courses2019.*")
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
