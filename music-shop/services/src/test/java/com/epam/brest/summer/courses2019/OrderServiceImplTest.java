package com.epam.brest.summer.courses2019;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-services.xml"})
@Rollback

public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test
    void findOrderById(){
        Order orders = orderService.findOrderById(1);
        System.out.println("orders = " + orders);
    }

    @Test
    void hyh(){
        Order order = new Order();
        orderService.addOrder(order);
        List<OrderDTO>orders = orderService.findAllOrderDTOs();
        System.out.println("orders = " + orders);

    }
}
