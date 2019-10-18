package com.epam.brest.summer.courses2019.web_app.consumers;

import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

class OrderRestConsumerTest {

    @Mock
    private RestTemplate restTemplate;

    private OrderRestConsumer consumer;

    @BeforeEach
    void setUp(){
        initMocks(this);
        consumer = new OrderRestConsumer("url", restTemplate);
    }

    @Test
    void findAllDto(){
        List<OrderDTO> orders = Arrays.asList(createDto(1), createDto(2));

        Mockito.when(restTemplate.exchange("url/orders",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDTO>>() {}))
                .thenReturn(new ResponseEntity<>(Arrays.asList(createDto(1), createDto(2)), HttpStatus.OK));

        List<OrderDTO> result = consumer.findAllOrderDTOs();

        assertNotNull(result);

        assertEquals(orders, result);
    }

    @Test
    void findOrderById(){
        int id = 1;
        Order order = createOrder(id);
        Mockito.when(restTemplate.getForEntity("url/"+id, Order.class))
                .thenReturn(new ResponseEntity<>(createOrder(id), HttpStatus.OK));

        Order result = consumer.findOrderById(id);

        assertNotNull(result);

        assertEquals(order.getOrderId(), result.getOrderId());
    }

    @Test
    void addOrder(){
        int id = 1;
        Order order = createOrder(id);
        Mockito.when(restTemplate.postForEntity("url", order, Order.class))
                .thenReturn(new ResponseEntity<>(order, HttpStatus.CREATED));

        consumer.addOrder(order);

        Mockito.verify(restTemplate).postForEntity("url", order, Order.class);
    }

    @Test
    void updateOrder(){
        int id = 1;
        Order order = createOrder(id);

        Mockito.doNothing().when(restTemplate).put("url", order);

        consumer.updateOrder(order);

        Mockito.verify(restTemplate).put("url", order);
    }

    @Test
    void deleteOrder(){
        int id = 1;

        Mockito.doNothing().when(restTemplate).delete("url/"+id+"/delete");

        consumer.deleteOrder(id);

        Mockito.verify(restTemplate).delete("url/"+id+"/delete");
    }

    private OrderDTO createDto(Integer orderId) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(orderId);
        return dto;
    }

    private Order createOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setItemsIds(Arrays.asList());
        return order;
    }
}
