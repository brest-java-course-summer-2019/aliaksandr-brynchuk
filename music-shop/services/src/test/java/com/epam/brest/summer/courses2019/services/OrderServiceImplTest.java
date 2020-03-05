package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.dao.ItemDao;
import com.epam.brest.summer.courses2019.dao.OrderDao;
import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestApplication.class)
@Transactional
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDao orderDao;

    private final static LocalDate FROM = LocalDate.now();
    private final static LocalDate TO = LocalDate.now();

    @Test
    void findOrderById(){
        Order order = orderService.findOrderById(1);

        assertNotNull(order);
        assertFalse(order.getItemsList().isEmpty());
    }

    @Test
    void findAllDtos(){
        List<OrderDTO> orders = orderService.findAllOrderDTOs();
        assertFalse(orders.isEmpty());
    }

    @Test
    void findDTOsByDates(){
        Order order = new Order();
        orderService.addOrder(order);

        List<OrderDTO> orders = orderService.findOrdersByDates(FROM, TO);
        assertEquals(1, orders.size());
    }



    @Test
    void updateOrder(){
        int id = 1;
        Order order = orderService.findOrderById(id);

        List<Item> itemsBefore = order.getItemsList();

        Item item1 = new Item("qwe", new BigDecimal("123"));
        Item item2 = new Item("qwe1", new BigDecimal("124"));

        itemDao.addItem(item1);
        itemDao.addItem(item2);

        List<String> itemsIds = new ArrayList<>();
        itemsIds.add(item1.getItemId().toString());
        itemsIds.add(item2.getItemId().toString());
        order.setItemsIds(itemsIds);

        orderService.updateOrder(order);

        List<Item>itemsAfter = orderDao.findOrderById(id).getItemsList();

        assertNotEquals(itemsBefore.get(0), itemsAfter.get(0));
        assertNotEquals(itemsBefore.get(1), itemsAfter.get(1));
    }

    @Test
    void deleteOrder(){
        int id = 1;

        int sizeBefore = orderService.findAllOrderDTOs().size();

        orderService.deleteOrder(id);

        int sizeAfter = orderService.findAllOrderDTOs().size();

        assertEquals(sizeBefore-1, sizeAfter);
    }
}
