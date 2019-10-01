package com.epam.brest.summer.courses2019;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-services.xml"})
@Rollback

public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

//    @Test
//    void addOrder(){
//        Order order = new Order();
//        orderService.addOrder(order);
//    }

    @Test
    void findOrderById(){
        Order orders = orderService.findOrderById(1);
        System.out.println("orders = " + orders);
    }

//    @Test
//    void hyh(){
//        Order order = new Order();
//        orderService.addOrder(order);
//        List<OrderDTO>orders = orderService.findAllOrderDTOs();
//        System.out.println("orders = " + orders);
//    }

//    @Test
//    void updateOrderItems(){
//        Order order = new Order();
//        orderService.addOrder(order);
//        List<Item> list = new ArrayList<>();
//        Item item1 = new Item("qwe", new BigDecimal("123"));
//        Item item2 = new Item("qwe1", new BigDecimal("124"));
//        item1.setItemId(2);
//        item2.setItemId(3);
//        list.add(item1);
//        list.add(item2);
//        order.setItemsList(list);
//        orderService.updateOrder(order);
//    }
}
