package com.test.hp.client.consumers;

import com.test.hp.client.api.OrderService;
import com.test.hp.client.model.Order;
import com.test.hp.client.model.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

/**
 * Order REST consumer
 */
public class OrderRestConsumer implements OrderService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestConsumer.class);

    /**
     * Request URL
     */
    private String url;

    /**
     * Rest template field
     */
    private RestTemplate restTemplate;

    /**
     * Constructor
     *
     * @param url Request URL
     * @param restTemplate Rest template
     */
    public OrderRestConsumer(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Find all orderDTOs
     *
     * @return Response entity body(OrderDTOs list)
     */
    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        LOGGER.debug("OrderRestConsumer: findAllOrderDTOs");

        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDTO>>(){})
                .getBody();
    }

    /**
     * Find order by ID
     *
     * @param orderId Order ID
     * @return response entity body(order)
     */
    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("OrderRestConsumer: findOrderById {}", orderId);

        return restTemplate.getForEntity(url +"/"+orderId, Order.class).getBody();
    }

    /**
     * Add order
     *
     * @param order Order
     */
    @Override
    public void addOrder(Order order) {
        LOGGER.debug("OrderRestConsumer: addOrder({})", order);

        restTemplate.postForEntity(url, order, Order.class).getBody();
    }

    /**
     * Update order
     *
     * @param order Order
     */
    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("OrderRestConsumer: updateOrder({})", order);

        restTemplate.put(url+"/"+order.getOrderId(), order);
    }

    /**
     * Delete order
     *
     * @param orderId Order ID
     */
    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("OrderRestConsumer: deleteOrder({})", orderId);

        restTemplate.delete(url+"/"+orderId);
    }

    /**
     * Find orderDTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return response entity body(OrderDTOs List)
     */
    @Override
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        LOGGER.debug("OrderRestConsumer: findOrdersByDates({}, {})", from, to);

        return restTemplate.exchange(url +"/"+from+"/"+to,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDTO>>(){})
                .getBody();
    }
}
