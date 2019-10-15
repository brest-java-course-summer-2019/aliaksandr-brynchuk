package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderDTO;
import com.epam.brest.summer.courses2019.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

public class OrderRestConsumer implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestConsumer.class);

    private String url;
    private RestTemplate restTemplate;

    public OrderRestConsumer(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        LOGGER.debug("OrderRestConsumer: findAllOrderDTOs");

        return restTemplate.exchange(url +"/orders",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDTO>>(){})
                .getBody();
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("OrderRestConsumer: findOrderById {}", orderId);

        return restTemplate.getForEntity(url +"/"+orderId, Order.class).getBody();
    }

    @Override
    public Order addOrder(Order order) {
        LOGGER.debug("OrderRestConsumer: addOrder({})", order);

        return restTemplate.postForEntity(url, order, Order.class).getBody();
    }

    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("OrderRestConsumer: updateOrder({})", order);

        restTemplate.put(url, order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("OrderRestConsumer: deleteOrder({})", orderId);

        restTemplate.delete(url+"/"+orderId);
    }

    @Override
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        LOGGER.debug("OrderRestConsumer: findOrdersByDates({}, {})", from, to);

        return restTemplate.exchange(url  + "/orders" +"/"+from+"/"+to,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDTO>>(){})
                .getBody();
    }
}
