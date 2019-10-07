package com.epam.brest.summer.courses2019;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-services.xml"})
@Rollback

class OrderServiceImplTest {

    @Autowired
    OrderService orderService;
    @Autowired
    ItemService itemService;

    @Test
    void addOrder(){
        int sizeBefore = orderService.findAllOrderDTOs().size();
        Order order = new Order();
        List<String> ids = new ArrayList<>();
        itemService.findAllItems().forEach(item->ids.add(item.getItemId().toString()));
        order.setItemsIds(ids);
        orderService.addOrder(order);
        assertEquals(order.getOrderDate(), LocalDate.now());
        assertEquals(sizeBefore+1, orderService.findAllOrderDTOs().size());
    }

    @Test
    void findOrderById(){
        Order orders = orderService.findOrderById(1);
        System.out.println("orders = " + orders);
    }


    @Test
    void updateOrderItems(){
        Order order = new Order();

        Item item1 = new Item("qwe", new BigDecimal("123"));
        Item item2 = new Item("qwe1", new BigDecimal("124"));
        item1.setItemId(1);
        item2.setItemId(2);

        List<String> items = new ArrayList<>();
        items.add(item1.getItemId().toString());
        items.add(item2.getItemId().toString());
        order.setItemsIds(items);

        orderService.addOrder(order);

        List<Item> newList = itemService.itemsList(order.getOrderId());
        assertEquals(item1.getItemId(), newList.get(0).getItemId());
        assertEquals(item2.getItemId(), newList.get(1).getItemId());
    }

    @Test
    void findAllDtos(){
        List<OrderDTO> orders = orderService.findAllOrderDTOs();
        assertNotNull(orders);
    }

    @Test
    void findDTOsByDates(){
        Order order = new Order();
        List<String> ids = new ArrayList<>();
        itemService.findAllItems().forEach(item->ids.add(item.getItemId().toString()));
        order.setItemsIds(ids);
        orderService.addOrder(order);

        List<OrderDTO> orders = orderService.findOrdersByDates(LocalDate.now(), LocalDate.now());
        assertEquals(1, orders.size());
    }
}
