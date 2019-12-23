package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.dao.ItemDao;
import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-services.xml"})

@Transactional
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDao itemDao;

    private final static LocalDate FROM = LocalDate.of(2019, 8, 1);
    private final static LocalDate TO = LocalDate.of(2019, 8, 1);

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
        List<String> ids = new ArrayList<>();
        itemService.findAllItems().forEach(item->ids.add(item.getItemId().toString()));
        order.setItemsIds(ids);
        orderService.addOrder(order);

        List<OrderDTO> orders = orderService.findOrdersByDates(FROM, TO);
        assertEquals(1, orders.size());
    }


    /*
    there are two methods in this test: addOrder and updateOrderItems
    updateOrderItems is private, so it should be tested here
     */
    @Test
    void addOrderAndUpdateOrderItems(){
        int sizeBefore = orderService.findAllOrderDTOs().size();
        Order order = new Order();

        Item item1 = new Item("qwe", new BigDecimal("123"));
        Item item2 = new Item("qwe1", new BigDecimal("124"));

        itemDao.addItem(item1);
        itemDao.addItem(item2);

        List<String> itemsIds = new ArrayList<>();
        itemsIds.add(item1.getItemId().toString());
        itemsIds.add(item2.getItemId().toString());
        order.setItemsIds(itemsIds);
        orderService.addOrder(order);
        assertEquals(order.getOrderDate(), LocalDate.now());
        assertEquals(sizeBefore+1, orderService.findAllOrderDTOs().size());

        List<Item> newList = itemDao.itemsListFromOrder(order.getOrderId());
        assertEquals(item1.getItemId(), newList.get(0).getItemId());
        assertEquals(item2.getItemId(), newList.get(1).getItemId());
        assertEquals(item1.getItemName(), newList.get(0).getItemName());
        assertEquals(item2.getItemName(), newList.get(1).getItemName());
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

        List<Item>itemsAfter = itemDao.itemsListFromOrder(order.getOrderId());

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
